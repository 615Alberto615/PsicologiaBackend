package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.RolBl;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.RolDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rol")
public class RolApi {
    private static final Logger logger = LoggerFactory.getLogger(RolApi.class);

    @Autowired
    private RolBl rolBl;

    @Autowired
    private Authbl authbl;

    //Todos los roles
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<RolDTO>>> findAllRoles(@RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        List<RolDTO> rolesDto = rolBl.findAllRoles();
        logger.info("Buscando todos los roles desde la IP: {}", clientIp);
        try {
            if (!authbl.validateToken(token)) {
                logger.error("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, rolesDto, "Success"));
        }catch (Exception e){
            logger.error("Error al buscar todos los roles desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
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
