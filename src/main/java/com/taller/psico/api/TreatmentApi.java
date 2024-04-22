package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.TreatmentBl;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.TreatmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/treatment")
@CrossOrigin(origins = "*")
public class TreatmentApi {

    @Autowired
    private TreatmentBl treatmentBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear un tratamiento
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<TreatmentDTO>> createTreatment(@RequestBody TreatmentDTO treatmentDTO, @RequestHeader("Authorization") String token){
        treatmentBl.createTreatment(treatmentDTO);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, treatmentDTO, "Tratamiento creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al crear el tratamiento"));
        }
    }

    //Ver todos los tratamientos de un usuario paciente
    @GetMapping("/all/{userId}")
    public ResponseEntity<ResponseDTO<List<TreatmentDTO>>> findAllByUserId(@PathVariable Integer userId, @RequestHeader("Authorization") String token){
        List<TreatmentDTO> treatmentDtos = treatmentBl.findAllByUserId(userId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, treatmentDtos, "Tratamientos encontrados"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar los tratamientos"));
        }
    }
}
