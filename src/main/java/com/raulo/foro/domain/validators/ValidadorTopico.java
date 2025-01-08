package com.raulo.foro.domain.validators;

import com.raulo.foro.domain.dtos.topicos.DatosCrearTopico;

public interface ValidadorTopico {
    void validar(DatosCrearTopico datos);
}
