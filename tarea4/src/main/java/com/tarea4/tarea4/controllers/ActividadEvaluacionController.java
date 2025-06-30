package com.tarea4.tarea4.controllers; 

import com.tarea4.tarea4.models.ActividadDTO; 
import com.tarea4.tarea4.services.ActividadService;
import jakarta.validation.constraints.Max;  
import jakarta.validation.constraints.Min; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated; 
import org.springframework.web.bind.annotation.*; 
import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping("/api/actividades") 
@CrossOrigin(origins = "*") 
@Validated 
public class ActividadEvaluacionController {

    @Autowired 
    private ActividadService actividadService;

    @GetMapping("/realizadas")
    public ResponseEntity<List<ActividadDTO>> getActividadesRealizadas() {
        List<ActividadDTO> actividades = actividadService.getActividadesRealizadas();
        return ResponseEntity.ok(actividades); 
    }

    @PostMapping("/{id}/evaluar")
    public ResponseEntity<String> evaluarActividad(
            @PathVariable Long id,
            @RequestParam @Min(1) @Max(7) Integer nota 
    ) {
        Optional<String> nuevoPromedio = actividadService.addNota(id, nota);

        if (nuevoPromedio.isPresent()) {
            return ResponseEntity.ok(nuevoPromedio.get()); 
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al evaluar la actividad o nota inválida (debe ser un entero entre 1 y 7).");
        }
    }
}