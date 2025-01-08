package com.raulo.foro.presentation.services;

import com.raulo.foro.domain.dtos.cursos.DatosCrearCurso;
import com.raulo.foro.domain.dtos.cursos.DatosDetalleCurso;
import com.raulo.foro.domain.entities.Curso;
import com.raulo.foro.domain.enums.CategoriaCurso;
import com.raulo.foro.domain.repositories.CursoRepository;
import com.raulo.foro.domain.validators.ValidadorTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleCurso crearCruso(DatosCrearCurso datos) {
        CategoriaCurso categoriaCurso = CategoriaCurso.valueOf(datos.categoria());

        var curso = new Curso(datos.nombre(), categoriaCurso);
        cursoRepository.save(curso);

        return new DatosDetalleCurso(curso);
    }

    public Page<Curso> obtenerTodosLosCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion);
    }
}
