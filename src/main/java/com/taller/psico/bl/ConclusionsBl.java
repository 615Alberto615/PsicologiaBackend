package com.taller.psico.bl;

import com.taller.psico.dto.ConclusionsDTO;
import com.taller.psico.entity.Conclusions;
import com.taller.psico.entity.PostTreatment;
import com.taller.psico.repository.ConclusionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConclusionsBl {

    @Autowired
    private ConclusionsRepository conclusionsRepository;

    //Crear una conclusion de un post tratamiento
    public void createConclusions(Integer postTreatmentId, String conclusions){
        Conclusions conclusionsEntity = new Conclusions();
        PostTreatment postTreatment = new PostTreatment();
        postTreatment.setPostTreatmentId(postTreatmentId);
        conclusionsEntity.setPostTreatmentId(postTreatment);
        conclusionsEntity.setContent(conclusions);
        conclusionsRepository.save(conclusionsEntity);
    }

    //Ver todas las conclusiones de un post tratamiento
    public List<ConclusionsDTO> findAllByPostTreatmentId(Integer postTreatmentId){
        List<Conclusions> conclusions = conclusionsRepository.findAllByPostTreatmentId(postTreatmentId);
        List<ConclusionsDTO> conclusionsDtos =  new ArrayList<>();
        for (Conclusions conclusion : conclusions){
            ConclusionsDTO conclusionsDto = new ConclusionsDTO();
            conclusionsDto.setConclusionsId(conclusion.getConclusionsId());
            conclusionsDto.setPostTreatmentId(conclusion.getPostTreatmentId().getPostTreatmentId());
            conclusionsDto.setContent(conclusion.getContent());
            conclusionsDtos.add(conclusionsDto);
        }
        return conclusionsDtos;
    }
}
