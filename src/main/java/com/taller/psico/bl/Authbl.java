package com.taller.psico.bl;
import com.taller.psico.dto.PeopleDTO;
import com.taller.psico.dto.UseriDTO;
import com.taller.psico.entity.People;
import com.taller.psico.entity.Rol;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.RolRepository;
import com.taller.psico.repository.UseriRepository;
import com.taller.psico.repository.PeopleRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    public String loginUser(String username, String password) {
        Optional<Useri> userOpt = userRepository.findByUserName(username);
        if (userOpt.isPresent()) {
            Useri user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return generateToken(user);
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    private String generateToken(Useri user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getUserId()))
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 3600000)) // 1 hora de validez
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}