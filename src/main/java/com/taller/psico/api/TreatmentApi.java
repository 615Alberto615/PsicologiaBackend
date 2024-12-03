package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.TreatmentBl;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.TreatmentDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/treatment")
public class TreatmentApi {
    private static final Logger logger = LoggerFactory.getLogger(TreatmentApi.class);

    @Autowired
    private TreatmentBl treatmentBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear un tratamiento
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<TreatmentDTO>> createTreatment(@RequestBody TreatmentDTO treatmentDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        treatmentBl.createTreatment(treatmentDTO);
        logger.info("Creando tratamiento desde la IP: {}", clientIp);
        try {
            if (!authBl.validateToken(token)) {
                logger.warn("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }

            return ResponseEntity.ok(new ResponseDTO<>(200, treatmentDTO, "Tratamiento creado correctamente"));
        } catch (Exception e) {
            logger.error("Error al crear tratamiento desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al crear el tratamiento"));
        }
    }

    //Ver todos los tratamientos de un usuario paciente
    @GetMapping("/all/{userId}")
    public ResponseEntity<ResponseDTO<List<TreatmentDTO>>> findAllByUserId(@PathVariable Integer userId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        List<TreatmentDTO> treatmentDtos = treatmentBl.findAllByUserId(userId);
        logger.info("Buscando todos los tratamientos desde la IP: {}", clientIp);
        try {
            if (!authBl.validateToken(token)) {
                logger.warn("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, treatmentDtos, "Tratamientos encontrados"));
        } catch (Exception e) {
            logger.error("Error al buscar todos los tratamientos desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar los tratamientos"));
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
