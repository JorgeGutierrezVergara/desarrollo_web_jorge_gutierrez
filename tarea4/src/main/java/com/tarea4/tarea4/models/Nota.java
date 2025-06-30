package com.tarea4.tarea4.models; 

import jakarta.persistence.*;
import lombok.Data;

@Entity 
@Table(name = "nota") 
@Data 
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actividad_id")
    private Long idActividad;

    @Column(name = "nota") 
    private Integer valorNota;


}