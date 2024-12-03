package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.ClinicalAdvancesBl;
import com.taller.psico.dto.ClinicalAdvancesDTO;
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
@RequestMapping("api/v1/clinical-advances")
public class ClinicalAdvancesApi {
    private static final Logger logger = LoggerFactory.getLogger(ClinicalAdvancesApi.class);

    @Autowired
    private ClinicalAdvancesBl clinicalAdvancesBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear un avance clinico
    @PostMapping("/create/{treatmentId}")
    public ResponseEntity<ResponseDTO<ClinicalAdvancesDTO>> createClinicalAdvances(
            @RequestBody ClinicalAdvancesDTO clinicalAdvancesDto,
            @RequestHeader("Authorization") String token,
            @PathVariable Integer treatmentId, HttpServletRequest request) {
        String clientIp = getClientIp(request);

        logger.info("Intentando crear avances clínicos para el tratamiento con ID: {}. Dirección IP: {}", treatmentId, clientIp);

        try {
            if (!authBl.validateToken(token)) {
                logger.error("Token inválido proporcionado. Dirección IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
            }

            clinicalAdvancesBl.createClinicalAdvance(clinicalAdvancesDto, treatmentId);
            logger.info("Avances clínicos creados exitosamente para el tratamiento con ID: {}. Dirección IP: {}", treatmentId, clientIp);

            return ResponseEntity.ok(new ResponseDTO<>(200, clinicalAdvancesDto, "Éxito"));
        } catch (Exception e) {
            logger.error("Error al crear avances clínicos para el tratamiento con ID: {}. Dirección IP: {}. Error: {}", treatmentId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "Error al crear avances clínicos"));
        }
    }

    // Ver todos los avances clínicos de un tratamiento
    @GetMapping("/all/{treatmentId}")
    public ResponseEntity<ResponseDTO<List<ClinicalAdvancesDTO>>> findAllByTreatmentId(
            @PathVariable Integer treatmentId,
            @RequestHeader("Authorization") String token,
            HttpServletRequest request) {
        String clientIp = getClientIp(request);

        logger.info("Consultando todos los avances clínicos para el tratamiento con ID: {}. Dirección IP: {}", treatmentId, clientIp);

        try {
            if (!authBl.validateToken(token)) {
                logger.error("Token inválido proporcionado. Dirección IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
            }

            List<ClinicalAdvancesDTO> clinicalAdvancesDto = clinicalAdvancesBl.findAllByTreatmentId(treatmentId);
            logger.info("Avances clínicos recuperados exitosamente para el tratamiento con ID: {}. Dirección IP: {}", treatmentId, clientIp);

            return ResponseEntity.ok(new ResponseDTO<>(200, clinicalAdvancesDto, "Éxito"));
        } catch (Exception e) {
            logger.error("Error al recuperar los avances clínicos para el tratamiento con ID: {}. Dirección IP: {}. Error: {}", treatmentId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "Error al recuperar los avances clínicos"));
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
