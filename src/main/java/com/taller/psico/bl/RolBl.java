package com.taller.psico.bl;

import com.taller.psico.dto.RolDTO;
import com.taller.psico.entity.Rol;
import com.taller.psico.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolBl {

    @Autowired
    private RolRepository rolRepository;

    //Todos los roles
    public List<RolDTO> findAllRoles(){
        List<Rol> roles = rolRepository.findAllRoles();
        List<RolDTO> rolesDto = new ArrayList<>();
        for (Rol rol : roles) {
            rolesDto.add(new RolDTO(rol.getRolId(), rol.getRolType(), rol.getStatus()));
        }
        return rolesDto;
    }


}
