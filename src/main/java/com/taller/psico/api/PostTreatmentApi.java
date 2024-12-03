package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.PostTreatmentBl;
import com.taller.psico.dto.PostTreatmentDTO;
import com.taller.psico.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post-treatment")
public class PostTreatmentApi {
    private static final Logger logger = LoggerFactory.getLogger(PostTreatmentApi.class);

    @Autowired
    private PostTreatmentBl postTreatmentBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear un post tratamiento
    @PostMapping("/create/{treatmentId}")
    public ResponseEntity<ResponseDTO<Integer>> createPostTreatment(@RequestBody PostTreatmentDTO postTreatmentDto, @PathVariable Integer treatmentId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        Integer postTreatmentId = postTreatmentBl.createPostTreatment(postTreatmentDto, treatmentId);
        try {
            logger.info("Creando post tratamiento para tratamiento: {} desde la IP: {}", treatmentId, clientIp);
            if (!authBl.validateToken(token)) {
                logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(401).body(new ResponseDTO<>(401, null, "Unauthorized"));
            }
            logger.info("Post tratamiento creado correctamente para tratamiento: {} desde la IP: {}", treatmentId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, postTreatmentId, "Post tratamiento creado correctamente"));
        } catch (Exception e) {
            logger.error("Error al crear post tratamiento para tratamiento: {} desde la IP: {}. Error: {}", treatmentId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al crear el post tratamiento"));
        }
    }

    //Mostrar todos los post tratamientos de un tratamiento
    @GetMapping("/all/{treatmentId}")
    public ResponseEntity<ResponseDTO<PostTreatmentDTO>> findAllByTreatmentId(@PathVariable Integer treatmentId, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        PostTreatmentDTO postTreatmentDtos = postTreatmentBl.findAllByTreatmentId(treatmentId);
        try {
            logger.info("Buscando post tratamientos de tratamiento: {} desde la IP: {}", treatmentId, clientIp);
            if (!authBl.validateToken(token)) {
                logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(401).body(new ResponseDTO<>(401, null, "Unauthorized"));
            }
            logger.info("Post tratamientos encontrados para tratamiento: {} desde la IP: {}", treatmentId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, postTreatmentDtos, "Post tratamientos encontrados"));
        } catch (Exception e) {
            logger.error("Error al buscar post tratamientos de tratamiento: {} desde la IP: {}. Error: {}", treatmentId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar los post tratamientos"));
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For"); // Si hay un proxy/reverse proxy como nginx
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
