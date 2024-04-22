package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.ObservationBl;
import com.taller.psico.dto.ObservationDTO;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/observation")
@CrossOrigin(origins = "*", methods= {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.DELETE})
public class ObservationApi {
    @Autowired
    private ObservationBl observationBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear una observacion de un avance clinico
    @PostMapping("/create/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<String>> createObservation(@PathVariable Integer clinicalAdvancesId,@RequestBody String observation, @RequestHeader("Authorization") String token){
        observationBl.createObservation(clinicalAdvancesId, observation);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, "Observacion creada correctamente", "Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, "Error al crear la observacion", "Error"));
        }
    }

    //Ver todas las observaciones de un avance clinico
    @GetMapping("/all/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<List<ObservationDTO>>> findAllByClinicalAdvancesId(@PathVariable Integer clinicalAdvancesId, @RequestHeader("Authorization") String token){
        List<ObservationDTO> observations = observationBl.findAllByClinicalAdvancesId(clinicalAdvancesId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, observations, "Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
        }
    }

}
