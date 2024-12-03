package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.QuoteBl;
import com.taller.psico.dto.IsAvailableDTO;
import com.taller.psico.dto.QuotesDTO;
import com.taller.psico.dto.QuotesObtenerDTO;
import com.taller.psico.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/quote")
public class QuoteApi {
    private static final Logger logger = LoggerFactory.getLogger(QuoteApi.class);
    private final QuoteBl quoteBl;
    private final Authbl authBl;  // Authentication service

    @Autowired
    public QuoteApi(QuoteBl quoteBl, Authbl authBl) {
        this.quoteBl = quoteBl;
        this.authBl = authBl;  // Initialize the authentication service
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<QuotesDTO>> createQuote(@RequestBody QuotesDTO quoteDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente

        logger.info("Creando cita desde la IP: {}", clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            QuotesDTO result = quoteBl.createQuote(quoteDTO);
            if (result == null) {
                logger.error("No hay más espacios disponibles para esta hora.");
                return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "No more slots available for this time."));
            }
            logger.info("Cita creada exitosamente desde la IP: {}", clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, result, "Cita creada exitosamente."));
        } catch (Exception e) {
            logger.error("Error al crear cita desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al crear cita: " + e.getMessage()));
        }
    }

    //Decir un un availability esta disponible por fecha de quote y id de availability
    @PostMapping("/is-available")
    public ResponseEntity<ResponseDTO<Boolean>> isAvailable(@RequestBody IsAvailableDTO isAvailableDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Verificando disponibilidad desde la IP: {}", clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            boolean isAvailable = quoteBl.isAvailable(isAvailableDTO);
            return ResponseEntity.ok(new ResponseDTO<>(200, isAvailable, "Disponibilidad verificada exitosamente."));
        } catch (Exception e) {
            logger.error("Error al verificar disponibilidad desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al verificar disponibilidad para cita: " + e.getMessage()));
        }
    }


    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<QuotesObtenerDTO>>> getAllQuotes(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando todas las citas desde la IP: {}", clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Invalid token provided for fetching quotes.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesObtenerDTO> quotes = quoteBl.getAllQuotes();
            logger.info("Todas las citas recuperadas exitosamente desde la IP: {}", clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, quotes, "Todas las citas recuperadas exitosamente."));
        } catch (Exception e) {
            logger.error("Error al recuperar todas las citas desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar las citas: " + e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDTO<List<QuotesDTO>>> getUserQuotes(@PathVariable int userId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando citas para el usuario con ID: {}", userId);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar citas de usuario.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesDTO> userQuotes = quoteBl.getUserQuotes(userId);
            logger.info("Citas para el usuario con ID {} recuperadas exitosamente", userId);
            return ResponseEntity.ok(new ResponseDTO<>(200, userQuotes, "Citas del usuario recuperadas exitosamente."));
        } catch (Exception e) {
            logger.error("Error al recuperar citas para el usuario con ID {}: {}", userId, e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar las citas del usuario: " + e.getMessage()));
        }
    }

    //obtener todas la cita de un usuario con estado false
    @GetMapping("/user/historial/{userId}")
    public ResponseEntity<ResponseDTO<List<QuotesDTO>>> getUserQuotesHistorial(@PathVariable int userId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        //agregale la ip
        logger.info("Recuperando citas para el usuario con ID: {}, IP: {}", userId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar citas de usuario, IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesDTO> userQuotes = quoteBl.getUserQuotesHistorial(userId,1);
            logger.info("Citas para el usuario con ID {} recuperadas exitosamente, IP: {}", userId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, userQuotes, "Citas del usuario recuperadas exitosamente."));
        } catch (Exception e) {
            logger.error("Error al recuperar citas para el usuario con ID {}: {}, IP: {}", userId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar las citas del usuario: " + e.getMessage()));
        }
    }

    //obtener todas la cita de un usuario docente con estado false
    @GetMapping("/therapist/historial/{therapistId}")
    public ResponseEntity<ResponseDTO<List<QuotesDTO>>> getUserQuotesHistorialTherapist(@PathVariable int therapistId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando citas para el terapeuta con ID: {}, IP: {}", therapistId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar citas de terapeuta, IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesDTO> userQuotes = quoteBl.getUserQuotesHistorial(therapistId, 2);
            logger.info("Citas para el terapeuta con ID {} recuperadas exitosamente, IP: {}", therapistId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, userQuotes, "Citas del usuario recuperadas exitosamente."));
        } catch (Exception e) {
            logger.error("Error al recuperar citas para el terapeuta con ID {}: {}, IP: {}", therapistId, e.getMessage(), clientIp, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(500, null, "Error al recuperar las citas del usuario: " + e.getMessage()));
        }
    }



    @GetMapping("/{quotesId}")
    public ResponseEntity<ResponseDTO<QuotesDTO>> getQuoteById(@PathVariable int quotesId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando cita con ID: {} desde la IP: {}", quotesId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar una cita.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            QuotesDTO quote = quoteBl.getQuoteById(quotesId);
            if (quote != null) {
                logger.info("Cita con ID {} recuperada exitosamente desde la IP: {}", quotesId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(200, quote, "Cita recuperada exitosamente."));
            } else {
                logger.warn("No se encontró la cita con ID: {}, IP: {}", quotesId, clientIp);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al recuperar la cita con ID {}: {}, IP: {}", quotesId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar la cita: " + e.getMessage()));
        }
    }

    @PutMapping("/{quotesId}")
    public ResponseEntity<ResponseDTO<QuotesDTO>> updateQuote(@PathVariable int quotesId, @RequestBody QuotesDTO quoteDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente

        logger.info("Actualizando cita con ID: {} desde la IP: {}", quotesId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para actualizar una cita, IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            QuotesDTO updatedQuote = quoteBl.updateQuote(quotesId, quoteDTO);
            logger.info("Cita con ID {} actualizada exitosamente desde la IP: {}", quotesId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, updatedQuote, "Cita actualizada exitosamente."));
        } catch (Exception e) {
            logger.error("Error al actualizar la cita con ID {}: {}, IP: {}", quotesId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al actualizar la cita: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{quotesId}")
    public ResponseEntity<ResponseDTO<Void>> deleteQuote(@PathVariable int quotesId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Eliminando cita con ID: {} desde la IP: {}", quotesId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para eliminar una cita, IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            boolean deleted = quoteBl.deleteQuoteStatus(quotesId);
            if (deleted) {
                logger.info("Cita con ID {} eliminada exitosamente desde la IP: {}", quotesId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(200, null, "Cita eliminada exitosamente."));
            } else {
                logger.warn("No se encontró la cita con ID: {}, IP: {}", quotesId, clientIp);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al eliminar la cita con ID {}: {}, IP: {}", quotesId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al eliminar la cita: " + e.getMessage()));
        }
    }

    @GetMapping("/by-therapist/{therapistId}")
    public ResponseEntity<ResponseDTO<List<QuotesDTO>>> getQuotesByTherapistId(@PathVariable int therapistId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando citas para el terapeuta con ID: {} desde la IP: {}", therapistId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar citas de terapeuta.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesDTO> therapistQuotes = quoteBl.getQuotesByTherapistId(therapistId);
            if (!therapistQuotes.isEmpty()) {
                logger.info("Citas para el terapeuta con ID {} recuperadas exitosamente desde la IP: {}", therapistId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(200, therapistQuotes, "Citas del terapeuta recuperadas exitosamente."));
            } else {
                logger.warn("No se encontraron citas para el terapeuta con ID: {}, IP: {}", therapistId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(200, therapistQuotes, "No se encontraron citas para el terapeuta indicado."));
            }
        } catch (Exception e) {
            logger.error("Error al recuperar citas para el terapeuta con ID {}: {}, IP: {}", therapistId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar las citas del terapeuta: " + e.getMessage()));
        }
    }


    @GetMapping("/available-slots/{therapistId}")
    public ResponseEntity<Map<String, Integer>> getAvailableSlots(@PathVariable int therapistId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Calculando espacios disponibles para el terapeuta con ID: {} desde la IP: {}", therapistId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para calcular espacios disponibles.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            Map<String, Integer> slotsAvailability = quoteBl.countAvailableSlots(therapistId);
            if (slotsAvailability.isEmpty()) {
                logger.warn("No se encontraron espacios disponibles para el terapeuta con ID: {}, IP: {}", therapistId, clientIp);
                return ResponseEntity.notFound().build();
            }
            logger.info("Espacios disponibles para el terapeuta con ID {} calculados exitosamente desde la IP: {}", therapistId, clientIp);
            return ResponseEntity.ok(slotsAvailability);
        } catch (Exception e) {
            logger.error("Error al calcular espacios disponibles para el terapeuta con ID {}: {}, IP: {}", therapistId, e.getMessage(), clientIp, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/dashboard-counts")
    public ResponseEntity<Map<String, Object>> getDashboardCounts(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Obteniendo los conteos del dashboard desde la IP: {}", clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para obtener los conteos del dashboard.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            Map<String, Object> dashboardCounts = quoteBl.getDashboardCounts();
            return ResponseEntity.ok(dashboardCounts);
        } catch (Exception e) {
            logger.error("Error al obtener los conteos del dashboard desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener todas las cita hasta la fecha de hoy de un usuario
    @GetMapping("/user/{userId}/today")
    public ResponseEntity<ResponseDTO<List<QuotesObtenerDTO>>> getUserQuotesToday(@PathVariable int userId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando citas para el usuario con ID: {} hasta hoy, IP: {}", userId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar citas de usuario.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesObtenerDTO> userQuotes = quoteBl.getAllQuotesByDateToday(1,userId);
            logger.info("Citas para el usuario con ID {} recuperadas exitosamente, IP: {}", userId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, userQuotes, "Citas del usuario recuperadas exitosamente."));
        } catch (Exception e) {
            logger.error("Error al recuperar citas para el usuario con ID {}: {}, IP: {}", userId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar las citas del usuario: " + e.getMessage()));
        }
    }

    // Obtener todas las cita hasta la fecha de hoy de un terapeuta
    @GetMapping("/therapist/{therapistId}/today")
    public ResponseEntity<ResponseDTO<List<QuotesObtenerDTO>>> getTherapistQuotesToday(@PathVariable int therapistId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Recuperando citas para el terapeuta con ID: {} hasta hoy, IP: {}", therapistId, clientIp);
        if (!authBl.validateToken(token)) {
            logger.error("Token inválido proporcionado para recuperar citas de terapeuta, IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), null, "Unauthorized"));
        }
        try {
            List<QuotesObtenerDTO> therapistQuotes = quoteBl.getAllQuotesByDateToday(2,therapistId);
            logger.info("Citas para el terapeuta con ID {} recuperadas exitosamente, IP: {}", therapistId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, therapistQuotes, "Citas del terapeuta recuperadas exitosamente."));
        } catch (Exception e) {
            logger.error("Error al recuperar citas para el terapeuta con ID {}: {}, IP: {}", therapistId, e.getMessage(), clientIp, e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, null, "Error al recuperar las citas del terapeuta: " + e.getMessage()));
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
