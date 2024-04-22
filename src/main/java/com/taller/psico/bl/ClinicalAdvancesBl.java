package com.taller.psico.bl;

import com.taller.psico.dto.ClinicalAdvancesDTO;
import com.taller.psico.entity.ClinicalAdvances;
import com.taller.psico.entity.Treatment;
import com.taller.psico.repository.ClinicalAdvancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicalAdvancesBl {

    @Autowired
    private ClinicalAdvancesRepository clinicalAdvancesRepository;

    //Crear un avance clinico
    public Integer createClinicalAdvance(ClinicalAdvancesDTO clinicalAdvancesDto){
        ClinicalAdvances clinicalAdvances = new ClinicalAdvances();
        Treatment treatment = new Treatment();
        treatment.setTreatmentId(clinicalAdvancesDto.getTreatmentId());
        clinicalAdvances.setTreatmentId(treatment);
        clinicalAdvances.setDescriptionAdvance(clinicalAdvancesDto.getDescriptionAdvance());
        clinicalAdvances.setDateAdvance(clinicalAdvancesDto.getDateAdvance());
        clinicalAdvances.setInstructionGrade(clinicalAdvancesDto.getInstructionGrade());
        clinicalAdvances.setCompanions(clinicalAdvancesDto.getCompanions());
        clinicalAdvances.setDoctorDerivation(clinicalAdvancesDto.getDoctorDerivation());
        clinicalAdvances.setPlaceDerivation(clinicalAdvancesDto.getPlaceDerivation());
        clinicalAdvances.setStatus(true);
        clinicalAdvances.setTypeIntervation(clinicalAdvancesDto.getTypeIntervation());
        clinicalAdvancesRepository.save(clinicalAdvances);
        return clinicalAdvances.getClinicalAdvancesId();
    }

    //Ver todos los avances clinicos de un tratamiento
    public List<ClinicalAdvancesDTO> findAllByTreatmentId(Integer treatmentId){
        List<ClinicalAdvances> clinicalAdvances = clinicalAdvancesRepository.findAllByTreatmentId(treatmentId);
        List<ClinicalAdvancesDTO> clinicalAdvancesDtos = new ArrayList<>();
        for (ClinicalAdvances clinicalAdvance : clinicalAdvances){
            ClinicalAdvancesDTO clinicalAdvancesDto = new ClinicalAdvancesDTO();
            clinicalAdvancesDto.setClinicalAdvancesId(clinicalAdvance.getClinicalAdvancesId());
            clinicalAdvancesDto.setTreatmentId(clinicalAdvance.getTreatmentId().getTreatmentId());
            clinicalAdvancesDto.setDescriptionAdvance(clinicalAdvance.getDescriptionAdvance());
            clinicalAdvancesDto.setDateAdvance(clinicalAdvance.getDateAdvance());
            clinicalAdvancesDto.setInstructionGrade(clinicalAdvance.getInstructionGrade());
            clinicalAdvancesDto.setCompanions(clinicalAdvance.getCompanions());
            clinicalAdvancesDto.setDoctorDerivation(clinicalAdvance.getDoctorDerivation());
            clinicalAdvancesDto.setPlaceDerivation(clinicalAdvance.getPlaceDerivation());
            clinicalAdvancesDto.setStatus(clinicalAdvance.getStatus());
            clinicalAdvancesDto.setTypeIntervation(clinicalAdvance.getTypeIntervation());
            clinicalAdvancesDtos.add(clinicalAdvancesDto);
        }
        return clinicalAdvancesDtos;
    }




}
