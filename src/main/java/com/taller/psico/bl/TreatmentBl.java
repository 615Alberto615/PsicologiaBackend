package com.taller.psico.bl;

import com.taller.psico.dto.TreatmentDTO;
import com.taller.psico.entity.PreTreatment;
import com.taller.psico.entity.Treatment;
import com.taller.psico.entity.Useri;
import com.taller.psico.repository.PreTreatmentRepository;
import com.taller.psico.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentBl {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PreTreatmentRepository preTreatmentRepository;

    //Crear tratamiento
    public void createTreatment(TreatmentDTO treatmentDto){
        Treatment treatment = new Treatment();
        Useri userPatient = new Useri();
        Useri userPsychiatrist = new Useri();
        userPatient.setUserId(treatmentDto.getUserPatientId());
        userPsychiatrist.setUserId(treatmentDto.getUserPsychiatristId());
        treatment.setUserPatientId(userPatient);
        treatment.setUserPsychiatristId(userPsychiatrist);
        treatment.setName(treatmentDto.getName());
        treatment.setStartDate(treatmentDto.getStartDate());
        treatment.setStatus(true);
        treatmentRepository.save(treatment);
        PreTreatment preTreatment = new PreTreatment();
        preTreatment.setTreatmentId(treatment);
        preTreatment.setUserId(userPatient);
        preTreatmentRepository.save(preTreatment);
    }

    //Ver todos los tratamientos de un usuario
    public List<TreatmentDTO> findAllByUserId(Integer userId){
        List<Treatment> treatments = treatmentRepository.findAllByUserId(userId);
        List<TreatmentDTO> treatmentDtos = new ArrayList<>();
        for (Treatment treatment : treatments){
            TreatmentDTO treatmentDto = new TreatmentDTO();
            treatmentDto.setTreatmentId(treatment.getTreatmentId());
            treatmentDto.setUserPatientId(treatment.getUserPatientId().getUserId());
            treatmentDto.setUserPsychiatristId(treatment.getUserPsychiatristId().getUserId());
            treatmentDto.setName(treatment.getName());
            treatmentDto.setStartDate(treatment.getStartDate());
            treatmentDto.setStatus(treatment.getStatus());
            treatmentDtos.add(treatmentDto);
        }
        return treatmentDtos;
    }

}
