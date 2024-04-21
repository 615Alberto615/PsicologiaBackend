package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.RolBl;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.RolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rol")
@CrossOrigin(origins = "*")
public class RolApi {

    @Autowired
    private RolBl rolBl;

    @Autowired
    private Authbl authbl;

    //Todos los roles
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<RolDTO>>> findAllRoles(@RequestHeader("Authorization") String token){
        List<RolDTO> rolesDto = rolBl.findAllRoles();
        try {
            if (!authbl.validateToken(token)) {
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, rolesDto, "Success"));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error"));
        }
    }

}
