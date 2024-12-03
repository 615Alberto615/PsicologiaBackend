package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.AvailabilityBl;
import com.taller.psico.dto.AvailabilityDTO;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.UserAvailabilitiesDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/availability")
public class AvailabilityApi {
    private static final Logger logger = LoggerFactory.getLogger(AvailabilityApi.class);
    private final AvailabilityBl availabilityBl;
    private final Authbl authBl;

    @Autowired
    public AvailabilityApi(AvailabilityBl availabilityBl, Authbl authBl) {
        this.availabilityBl = availabilityBl;
        this.authBl = authBl;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<AvailabilityDTO>> createAvailability(@RequestBody AvailabilityDTO availabilityDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        logger.info("Solicitud recibida desde la IP: {}", clientIp);

        if (availabilityDTO.getUser() == null || availabilityDTO.getUser().getUserId() == null) {
            logger.warn("Falta el ID del usuario en la solicitud. IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Falta el ID del usuario"));
        }

        logger.info("Creando disponibilidad para el ID de usuario: {} desde la IP: {}", availabilityDTO.getUser().getUserId(), clientIp);

        if (!authBl.validateToken(token)) {
            logger.warn("Intento de acceso no autorizado con token inválido desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }

        try {
            AvailabilityDTO createdAvailability = availabilityBl.createAvailability(availabilityDTO);
            logger.info("Disponibilidad creada exitosamente con ID: {} desde la IP: {}", createdAvailability.getAvailabilityId(), clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), createdAvailability, "Disponibilidad creada exitosamente"));
        } catch (Exception e) {
            logger.error("Error al crear la disponibilidad desde la IP: {}. Error: {}", clientIp, e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Error al crear la disponibilidad"));
        }
    }

    @PutMapping("/update/{availabilityId}")
    public ResponseEntity<ResponseDTO<AvailabilityDTO>> updateAvailability(@PathVariable int availabilityId, @RequestBody AvailabilityDTO availabilityDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        logger.info("Intentando actualizar disponibilidad con ID: {} desde la IP: {}", availabilityId, clientIp);

        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }

        try {
            AvailabilityDTO updatedAvailability = availabilityBl.updateAvailability(availabilityId, availabilityDTO);
            if (updatedAvailability != null) {
                logger.info("Disponibilidad actualizada exitosamente para el ID: {} desde la IP: {}", updatedAvailability.getAvailabilityId(), clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), updatedAvailability, "Disponibilidad actualizada exitosamente"));
            } else {
                logger.warn("No se encontró disponibilidad con el ID: {} desde la IP: {}", availabilityId, clientIp);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al actualizar disponibilidad con ID: {} desde la IP: {}. Error: {}", availabilityId, clientIp, e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Error al actualizar la disponibilidad"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<AvailabilityDTO>>> getAllAvailabilities(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        logger.info("Obteniendo todas las disponibilidades desde la IP: {}", clientIp);

        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }

        List<AvailabilityDTO> allAvailabilities = availabilityBl.getAllAvailabilities();
        logger.info("Disponibilidades obtenidas exitosamente desde la IP: {}", clientIp);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), allAvailabilities, "Todas las disponibilidades obtenidas exitosamente."));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO<List<AvailabilityDTO>>> getAvailabilitiesByUserId(@PathVariable int userId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        logger.info("Obteniendo disponibilidades para el ID de usuario: {} desde la IP: {}", userId, clientIp);

        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }

        List<AvailabilityDTO> availabilities = availabilityBl.getAvailabilitiesByUserId(userId);
        logger.info("Disponibilidades obtenidas exitosamente para el ID de usuario: {} desde la IP: {}", userId, clientIp);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), availabilities, "Disponibilidades obtenidas exitosamente para el usuario " + userId));
    }

    @GetMapping("/{availabilityId}")
    public ResponseEntity<ResponseDTO<AvailabilityDTO>> getAvailabilityById(@PathVariable int availabilityId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        logger.info("Solicitando disponibilidad con ID: {} desde la IP: {}", availabilityId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }
        try {
            AvailabilityDTO availability = availabilityBl.getAvailabilityById(availabilityId);
            if (availability != null) {
                logger.info("Disponibilidad obtenida correctamente para el ID: {} desde la IP: {}", availabilityId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), availability, "Disponibilidad obtenida exitosamente para el ID: " + availabilityId));
            } else {
                logger.warn("No se encontró disponibilidad para el ID: {} desde la IP: {}", availabilityId, clientIp);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Ocurrió un error al obtener disponibilidad para el ID: {} desde la IP: {}. Error: {}", availabilityId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Ocurrió un error al obtener la disponibilidad para el ID: " + availabilityId));
        }
    }

    @DeleteMapping("/delete/{availabilityId}")
    public ResponseEntity<ResponseDTO<Void>> deleteAvailabilityLogically(@PathVariable int availabilityId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        logger.info("Intentando eliminar lógicamente disponibilidad con ID: {} desde la IP: {}", availabilityId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }
        try {
            availabilityBl.deleteAvailabilityLogically(availabilityId);
            logger.info("Disponibilidad eliminada lógicamente para el ID: {} desde la IP: {}", availabilityId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), null, "Disponibilidad eliminada lógicamente con éxito."));
        } catch (Exception e) {
            logger.error("Ocurrió un error al eliminar lógicamente la disponibilidad con ID: {} desde la IP: {}. Error: {}", availabilityId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Ocurrió un error al eliminar la disponibilidad."));
        }
    }

    @GetMapping("/grouped-by-user")
    public ResponseEntity<ResponseDTO<List<UserAvailabilitiesDTO>>> getGroupedByUser(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Solicitando disponibilidades agrupadas por usuario desde la IP: {}", clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }
        List<UserAvailabilitiesDTO> groupedAvailabilities = availabilityBl.getAllGroupedByUser();
        logger.info("Disponibilidades agrupadas por usuario obtenidas exitosamente desde la IP: {}", clientIp);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), groupedAvailabilities, "Disponibilidades agrupadas por usuario obtenidas exitosamente."));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO<List<AvailabilityDTO>>> getActiveAvailabilities(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request);// Obtener la IP del cliente
        logger.info("Solicitando disponibilidades activas desde la IP: {}", clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }
        List<AvailabilityDTO> activeAvailabilities = availabilityBl.getActiveAvailabilities();
        logger.info("Disponibilidades activas obtenidas exitosamente desde la IP: {}", clientIp);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), activeAvailabilities, "Disponibilidades activas obtenidas exitosamente."));
    }

    @GetMapping("/disponibilidad/{availabilityId}")
    public ResponseEntity<ResponseDTO<Boolean>> getAvailabilityById(@PathVariable Integer availabilityId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Verificando disponibilidad con ID: {} desde la IP: {}", availabilityId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "No autorizado"));
        }
        try {
            Boolean availability = availabilityBl.isAvailabilityAvailable(availabilityId);
            if (availability != null) {
                logger.info("Disponibilidad verificada exitosamente para el ID: {} desde la IP: {}", availabilityId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), availability, "Disponibilidad verificada exitosamente para el ID: " + availabilityId));
            } else {
                logger.warn("No se encontró disponibilidad para el ID: {} desde la IP: {}", availabilityId, clientIp);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al verificar disponibilidad para el ID: {} desde la IP: {}. Error: {}", availabilityId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Ocurrió un error al verificar la disponibilidad para el ID: " + availabilityId));
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
