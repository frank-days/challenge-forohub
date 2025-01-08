package com.raulo.foro.domain.dtos.auth;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAuthUsuario(
        @JsonAlias("email")
        String username,
        String password
) {
}
