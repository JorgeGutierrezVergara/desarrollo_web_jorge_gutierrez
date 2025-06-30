package com.tarea4.tarea4.repositories; 

import com.tarea4.tarea4.models.Nota; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository 
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByIdActividad(Long idActividad);
}