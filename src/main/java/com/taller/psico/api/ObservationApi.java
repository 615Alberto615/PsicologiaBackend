package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.ObservationBl;
import com.taller.psico.dto.ObservationDTO;
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
@RequestMapping("api/v1/observation")
public class ObservationApi {
    private static final Logger logger = LoggerFactory.getLogger(ObservationApi.class);
    @Autowired
    private ObservationBl observationBl;

    @Autowired
    private Authbl authBl;  // Authentication service

    //Crear una observacion de un avance clinico
    @PostMapping("/create/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<String>> createObservation(@PathVariable Integer clinicalAdvancesId,@RequestBody String observation, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        observationBl.createObservation(clinicalAdvancesId, observation);
        try {
            logger.info("Creando observacion para avance clinico: {} desde la IP: {}", clinicalAdvancesId, clientIp);
            if (!authBl.validateToken(token)) {
                logger.error("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, "Observacion creada correctamente", "Success"));
        } catch (Exception e) {
            logger.error("Error al crear observacion para avance clinico: {} desde la IP: {}. Error: {}", clinicalAdvancesId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, "Error al crear la observacion", "Error"));
        }
    }

    //Ver todas las observaciones de un avance clinico
    @GetMapping("/all/{clinicalAdvancesId}")
    public ResponseEntity<ResponseDTO<List<ObservationDTO>>> findAllByClinicalAdvancesId(@PathVariable Integer clinicalAdvancesId, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        List<ObservationDTO> observations = observationBl.findAllByClinicalAdvancesId(clinicalAdvancesId);
        try {
            logger.info("Buscando observaciones de avance clinico: {} desde la IP: {}", clinicalAdvancesId, clientIp);
            if (!authBl.validateToken(token)) {
                logger.error("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, observations, "Success"));
        } catch (Exception e) {
            logger.error("Error al buscar observaciones de avance clinico: {} desde la IP: {}. Error: {}", clinicalAdvancesId, clientIp, e.getMessage(), e);
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
