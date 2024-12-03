package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.bl.UserBl;
import com.taller.psico.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

public class UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserBl userBl;

    @Autowired
    private Authbl authbl;

    //Mostrar usuario por id
    @GetMapping("/find/{userId}")
    public ResponseEntity<ResponseDTO<UseriObtenerDTO>> findByIdUser(@PathVariable Integer userId, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        UseriObtenerDTO useriDTO = userBl.findByIdUser(userId);
        logger.info("Buscando usuario por ID: {} desde la IP: {}", userId, clientIp);
        try {
            if (!authbl.validateToken(token)) {
                logger.warn("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, useriDTO, "Usuario encontrado"));
        } catch (Exception e) {
            logger.error("Error al buscar el usuario por ID: {} desde la IP: {}. Error: {}", userId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar el usuario"));
        }
    }

    //Mostrar persona por id de usuario
    @GetMapping("/findPerson/{userId}")
    public ResponseEntity<ResponseDTO<PeopleObtenerDTO>> findByIdPerson(@PathVariable Integer userId, @RequestHeader("Authorization") String token, HttpServletRequest request){
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Buscando persona por ID de usuario: {} desde la IP: {}", userId, clientIp);
        PeopleObtenerDTO peopleDTO = userBl.findByIdPerson(userId);
        try {
            if (!authbl.validateToken(token)) {
                logger.warn("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, peopleDTO, "Persona encontrada"));
        } catch (Exception e) {
            logger.error("Error al buscar la persona por ID de usuario: {} desde la IP: {}. Error: {}", userId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al buscar la persona"));
        }
    }

    //Actualizar persona por id de usuario
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO<PeopleDTO>> updatePerson(@PathVariable Integer userId, @RequestBody PeopleDTO peopleDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Actualizando persona por ID de usuario: {} desde la IP: {}", userId, clientIp);
        userBl.updateUser(peopleDTO, userId);
        try {
            if (!authbl.validateToken(token)) {
                logger.warn("Token invalido proporcionado desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(401, null, "Token invalido"));
            }
            return ResponseEntity.ok(new ResponseDTO<>(200, peopleDTO, "Usuario actualizado correctamente"));
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario por ID: {} desde la IP: {}. Error: {}", userId, clientIp, e.getMessage(), e);
            return ResponseEntity.ok(new ResponseDTO<>(500, null, "Error al actualizar el usuario"));
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDTO<Void>> deleteUser(@PathVariable Integer userId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Solicitud recibida para eliminar usuario con ID: {} desde la IP: {}", userId, clientIp);

        if (!authbl.validateToken(token)) {
            logger.warn("Token invalido proporcionado para eliminar usuario, desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }

        try {
            boolean deleted = userBl.deleteUser(userId);
            if (deleted) {
                logger.info("Usuario eliminado con ID: {} desde la IP: {}", userId, clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(200, null, "User deleted successfully."));
            } else {
                logger.warn("Usuario no encontrado con ID: {} desde la IP: {}", userId, clientIp);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(404, null, "User not found."));
            }
        } catch (Exception e) {
            logger.error("Error al eliminar el usuario con ID: {} desde la IP: {}. Error: {}", userId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while deleting the user."));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<UseriObtenerDTO>>> getAllUsers(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        //en espaniol y con la Ip
        logger.info("Solicitud recibida para obtener todos los usuarios desde la IP: {}", clientIp);
        if (!authbl.validateToken(token)) {
            logger.warn("Token invalido proporcionado para obtener todos los usuarios, desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }

        try {
            List<UseriObtenerDTO> users = userBl.getAllUsers();
            logger.info("Usuarios obtenidos correctamente desde la IP: {}", clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, users, "Users fetched successfully."));
        } catch (Exception e) {
            logger.error("Error al obtener los usuarios desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while fetching the users."));
        }
    }

    @GetMapping("/byRole/{roleId}")
    public ResponseEntity<ResponseDTO<List<UseriDTO>>> findUsersByRole(@PathVariable Integer roleId, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Solicitud recibida para obtener usuarios por rol desde la IP: {}", clientIp);
        if (!authbl.validateToken(token)) {
            logger.warn("Token invalido proporcionado para obtener usuarios por rol, desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }
        try {
            List<UseriDTO> users = userBl.findUsersByRoleId(roleId);
            if (users.isEmpty()) {
                logger.warn("No se encontraron usuarios para el rol desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(404, null, "No users found for the role"));
            }
            logger.info("Usuarios encontrados correctamente para el rol desde la IP: {}", clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, users, "Users found"));
        } catch (Exception e) {
            logger.error("Error al obtener los usuarios por rol desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while fetching the users"));
        }
    }

    @GetMapping("/peopleByRole/{roleId}")
    public ResponseEntity<ResponseDTO<List<PeopleObtenerDTO>>> findPeopleByRole(
            @PathVariable Integer roleId,
            @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Solicitud recibida para obtener personas por rol desde la IP: {}", clientIp);
        if (!authbl.validateToken(token)) {
            logger.warn("Token invalido proporcionado para obtener personas por rol, desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }
        try {

            List<PeopleObtenerDTO> people = userBl.findPeopleByRoleId(roleId);
            if (people.isEmpty()) {
                logger.warn("No se encontraron personas para el rol desde la IP: {}", clientIp);
                return ResponseEntity.ok(new ResponseDTO<>(404, null, "No people found for the role"));
            }
            logger.info("Personas encontradas correctamente para el rol desde la IP: {}", clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, people, "People found"));
        } catch (Exception e) {
            logger.error("Error al obtener las personas por rol desde la IP: {}. Error: {}", clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO<>(500, null, "An error occurred while fetching the people"));
        }
    }

    //Actualizar User por Admin
    @PutMapping("/changeRole/{userId}")
    public ResponseEntity<ResponseDTO<Void>> changeUserRole(@PathVariable Integer userId, @RequestBody UpdateRoleDTO updateRoleDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        logger.info("Solicitud recibida para actualizar el rol del usuario con ID: {} desde la IP: {}", userId, clientIp);
        if (!authbl.validateToken(token)) {
            logger.warn("Token invalido proporcionado para actualizar el rol del usuario, desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }
        try {
            userBl.updateUserRole(userId, updateRoleDTO.getRolId());
            logger.info("Rol actualizado correctamente para el usuario con ID: {} desde la IP: {}", userId, clientIp);
            return ResponseEntity.ok(new ResponseDTO<>(200, null, "Rol actualizado correctamente"));
        } catch (Exception e) {
            logger.error("Error al actualizar el rol del usuario con ID: {} desde la IP: {}. Error: {}", userId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(500, null, "Error al actualizar el rol: " + e.getMessage()));
        }
    }

    // cambiar status
    @PutMapping("/changeStatus/{userId}")
    public ResponseEntity<ResponseDTO<Void>> changeUserStatus(@PathVariable Integer userId, @RequestBody UpdateStatusDTO updateStatusDTO, @RequestHeader("Authorization") String token, HttpServletRequest request) {
        String clientIp = getClientIp(request); // Obtener la IP del cliente
        if (!authbl.validateToken(token)) {
            logger.warn("Token invalido proporcionado para actualizar el estado del usuario, desde la IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO<>(401, null, "Unauthorized"));
        }
        try {
            logger.info("Solicitud recibida para actualizar el estado del usuario con ID: {} desde la IP: {}", userId, clientIp);
            userBl.updateUserStatus(userId, updateStatusDTO.getStatus());
            return ResponseEntity.ok(new ResponseDTO<>(200, null, "Estado actualizado correctamente"));
        } catch (Exception e) {
            logger.error("Error al actualizar el estado del usuario con ID: {} desde la IP: {}. Error: {}", userId, clientIp, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(500, null, "Error al actualizar el estado: " + e.getMessage()));
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
