package com.raulo.foro.domain.dtos.topicos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @FutureOrPresent
        @NotNull
        @JsonAlias("fecha_de_creacion")
        LocalDateTime fechaDeCreacion,
        @NotNull
        Long autor,
        @NotNull
        Long curso
) {
}
