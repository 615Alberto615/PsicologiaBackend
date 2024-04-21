package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.UserBl;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.UseriDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", methods = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.DELETE})

public class UserApi {

    @Autowired
    private UserBl userBl;

    @Autowired
    private Authbl authbl;

    //Mostrar usuario por id
    @GetMapping("/find/{userId}")
    public ResponseEntity<ResponseDTO<UseriDTO>> findByIdUser(@PathVariable Integer userId, @RequestHeader("Authorization") String token){
        UseriDTO useriDTO = userBl.findByIdUser(userId);
        try {
            if (!authbl.validateToken(token)) {
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, useriDTO, "Usuario encontrado"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar el usuario"));
        }
    }

    //Mostrar persona por id de usuario
    @GetMapping("/findPerson/{userId}")
    public ResponseEntity<ResponseDTO<PeopleDTO>> findByIdPerson(@PathVariable Integer userId, @RequestHeader("Authorization") String token){
        PeopleDTO peopleDTO = userBl.findByIdPerson(userId);
        try {
            if (!authbl.validateToken(token)) {
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, peopleDTO, "Persona encontrada"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar la persona"));
        }
    }

    //Actualizar persona por id de usuario
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO<PeopleDTO>> updatePerson(@PathVariable Integer userId, @RequestBody PeopleDTO peopleDTO, @RequestHeader("Authorization") String token){
        userBl.updateUser(peopleDTO, userId);
        try {
            if (!authbl.validateToken(token)) {
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, peopleDTO, "Usuario actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al actualizar el usuario"));
        }
    }



}
