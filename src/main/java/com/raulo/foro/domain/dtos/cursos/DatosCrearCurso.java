package com.raulo.foro.domain.dtos.cursos;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
