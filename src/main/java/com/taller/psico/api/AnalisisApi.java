package com.taller.psico.api;

import com.taller.psico.bl.AnalisisBl;
import com.taller.psico.dto.AnalisisDto;
import com.taller.psico.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")

public class AnalisisApi {

    @Autowired
    private AnalisisBl analisisBl;

    //Agregar un analisis
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<AnalisisDto>> createAnalisis(@RequestBody AnalisisDto analisisDto){
        analisisBl.createAnalisis(analisisDto);
        try {
            return ResponseEntity.ok(new ResponseDTO<>(200,analisisDto, "Analisis creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Error al crear el analisis"));
        }
    }

    //Modificar un analisis
    @PutMapping("/update/{analisisId}")
    public ResponseEntity<ResponseDTO<AnalisisDto>> updateAnalisis(@PathVariable Integer analisisId, @RequestBody AnalisisDto analisisDto){
        analisisBl.updateAnalisis(analisisId, analisisDto);
        try {
            return ResponseEntity.ok(new ResponseDTO<>(200,analisisDto, "Analisis actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Error al actualizar el analisis"));
        }
    }

    //Mostrar todos los analisis
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<AnalisisDto>>> findAllAnalisis(){
        List<AnalisisDto> analisis =analisisBl.findAllAnalisis();
        try {
            return ResponseEntity.ok(new ResponseDTO<>(200,analisis, "Analisis encontrados"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Error al encontrar los analisis"));
        }
    }

    //Eliminar
    @DeleteMapping("/{analisisId}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteAnalisis(@PathVariable Integer analisisId){
        analisisBl.deleteAnalisis(analisisId);
        try {
            return ResponseEntity.ok(new ResponseDTO<>(200,null, "Analisis eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), null, "Error al eliminar el analisis"));
        }
    }

}
