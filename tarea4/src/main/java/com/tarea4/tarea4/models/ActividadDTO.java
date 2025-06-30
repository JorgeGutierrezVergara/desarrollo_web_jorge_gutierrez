package com.tarea4.tarea4.models; 

import lombok.Data;
import java.time.LocalDate;

@Data 
public class ActividadDTO {
    private Long id;
    private LocalDate fechaInicio; 
    private String sector;
    private String descripcion;
    private String nota;
}