package com.taller.psico.bl;

import com.taller.psico.dto.PostTreatmentDTO;
import com.taller.psico.entity.PostTreatment;
import com.taller.psico.entity.Treatment;
import com.taller.psico.repository.PostTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostTreatmentBl {

    @Autowired
    private PostTreatmentRepository postTreatmentRepository;

    //Crear un post tratamiento de un tratamiento
    public Integer createPostTreatment(PostTreatmentDTO postTreatment, Integer treatmentId){
        PostTreatment postTreatmentEntity = new PostTreatment();
        Treatment treatment = new Treatment();
        treatment.setTreatmentId(treatmentId);
        postTreatmentEntity.setTreatmentId(treatment);
        postTreatmentEntity.setEndTreatment(postTreatment.getEndTreatment());
        postTreatmentEntity.setReopeningCase(false);
        postTreatmentEntity.setWhyReopeningCase(postTreatment.getWhyReopeningCase());
        postTreatmentRepository.save(postTreatmentEntity);
        return postTreatmentEntity.getPostTreatmentId();
    }

    //mostrar el post tratamiento de un tratamiento
    public PostTreatmentDTO findAllByTreatmentId(Integer treatmentId){
        PostTreatment postTreatment = postTreatmentRepository.findAllByTreatmentId(treatmentId);
        PostTreatmentDTO postTreatmentDto = new PostTreatmentDTO();
        postTreatmentDto.setPostTreatmentId(postTreatment.getPostTreatmentId());
        postTreatmentDto.setEndTreatment(postTreatment.getEndTreatment());
        postTreatmentDto.setReopeningCase(postTreatment.getReopeningCase());
        postTreatmentDto.setWhyReopeningCase(postTreatment.getWhyReopeningCase());
        return postTreatmentDto;
    }
}
