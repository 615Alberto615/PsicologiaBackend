package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.TokenDTO;
import com.taller.psico.dto.UseriDTO;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.UseriRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {

    private static final Logger logger = LoggerFactory.getLogger(AuthApi.class);

    @Autowired
    private Authbl authService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UseriRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UseriDTO>> registerUser(@RequestBody RegistrationRequest registrationRequest, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        try {
            logger.info("Direccion IP: {},Intentando registrar usuario: {}", clientIp, registrationRequest.getUserDto().getUserName());
            UseriDTO result = authService.registerUser(registrationRequest.getUserDto(), registrationRequest.getPeopleDto());
            if (result == null) {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Ya existe un usuario con este correo"));
            }
            logger.info("User registered successfully: {}", registrationRequest.getUserDto().getUserName());
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), result, "Usuario registrado exitosamente!"));
        } catch (Exception e) {
            logger.error("Direccion IP: {},Error en el registro del usuario: {} - Error: {}", clientIp,registrationRequest.getUserDto().getUserName(), e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TokenDTO>> loginUser(@RequestBody UseriDTO userDto, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        try {
            logger.info("Direccion IP: {},Intentando registrar usuario: {}",clientIp, userDto.getUserName());
            TokenDTO token = authService.loginUser(userDto.getUserName(), userDto.getPassword());
            if (token == null) {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Usuario bloqueado termporalmente"));
            }
            //String token = authService.loginUser(userDto.getUserName(), userDto.getPassword());
            logger.info("Direccion IP: {},Inicio de sesión exitoso para la usuario: {}",clientIp, userDto.getUserName());
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), token, "Inicio de Sesion exitoso!"));
        } catch (RuntimeException e) {
            logger.error("Direccion IP: {},Error de inicio de sesión para la usuario: {} - Error: {}",clientIp, userDto.getUserName(), e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Contrasenia o usuario incorrectos"));
        }
    }

    static class RegistrationRequest {
        private UseriDTO userDto;
        private PeopleDTO peopleDto;
        public UseriDTO getUserDto() {
            return userDto;
        }
        public void setUserDto(UseriDTO userDto) {
            this.userDto = userDto;
        }
        public PeopleDTO getPeopleDto() {
            return peopleDto;
        }
        public void setPeopleDto(PeopleDTO peopleDto) {
            this.peopleDto = peopleDto;
        }
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        try {
            logger.info("Dirección IP: {}, Intentando recuperar contraseña para el correo: {}", clientIp, email);

            List<Useri> users = userRepository.findByEmail(email);
            if (users.isEmpty()) {
                logger.warn("Dirección IP: {}, No se encontró ningún usuario con el correo: {}", clientIp, email);
                return ResponseEntity.badRequest().body("Usuario no encontrado");
            }

            users.forEach(user -> {
                String token = UUID.randomUUID().toString();
                user.setResetPasswordToken(token);
                userRepository.save(user);

                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getPeopleId().getEmail());
                message.setSubject("Restablece tu contraseña");
                message.setText("Usa el siguiente token para restablecer tu contraseña: " + token);
                emailSender.send(message);

                logger.info("Dirección IP: {}, Token enviado al correo: {}", clientIp, user.getPeopleId().getEmail());
            });

            logger.info("Dirección IP: {}, Tokens de restablecimiento de contraseña enviados exitosamente", clientIp);
            return ResponseEntity.ok("Tokens de restablecimiento enviados exitosamente a todas las cuentas asociadas");
        } catch (Exception e) {
            logger.error("Dirección IP: {}, Error al enviar tokens de restablecimiento de contraseña: {}", clientIp, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar los tokens de restablecimiento");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        String token = resetPasswordRequest.getToken();
        String newPassword = resetPasswordRequest.getNewPassword();

        try {
            logger.info("Dirección IP: {}, Intentando restablecer la contraseña con el token: {}", clientIp, token);

            Optional<Useri> userOpt = userRepository.findByResetPasswordToken(token);
            if (!userOpt.isPresent()) {
                logger.warn("Dirección IP: {}, Token inválido: {}", clientIp, token);
                return ResponseEntity.badRequest().body("Token inválido");
            }

            Useri user = userOpt.get();
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashedPassword);  // Asegura consistencia en el hashing
            user.setResetPasswordToken(null);  // Limpia el token después de usar
            userRepository.save(user);

            logger.info("Dirección IP: {}, Contraseña restablecida exitosamente para el usuario con correo: {}", clientIp, user.getPeopleId().getEmail());
            return ResponseEntity.ok(new ResponseDTO2<>(HttpStatus.OK.value(), "La contraseña ha sido restablecida exitosamente"));
        } catch (Exception e) {
            logger.error("Dirección IP: {}, Error al restablecer la contraseña: {}", clientIp, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al restablecer la contraseña");
        }
    }


    static class ResetPasswordRequest {
        private String token;
        private String newPassword;

        // Getters and setters
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
    static class ResponseDTO2<T> {
        private int status;
        private T data;

        public ResponseDTO2(int status, T data) {
            this.status = status;
            this.data = data;
        }

        // Getters and setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
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
