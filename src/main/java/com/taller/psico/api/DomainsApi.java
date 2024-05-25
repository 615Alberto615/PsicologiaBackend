package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.DomainsBl;
import com.taller.psico.dto.DomainsDTO;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/domains")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class DomainsApi {

    @Autowired
    private DomainsBl domainsBl;

    @Autowired
    private Authbl authbl;

    //Todos los dominios de un tipo
    @GetMapping("/{type}")
    public ResponseEntity<ResponseDTO<List<DomainsDTO>>> findByType(@PathVariable String type, @RequestHeader("Authorization") String token){
        List<DomainsDTO> domainsDto = domainsBl.findByType(type);
        try {
            if (!authbl.validateToken(token)) {
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, domainsDto, "Success"));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
        }
    }

}
