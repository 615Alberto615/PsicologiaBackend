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
    public ResponseEntity<ResponseDTO<UseriDTO>> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            logger.info("Attempting to register user: {}", registrationRequest.getUserDto().getUserName());
            UseriDTO result = authService.registerUser(registrationRequest.getUserDto(), registrationRequest.getPeopleDto());
            logger.info("User registered successfully: {}", registrationRequest.getUserDto().getUserName());
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), result, "User registered successfully"));
        } catch (Exception e) {
            logger.error("Registration failed for user: {} - Error: {}", registrationRequest.getUserDto().getUserName(), e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TokenDTO>> loginUser(@RequestBody UseriDTO userDto) {
        try {
            logger.info("Attempting to login user: {}", userDto.getUserName());
            TokenDTO token = authService.loginUser(userDto.getUserName(), userDto.getPassword());
            //String token = authService.loginUser(userDto.getUserName(), userDto.getPassword());
            logger.info("Login successful for user: {}", userDto.getUserName());
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.value(), token, "Login successful"));
        } catch (RuntimeException e) {
            logger.error("Login failed for user: {} - Error: {}", userDto.getUserName(), e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Invalid credentials"));
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
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        List<Useri> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        users.forEach(user -> {
            String token = UUID.randomUUID().toString();
            user.setResetPasswordToken(token);
            userRepository.save(user);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getPeopleId().getEmail());
            message.setSubject("Reset Your Password");
            message.setText("Use the following token to reset your password: " + token);
            emailSender.send(message);
        });

        return ResponseEntity.ok("Reset password tokens sent successfully to all associated accounts");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        String token = resetPasswordRequest.getToken();
        String newPassword = resetPasswordRequest.getNewPassword();

        Optional<Useri> userOpt = userRepository.findByResetPasswordToken(token);
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Useri user = userOpt.get();
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);  // Asegura consistencia en el hashing
        user.setResetPasswordToken(null);  // Limpia el token después de usar
        userRepository.save(user);

        return ResponseEntity.ok(new ResponseDTO2<>(HttpStatus.OK.value(), "La contraseña ha sido restablecida exitosamente"));
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

}
