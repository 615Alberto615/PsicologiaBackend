package com.taller.psico.bl;

import com.taller.psico.dto.AnalisisDto;
import com.taller.psico.entity.Analisis;
import com.taller.psico.repository.AnalisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalisisBl {

    @Autowired
    private AnalisisRepository analisisRepository;

    //Agregar un analisis
    public void createAnalisis(AnalisisDto analisisDTO) {
        Analisis analisis = new Analisis();
        analisis.setActivo(analisisDTO.getActivo());
        analisis.setAmenaza(analisisDTO.getAmenaza());
        analisis.setConsecuencia(analisisDTO.getConsecuencia());
        analisis.setProbabilidad(analisisDTO.getProbabilidad());
        analisis.setImpacto(analisisDTO.getImpacto());
        analisis.setRiesgoInherente(analisisDTO.getRiesgoInherente());
        analisis.setTratamiento(analisisDTO.getTratamiento());
        analisis.setNivelRiesgo(analisisDTO.getNivelRiesgo());
        analisis.setControles(analisisDTO.getControles());
        analisis.setTipo(analisisDTO.getTipo());
        analisis.setNivel(analisisDTO.getNivel());
        analisis.setFrecuencia(analisisDTO.getFrecuencia());
        analisis.setProbabilidadResidual(analisisDTO.getProbabilidadResidual());
        analisis.setImpactoResidual(analisisDTO.getImpactoResidual());
        analisis.setRiesgoResidual(analisisDTO.getRiesgoResidual());
        analisis.setNivelRiesgoResidual(analisisDTO.getNivelRiesgoResidual());
        analisis.setCreatedAt(analisisDTO.getCreatedAt());
        analisis.setUpdatedAt(analisisDTO.getUpdatedAt());
        analisisRepository.save(analisis);
    }

    //Actualizar un analisis
    public void updateAnalisis(Integer analisisId, AnalisisDto analisisDTO) {
        Analisis analisis = analisisRepository.findByIdAnalisis(analisisId);
        analisis.setActivo(analisisDTO.getActivo());
        analisis.setAmenaza(analisisDTO.getAmenaza());
        analisis.setConsecuencia(analisisDTO.getConsecuencia());
        analisis.setProbabilidad(analisisDTO.getProbabilidad());
        analisis.setImpacto(analisisDTO.getImpacto());
        analisis.setRiesgoInherente(analisisDTO.getRiesgoInherente());
        analisis.setTratamiento(analisisDTO.getTratamiento());
        analisis.setNivelRiesgo(analisisDTO.getNivelRiesgo());
        analisis.setControles(analisisDTO.getControles());
        analisis.setTipo(analisisDTO.getTipo());
        analisis.setNivel(analisisDTO.getNivel());
        analisis.setFrecuencia(analisisDTO.getFrecuencia());
        analisis.setProbabilidadResidual(analisisDTO.getProbabilidadResidual());
        analisis.setImpactoResidual(analisisDTO.getImpactoResidual());
        analisis.setRiesgoResidual(analisisDTO.getRiesgoResidual());
        analisis.setNivelRiesgoResidual(analisisDTO.getNivelRiesgoResidual());
        analisis.setUpdatedAt(analisisDTO.getUpdatedAt());
        analisisRepository.save(analisis);
    }

    //Mostrar todos los analisis
    public List<AnalisisDto> findAllAnalisis() {
        List<Analisis> analisisList = analisisRepository.findAllAnalisis();
        return analisisList.stream().map(analisis -> {
            AnalisisDto analisisDTO = new AnalisisDto();
            analisisDTO.setId(analisis.getId());
            analisisDTO.setActivo(analisis.getActivo());
            analisisDTO.setAmenaza(analisis.getAmenaza());
            analisisDTO.setConsecuencia(analisis.getConsecuencia());
            analisisDTO.setProbabilidad(analisis.getProbabilidad());
            analisisDTO.setImpacto(analisis.getImpacto());
            analisisDTO.setRiesgoInherente(analisis.getRiesgoInherente());
            analisisDTO.setTratamiento(analisis.getTratamiento());
            analisisDTO.setNivelRiesgo(analisis.getNivelRiesgo());
            analisisDTO.setControles(analisis.getControles());
            analisisDTO.setTipo(analisis.getTipo());
            analisisDTO.setNivel(analisis.getNivel());
            analisisDTO.setFrecuencia(analisis.getFrecuencia());
            analisisDTO.setProbabilidadResidual(analisis.getProbabilidadResidual());
            analisisDTO.setImpactoResidual(analisis.getImpactoResidual());
            analisisDTO.setRiesgoResidual(analisis.getRiesgoResidual());
            analisisDTO.setNivelRiesgoResidual(analisis.getNivelRiesgoResidual());
            analisisDTO.setCreatedAt(analisis.getCreatedAt());
            analisisDTO.setUpdatedAt(analisis.getUpdatedAt());
            return analisisDTO;
        }).collect(Collectors.toList());
    }

    //Eliminar por id
    public void deleteAnalisis(Integer analisisId) {
        Analisis analisis = analisisRepository.findByIdAnalisis(analisisId);
        analisisRepository.delete(analisis);
    }

}
