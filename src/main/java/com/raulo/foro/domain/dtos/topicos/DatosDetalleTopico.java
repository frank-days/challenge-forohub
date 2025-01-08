package com.raulo.foro.domain.dtos.topicos;

import com.raulo.foro.domain.entities.Topico;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        boolean estado,
        Long autor,
        Long curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.isEstado(),
                topico.getAutor().getId(), topico.getCurso().getId());
    }
}
