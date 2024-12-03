package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.DomainsBl;
import com.taller.psico.dto.DomainsDTO;
import com.taller.psico.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/domains")
public class DomainsApi {
    private static final Logger logger = LoggerFactory.getLogger(DomainsApi.class);

    @Autowired
    private DomainsBl domainsBl;

    @Autowired
    private Authbl authbl;

    //Todos los dominios de un tipo
    @GetMapping("/{type}")
    public ResponseEntity<ResponseDTO<List<DomainsDTO>>> findByType(@PathVariable String type, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request);
        List<DomainsDTO> domainsDto = domainsBl.findByType(type);
        try {
            logger.info("Buscando dominios de tipo: {} desde la IP: {}", type, clientIp);
            if (!authbl.validateToken(token)) {
                logger.error("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
                //log

            }
            return ResponseEntity.ok(new ResponseDTO<>(200, domainsDto, "Success"));
        }catch (Exception e){
            logger.error("Error al buscar dominios de tipo: {} desde la IP: {}. Error: {}", type, clientIp, e.getMessage(), e);
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
