package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.PrescriptionBl;
import com.taller.psico.dto.PrescriptionDTO;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/prescription")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class PrescriptionApi {

    @Autowired
    private PrescriptionBl prescriptionBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //crear una presscripcion
    @PostMapping("/create/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<String>> createPrescription(@PathVariable Integer clinicalAdvancesId, @RequestBody String prescription, @RequestHeader("Authorization") String token){
        prescriptionBl.createPrescription(clinicalAdvancesId, prescription);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, "Prescripcion creada correctamente", "Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, "Error al crear la prescripcion", "Error"));
        }
    }

    //ver todas las prescripciones de un avance clinico
    @GetMapping("/all/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<List<PrescriptionDTO>>> findAllByClinicalAdvancesId(@PathVariable Integer clinicalAdvancesId, @RequestHeader("Authorization") String token){
        List<PrescriptionDTO> prescriptionDtos = prescriptionBl.findAllByClinicalAdvancesId(clinicalAdvancesId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, prescriptionDtos, "Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
        }
    }

}
