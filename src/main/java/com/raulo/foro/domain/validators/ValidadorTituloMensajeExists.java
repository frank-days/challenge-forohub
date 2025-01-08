package com.raulo.foro.domain.validators;

import com.raulo.foro.domain.dtos.topicos.DatosCrearTopico;
import com.raulo.foro.domain.repositories.TopicoRepository;
import com.raulo.foro.infrastructure.errors.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTituloMensajeExists implements ValidadorTopico{
    @Autowired
    public TopicoRepository topicoRepository;

    @Override
    public void validar(DatosCrearTopico datos) {
        if(topicoRepository.existsByTituloContainsIgnoreCase(datos.titulo())){
            throw new ValidacionException("Ya existe un topico con ese titulo.");
        }

        if(topicoRepository.existsByMensajeContainsIgnoreCase(datos.mensaje())){
            throw new ValidacionException("Ya existe un topico con ese mensaje.");
        }
    }
}
