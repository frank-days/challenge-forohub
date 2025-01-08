package com.raulo.foro.presentation.services;

import com.raulo.foro.domain.dtos.topicos.DatosCrearTopico;
import com.raulo.foro.domain.dtos.topicos.DatosDetalleTopico;
import com.raulo.foro.domain.dtos.topicos.DatosModificarTopico;
import com.raulo.foro.domain.entities.Topico;
import com.raulo.foro.domain.repositories.CursoRepository;
import com.raulo.foro.domain.repositories.TopicoRepository;
import com.raulo.foro.domain.repositories.UsuarioRepository;
import com.raulo.foro.domain.validators.ValidadorTituloMensajeExists;
import com.raulo.foro.domain.validators.ValidadorTopico;
import com.raulo.foro.infrastructure.errors.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Inyectamos unicamente el validador del titulo para usarlo con la actualizacion.
    @Autowired
    private ValidadorTituloMensajeExists validador;

    @Autowired
    private List<ValidadorTopico> validadores;

    public DatosDetalleTopico crearTopico(DatosCrearTopico datos) {
        // Buscamos al usuario y al curso para asegurarnos de que exista.
        if(!cursoRepository.existsById(datos.curso())){
            throw new ValidacionException("No existe un curso con el ID proporcinado.");
        }

        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionException("No existe un usuario con el ID proporcinado.");
        }

        validadores.forEach( v -> v.validar(datos) );

        var curso = cursoRepository.findById(datos.curso()).get();
        var usuario = usuarioRepository.findById(datos.autor()).get();

        var topico = new Topico(datos.titulo(), datos.mensaje(), datos.fechaDeCreacion(), usuario, curso);
        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);
    }

    public Page<Topico> obtenerTodosLosTopicos(Pageable paginacion){
        return topicoRepository.findAll(paginacion);
    }

    public DatosDetalleTopico obtenerTopicoPorId(Long id) {
        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("No existe un topico con el ID proporcinado.");
        }

        var topico = topicoRepository.findById(id).get();

        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico modificarTopico(Long id, DatosModificarTopico datos) {
        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("No existe un topico con el ID proporcinado.");
        }

        validador.validar(new DatosCrearTopico(datos.titulo(), datos.mensaje(),
                null, null, null));

        var topico = topicoRepository.findById(id).get();
        topico.actualizarDatos(datos);

        return new DatosDetalleTopico(topico);
    }

    public void eliminarTopico(Long id) {
        if(!topicoRepository.existsById(id)){
            throw new ValidacionException("No existe un topico con el ID proporcinado.");
        }

        topicoRepository.deleteById(id);
    }
}
