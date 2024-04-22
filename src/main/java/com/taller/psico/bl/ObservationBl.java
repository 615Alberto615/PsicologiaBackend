package com.taller.psico.bl;

import com.taller.psico.dto.ObservationDTO;
import com.taller.psico.entity.ClinicalAdvances;
import com.taller.psico.entity.Observation;
import com.taller.psico.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObservationBl {

    @Autowired
    private ObservationRepository observationRepository;

    //Crear una observacion de un avance clinico
    public void createObservation(Integer clinicalAdvancesId, String observation){
        Observation observationEntity = new Observation();
        ClinicalAdvances clinicalAdvances = new ClinicalAdvances();
        clinicalAdvances.setClinicalAdvancesId(clinicalAdvancesId);
        observationEntity.setClinicalAdvancesId(clinicalAdvances);
        observationEntity.setObservationContent(observation);
        observationRepository.save(observationEntity);
    }

    //Ver todas las observaciones de un avance clinico
    public List<ObservationDTO> findAllByClinicalAdvancesId(Integer clinicalAdvancesId){
        List<Observation> observations = observationRepository.findAllByClinicalAdvancesId(clinicalAdvancesId);
        List<ObservationDTO> observationDtos =  new ArrayList<>();
        for (Observation observation : observations){
            ObservationDTO observationDto = new ObservationDTO();
            observationDto.setObservationId(observation.getObservationId());
            observationDto.setClinicalAdvancesId(observation.getClinicalAdvancesId().getClinicalAdvancesId());
            observationDto.setObservationContent(observation.getObservationContent());
            observationDtos.add(observationDto);
        }
        return observationDtos;
    }
}
