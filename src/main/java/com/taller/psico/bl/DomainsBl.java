package com.taller.psico.bl;

import com.taller.psico.dto.DomainsDTO;
import com.taller.psico.entity.Domains;
import com.taller.psico.repository.DomainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainsBl {

    @Autowired
    private DomainsRepository domainsRepository;

    //Mostar todos los dominios de un tipo
    public List<DomainsDTO> findByType(String type) {
        List<Domains> domains = domainsRepository.findByType(type);
        List<DomainsDTO> domainsDto = new ArrayList<>();
        for (Domains domain : domains) {
            domainsDto.add(new DomainsDTO(domain.getDomainsId(), domain.getType(), domain.getName(), domain.getDescription()));
        }
        return domainsDto;
    }

}
