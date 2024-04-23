package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.ClinicalAdvancesBl;
import com.taller.psico.dto.ClinicalAdvancesDTO;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clinical-advances")
@CrossOrigin(origins = "*", methods = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.DELETE})
public class ClinicalAdvancesApi {

    @Autowired
    private ClinicalAdvancesBl clinicalAdvancesBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear un avance clinico
    @PostMapping("/create/{treatmentId}")
    public ResponseEntity<ResponseDTO<ClinicalAdvancesDTO>> createClinicalAdvances(@RequestBody ClinicalAdvancesDTO clinicalAdvancesDto, @RequestHeader("Authorization") String token, @PathVariable Integer treatmentId){
        clinicalAdvancesBl.createClinicalAdvance(clinicalAdvancesDto, treatmentId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, clinicalAdvancesDto, "Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
        }
    }

    //Ver todos los avances clinicos de un tratamiento
    @GetMapping("/all/{treatmentId}")
    public ResponseEntity<ResponseDTO<List<ClinicalAdvancesDTO>>> findAllByTreatmentId(@PathVariable Integer treatmentId, @RequestHeader("Authorization") String token){
        List<ClinicalAdvancesDTO> clinicalAdvancesDto = clinicalAdvancesBl.findAllByTreatmentId(treatmentId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, clinicalAdvancesDto, "Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
        }
    }
}
