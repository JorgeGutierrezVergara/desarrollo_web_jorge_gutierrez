package com.tarea4.tarea4.services;

import com.tarea4.tarea4.models.Actividad;
import com.tarea4.tarea4.models.ActividadDTO;
import com.tarea4.tarea4.models.Nota;
import com.tarea4.tarea4.repositories.ActividadRepository;
import com.tarea4.tarea4.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private NotaRepository notaRepository;

    public List<ActividadDTO> getActividadesRealizadas() {
        LocalDate today = LocalDate.now();
        List<Actividad> actividades = actividadRepository.findByDiaHoraTerminoBefore(today.atStartOfDay()); // <--- ¡Importante cambio aquí!

        return actividades.stream().map(actividad -> {
            ActividadDTO dto = new ActividadDTO();
            dto.setId(actividad.getId());
            dto.setFechaInicio(actividad.getDiaHoraInicio() != null ? actividad.getDiaHoraInicio().toLocalDate() : null); // Ajustado
            dto.setSector(actividad.getSector());
            dto.setDescripcion(actividad.getDescripcion()); 

            Double promedio = calculatePromedioNota(actividad.getId());
            if (promedio != null) {
                dto.setNota(String.format("%.1f", promedio));
            } else {
                dto.setNota("-");
            }
            return dto;
        }).toList();
    }

    public Optional<String> addNota(Long idActividad, Integer valorNota) {
        if (valorNota == null || valorNota < 1 || valorNota > 7) {
            return Optional.empty();
        }

        if (!actividadRepository.existsById(idActividad)) {
            return Optional.empty();
        }

        Nota nuevaNota = new Nota();
        nuevaNota.setIdActividad(idActividad);
        nuevaNota.setValorNota(valorNota);

        notaRepository.save(nuevaNota);

        Double nuevoPromedio = calculatePromedioNota(idActividad);
        if (nuevoPromedio != null) {
            return Optional.of(String.format("%.1f", nuevoPromedio));
        } else {
            return Optional.of("-");
        }
    }

    private Double calculatePromedioNota(Long idActividad) {
        List<Nota> notas = notaRepository.findByIdActividad(idActividad);
        if (notas.isEmpty()) {
            return null;
        }
        double sum = notas.stream().mapToInt(Nota::getValorNota).sum();
        return sum / notas.size();
    }
}