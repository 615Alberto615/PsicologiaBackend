package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.ConclusionsBl;
import com.taller.psico.dto.ConclusionsDTO;
import com.taller.psico.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/conclusions")
public class ConclusionsApi {
    //loger
    private static final Logger logger = LoggerFactory.getLogger(ConclusionsApi.class);

    @Autowired
    private ConclusionsBl conclusionsBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear una conclusion
    @PostMapping("/create/{postTreatmentId}")
    public ResponseEntity<ResponseDTO<String>> createConclusions(
            @PathVariable Integer postTreatmentId,
            @RequestBody String conclusions,
            @RequestHeader("Authorization") String token,
            HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Creando conclusiones para postTreatmentId: {} desde la IP: {}", postTreatmentId, clientIp);

        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }

        try {
            conclusionsBl.createConclusions(postTreatmentId, conclusions);
            logger.info("Conclusiones creadas correctamente para postTreatmentId: {} desde la IP: {}", postTreatmentId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), null, "Conclusión creada correctamente"));
        } catch (Exception e) {
            logger.error("Error al crear conclusiones para postTreatmentId: {} desde la IP: {}. Error: {}", postTreatmentId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error al crear la conclusión"));
        }
    }

    //Ver todas las conclusiones de un post tratamiento
    @GetMapping("/all/{postTreatmentId}")
    public ResponseEntity<ResponseDTO<List<ConclusionsDTO>>> findAllByPostTreatmentId(
            @PathVariable Integer postTreatmentId,
            @RequestHeader("Authorization") String token,
            HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Buscando todas las conclusiones para postTreatmentId: {} desde la IP: {}", postTreatmentId, clientIp);

        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }

        try {
            List<ConclusionsDTO> conclusions = conclusionsBl.findAllByPostTreatmentId(postTreatmentId);
            logger.info("Conclusiones encontradas para postTreatmentId: {} desde la IP: {}", postTreatmentId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), conclusions, "Conclusiones encontradas"));
        } catch (Exception e) {
            logger.error("Error al buscar conclusiones para postTreatmentId: {} desde la IP: {}. Error: {}", postTreatmentId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error al buscar las conclusiones"));
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
