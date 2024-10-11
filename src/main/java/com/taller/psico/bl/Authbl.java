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

import java.time.LocalDateTime;
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

    @Autowired
    private EmailSenderBl emailSenderBl;

    private final String SECRET_KEY = "tupapisoyo";

    @Transactional
    public UseriDTO registerUser(UseriDTO userDto, PeopleDTO peopleDto) {
        // Configuración de dominios
        Domains Gender = new Domains();
        Domains Occupation = new Domains();
        Domains Semester = new Domains();

        Gender.setDomainsId(peopleDto.getGenderId());
        Occupation.setDomainsId(peopleDto.getOccupationId());
        Semester.setDomainsId(peopleDto.getSemesterId());

        // Creación de la entidad People
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
        people.setOccupationId(Occupation);
        people.setSemesterId(Semester);
        people = peopleRepository.save(people);

        // Búsqueda del Rol
        Rol rol = rolRepository.findById(userDto.getRolId()).orElseThrow(() -> new RuntimeException("Rol not found"));

        // Creación de la entidad Useri
        Useri user = new Useri();
        user.setUserName(userDto.getUserName());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        user.setStatus(userDto.getStatus());
        user.setPeopleId(people);
        user.setRolId(rol);
        user = userRepository.save(user);

        // Configuración del UseriDTO para devolver
        userDto.setUserId(user.getUserId());
        userDto.setPeople(convertToPeopleDTO(people));
        userDto.setRolId(rol.getRolId());
        return userDto;
    }

    public static PeopleDTO convertToPeopleDTO(People people) {
        if (people == null) {
            return null;
        }
        PeopleDTO dto = new PeopleDTO();
        dto.setPeopleId(people.getPeopleId());
        dto.setName(people.getName());
        dto.setFirstLastname(people.getFirstLastname());
        dto.setSecondLastname(people.getSecondLastname());
        dto.setEmail(people.getEmail());
        dto.setAge(people.getAge());
        dto.setCellphone(people.getCellphone());
        dto.setAddress(people.getAddress());
        dto.setCi(people.getCi());
        dto.setStatus(people.getStatus());
        return dto;
    }


    public TokenDTO loginUser(String username, String password) {
        Optional<Useri> userOpt = userRepository.findByUserName(username);

        TokenDTO tokenDTO = new TokenDTO();
        if (userOpt.isPresent()) {
            Useri user = userOpt.get();
            if (user.getBloqueado() == null){
                user.setBloqueado(0);
            }
            if (user.getBloqueado() == 3) {
                if (LocalDateTime.now().isAfter(user.getTiempoBloqueo())) {
                    user.setBloqueado(0);
                    //comprobar si la contrasenia es correcta
                    if (BCrypt.checkpw(password, user.getPassword())) {
                        String token = generateToken(user);
                        tokenDTO.setToken(token);
                        tokenDTO.setId(user.getUserId());
                        tokenDTO.setRol(user.getRolId().getRolId());
                        user.setBloqueado(0);
                        userRepository.save(user);
                        //emailSenderBl.sendEmail(user.getPeopleId().getEmail().toString(), "Inicio de sesión", "Se ha iniciado sesión en el sistema");
                        return tokenDTO;
                    } else {
                        user.setBloqueado(user.getBloqueado() + 1);
                        userRepository.save(user);
                        throw new RuntimeException("Invalid credentials");
                    }
                } else {
                    tokenDTO = null;
                    return tokenDTO;
                }
            }else {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    String token = generateToken(user);
                    tokenDTO.setToken(token);
                    tokenDTO.setId(user.getUserId());
                    tokenDTO.setRol(user.getRolId().getRolId());
                    user.setBloqueado(0);
                    userRepository.save(user);
                    //emailSenderBl.sendEmail(user.getPeopleId().getEmail().toString(), "Inicio de sesión", "Se ha iniciado sesión en el sistema");
                    return tokenDTO;

                }else {
                    user.setBloqueado(user.getBloqueado() + 1);
                    if (user.getBloqueado() == 3) {
                        user.setTiempoBloqueo(LocalDateTime.now().plusMinutes(2));
                        userRepository.save(user);
                        tokenDTO = null;
                        return tokenDTO;
                    }
                    userRepository.save(user);
                    throw new RuntimeException("Invalid credentials");
                }
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
                .setExpiration(new Date(currentTimeMillis + 86400000)) // 1 hora de validez
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

            return true;
        } catch (SignatureException e) {
            System.err.println("Token inválido: " + e.getMessage());
            return false;
        }
    }




}