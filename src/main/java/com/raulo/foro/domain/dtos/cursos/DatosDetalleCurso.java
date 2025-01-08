package com.raulo.foro.domain.dtos.cursos;

import com.raulo.foro.domain.entities.Curso;
import jakarta.validation.constraints.NotBlank;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosDetalleCurso(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
    }
}
