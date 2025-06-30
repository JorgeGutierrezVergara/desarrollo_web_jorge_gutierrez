package com.tarea4.tarea4.models; 

import jakarta.persistence.*; 
import lombok.Data; 
import java.time.LocalDateTime;

@Entity
@Table(name = "actividad")
@Data 
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comuna_id")
    private Integer comunaId; 

    @Column(name = "sector")
    private String sector;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "celular")
    private String celular;

    @Column(name = "dia_hora_inicio") 
    private LocalDateTime diaHoraInicio; 

    @Column(name = "dia_hora_termino") 
    private LocalDateTime diaHoraTermino; 

    @Column(name = "descripcion") 
    private String descripcion;

}