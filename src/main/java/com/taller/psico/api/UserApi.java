package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.UserBl;
import com.taller.psico.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", methods = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.DELETE})

public class UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

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

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDTO<Void>> deleteUser(@PathVariable Integer userId, @RequestHeader("Authorization") String token) {
        logger.info("Request to delete user with ID: {}", userId);

        if (!authbl.validateToken(token)) {
            logger.error("Invalid token provided for deletion.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }

        try {
            boolean deleted = userBl.deleteUser(userId);
            if (deleted) {
                logger.info("User deleted successfully for ID: {}", userId);
                return ResponseEntity.ok(new ResponseDTO<>(200, null, "User deleted successfully."));
            } else {
                logger.warn("No user found with ID: {}", userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, null, "User not found."));
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting user with ID: {}. Error: {}", userId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while deleting the user."));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<UseriDTO>>> getAllUsers(@RequestHeader("Authorization") String token) {
        logger.info("Request received to fetch all users.");

        if (!authbl.validateToken(token)) {
            logger.error("Invalid token provided for fetching users.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }

        try {
            List<UseriDTO> users = userBl.getAllUsers();
            logger.info("Successfully fetched all users. Number of users: {}", users.size());
            return ResponseEntity.ok(new ResponseDTO<>(200, users, "Users fetched successfully."));
        } catch (Exception e) {
            logger.error("Error occurred while fetching all users: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while fetching the users."));
        }
    }

    @GetMapping("/byRole/{roleId}")
    public ResponseEntity<ResponseDTO<List<UseriDTO>>> findUsersByRole(@PathVariable Integer roleId, @RequestHeader("Authorization") String token) {
        if (!authbl.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }
        try {
            List<UseriDTO> users = userBl.findUsersByRoleId(roleId);
            if (users.isEmpty()) {
                return ResponseEntity.ok(new ResponseDTO<>(404, null, "No users found for the role"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, users, "Users found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while fetching the users"));
        }
    }

    @GetMapping("/peopleByRole/{roleId}")
    public ResponseEntity<ResponseDTO<List<PeopleDTO>>> findPeopleByRole(
            @PathVariable Integer roleId,
            @RequestHeader("Authorization") String token) {

        if (!authbl.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }
        try {
            List<PeopleDTO> people = userBl.findPeopleByRoleId(roleId);
            if (people.isEmpty()) {
                return ResponseEntity.ok(new ResponseDTO<>(404, null, "No people found for the role"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, people, "People found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while fetching the people"));
        }
    }

    //Actualizar User por Admin
    @PutMapping("/updateUser")
    public ResponseEntity<ResponseDTO<ActualizarUserPorAdminDto>> updateUser(@RequestBody ActualizarUserPorAdminDto actualizarUserPorAdminDto, @RequestHeader("Authorization") String token){
        userBl.updateUserByAdmin(actualizarUserPorAdminDto);
        try {
            if (!authbl.validateToken(token)) {
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, actualizarUserPorAdminDto, "Usuario actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al actualizar el usuario"));
        }
    }



}
