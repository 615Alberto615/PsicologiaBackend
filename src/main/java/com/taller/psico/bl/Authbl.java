package com.taller.psico.bl;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.TokenDTO;
import com.taller.psico.dto.UseriDTO;
import com.taller.psico.entity.Domains;
import com.taller.psico.entity.People;
import com.taller.psico.entity.Rol;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.RolRepository;
import com.taller.psico.repository.UseriRepository;
import com.taller.psico.repository.PeopleRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class Authbl {

    @Autowired
    private UseriRepository userRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private RolRepository rolRepository;

    private final String SECRET_KEY = "tupapisoyo";

    @Transactional
    public UseriDTO registerUser(UseriDTO userDto, PeopleDTO peopleDto) {
        Domains Gender = new Domains();
        Domains Ocuppation = new Domains();
        Domains Semester = new Domains();

        Gender.setDomainsId(peopleDto.getGenderId());
        Ocuppation.setDomainsId(peopleDto.getOccupationId());
        Semester.setDomainsId(peopleDto.getSemesterId());

        People people = new People();
        people.setName(peopleDto.getName());
        people.setFirstLastname(peopleDto.getFirstLastname());
        people.setSecondLastname(peopleDto.getSecondLastname());
        people.setEmail(peopleDto.getEmail());
        people.setAge(peopleDto.getAge());
        people.setCellphone(peopleDto.getCellphone());
        people.setAddress(peopleDto.getAddress());
        people.setCi(peopleDto.getCi());
        people.setStatus(peopleDto.getStatus());
        people.setGenderId(Gender);
        people.setOccupationId(Ocuppation);
        people.setSemesterId(Semester);
        people = peopleRepository.save(people);

        Rol rol = rolRepository.findById(userDto.getRolId()).orElseThrow(() -> new RuntimeException("Rol not found"));

        Useri user = new Useri();
        user.setUserName(userDto.getUserName());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        user.setStatus(userDto.getStatus());
        user.setPeopleId(people);
        user.setRolId(rol);
        user = userRepository.save(user);

        userDto.setUserId(user.getUserId());
        userDto.setPeopleId(people.getPeopleId());
        userDto.setRolId(rol.getRolId()); // Ensure you return the correct rolId if needed
        return userDto;
    }

    public TokenDTO loginUser(String username, String password) {
        Optional<Useri> userOpt = userRepository.findByUserName(username);
        TokenDTO tokenDTO = new TokenDTO();
        if (userOpt.isPresent()) {
            Useri user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                String token = generateToken(user);
                tokenDTO.setToken(token);
                tokenDTO.setId(user.getUserId());
                tokenDTO.setRol(user.getRolId().getRolId());
                return tokenDTO;
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    private String generateToken(Useri user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                //.setSubject(String.valueOf(user.getUserId()))
                //.setSubject(String.valueOf(user.getRolId().getRolId()))
                .claim("userId", user.getUserId())
                .claim("rolId", user.getRolId().getRolId())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 3600000)) // 1 hora de validez
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            // Aquí puedes agregar validaciones adicionales si lo deseas
            // Por ejemplo, verificar el emisor (issuer) del token
            // String issuer = claims.getIssuer();
            // if (!"www.softbabysi.com".equals(issuer)) {
            //     return false;
            // }

            return true;
        } catch (SignatureException e) {
            System.err.println("Token inválido: " + e.getMessage());
            return false;
        }
    }



}