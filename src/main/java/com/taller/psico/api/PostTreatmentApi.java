package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.PostTreatmentBl;
import com.taller.psico.dto.PostTreatmentDTO;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post-treatment")
@CrossOrigin(origins = "*")
public class PostTreatmentApi {

    @Autowired
    private PostTreatmentBl postTreatmentBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear un post tratamiento
    @PostMapping("/create/{treatmentId}")
    public ResponseEntity<ResponseDTO<Integer>> createPostTreatment(@RequestBody PostTreatmentDTO postTreatmentDto, @PathVariable Integer treatmentId, @RequestHeader("Authorization") String token){
        Integer postTreatmentId = postTreatmentBl.createPostTreatment(postTreatmentDto, treatmentId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(401).body(new ResponseDTO<>(401, null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, postTreatmentId, "Post tratamiento creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al crear el post tratamiento"));
        }
    }

    //Mostrar todos los post tratamientos de un tratamiento
    @GetMapping("/all/{treatmentId}")
    public ResponseEntity<ResponseDTO<PostTreatmentDTO>> findAllByTreatmentId(@PathVariable Integer treatmentId, @RequestHeader("Authorization") String token){
        PostTreatmentDTO postTreatmentDtos = postTreatmentBl.findAllByTreatmentId(treatmentId);
        try {
            if (!authBl.validateToken(token)) {
                return ResponseEntity.status(401).body(new ResponseDTO<>(401, null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, postTreatmentDtos, "Post tratamientos encontrados"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar los post tratamientos"));
        }
    }


}
