package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.PrescriptionBl;
import com.taller.psico.dto.PrescriptionDTO;
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
@RequestMapping("api/v1/prescription")
public class PrescriptionApi {
    private static final Logger logger = LoggerFactory.getLogger(PrescriptionApi.class);
    @Autowired
    private PrescriptionBl prescriptionBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //crear una presscripcion
    @PostMapping("/create/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<String>> createPrescription(@PathVariable Integer clinicalAdvancesId, @RequestBody String prescription, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        prescriptionBl.createPrescription(clinicalAdvancesId, prescription);
        try {
            logger.info("Creando prescripcion para avance clinico: {} desde la IP: {}", clinicalAdvancesId, clientIp);
            if (!authBl.validateToken(token)) {
                logger.error("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            logger.info("Prescripcion creada correctamente para avance clinico: {} desde la IP: {}", clinicalAdvancesId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, "Prescripcion creada correctamente", "Success"));
        } catch (Exception e) {
            logger.error("Error al crear prescripcion para avance clinico: {} desde la IP: {}. Error: {}", clinicalAdvancesId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, "Error al crear la prescripcion", "Error"));
        }
    }

    //ver todas las prescripciones de un avance clinico
    @GetMapping("/all/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<List<PrescriptionDTO>>> findAllByClinicalAdvancesId(@PathVariable Integer clinicalAdvancesId, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        List<PrescriptionDTO> prescriptionDtos = prescriptionBl.findAllByClinicalAdvancesId(clinicalAdvancesId);
        try {
            logger.info("Buscando prescripciones de avance clinico: {} desde la IP: {}", clinicalAdvancesId, clientIp);
            if (!authBl.validateToken(token)) {
                logger.error("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            logger.info("Prescripciones encontradas para avance clinico: {} desde la IP: {}", clinicalAdvancesId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, prescriptionDtos, "Success"));
        } catch (Exception e) {
            logger.error("Error al buscar prescripciones de avance clinico: {} desde la IP: {}. Error: {}", clinicalAdvancesId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
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
