package com.taller.psico.bl;

import com.taller.psico.dto.PrescriptionDTO;
import com.taller.psico.entity.ClinicalAdvances;
import com.taller.psico.entity.Prescription;
import com.taller.psico.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionBl {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    //Crear una prescripcion de un avance clinico
    public void createPrescription(Integer clinicalAdvancesId, String prescription){
        Prescription prescriptionEntity = new Prescription();
        ClinicalAdvances clinicalAdvances = new ClinicalAdvances();
        clinicalAdvances.setClinicalAdvancesId(clinicalAdvancesId);
        prescriptionEntity.setClinicalAdvancesId(clinicalAdvances);
        prescriptionEntity.setContentPrescription(prescription);
        prescriptionRepository.save(prescriptionEntity);
    }

    //Ver todas las prescripciones de un avance clinico
    public List<PrescriptionDTO> findAllByClinicalAdvancesId(Integer clinicalAdvancesId){
        List<Prescription> prescriptions = prescriptionRepository.findAllByClinicalAdvancesId(clinicalAdvancesId);
        List<PrescriptionDTO> prescriptionDtos =  new ArrayList<>();
        for (Prescription prescription : prescriptions){
            PrescriptionDTO prescriptionDto = new PrescriptionDTO();
            prescriptionDto.setPrescriptionId(prescription.getPrescriptionId());
            prescriptionDto.setClinicalAdvancesId(prescription.getClinicalAdvancesId().getClinicalAdvancesId());
            prescriptionDto.setContentPrescription(prescription.getContentPrescription());
            prescriptionDtos.add(prescriptionDto);
        }
        return prescriptionDtos;
    }
}
