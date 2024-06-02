package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.ConclusionsBl;
import com.taller.psico.dto.ConclusionsDTO;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/conclusions")
public class ConclusionsApi {

    @Autowired
    private ConclusionsBl conclusionsBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear una conclusion
    @PostMapping("/create/{postTreatmentId}")
    public ResponseEntity<ResponseDTO<String>> createConclusions(@PathVariable Integer postTreatmentId,@RequestBody String conclusions, @RequestHeader("Authorization") String token){
        conclusionsBl.createConclusions(postTreatmentId, conclusions);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(401).body(new ResponseDTO<>(401, null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, null, "Conclusion creada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al crear la conclusion"));
        }
    }

    //Ver todas las conclusiones de un post tratamiento
    @GetMapping("/all/{postTreatmentId}")
    public ResponseEntity<ResponseDTO<List<ConclusionsDTO>>> findAllByPostTreatmentId(@PathVariable Integer postTreatmentId, @RequestHeader("Authorization") String token){
        List<ConclusionsDTO> conclusions = conclusionsBl.findAllByPostTreatmentId(postTreatmentId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(401).body(new ResponseDTO<>(401, null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, conclusions, "Conclusiones encontradas"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar las conclusiones"));
        }
    }
}
