package com.raulo.foro.domain.dtos.topicos;

public record DatosModificarTopico(
        String titulo,
        String mensaje,
        Boolean estado
) {
}
