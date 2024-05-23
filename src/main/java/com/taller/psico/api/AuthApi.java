package com.taller.psico.api;

import com.taller.psico.bl.Authbl;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.ResponseDTO;
import com.taller.psico.dto.TokenDTO;
import com.taller.psico.dto.UseriDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AuthApi {

    private static final Logger logger = LoggerFactory.getLogger(AuthApi.class);

    @Autowired
    private Authbl authService;

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
}
