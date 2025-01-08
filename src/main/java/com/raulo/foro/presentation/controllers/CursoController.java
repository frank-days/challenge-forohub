package com.raulo.foro.presentation.controllers;

import com.raulo.foro.domain.dtos.cursos.DatosCrearCurso;
import com.raulo.foro.domain.dtos.cursos.DatosDetalleCurso;
import com.raulo.foro.presentation.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity crearCurso(@RequestBody DatosCrearCurso datos) {
        DatosDetalleCurso cursoCreado = cursoService.crearCruso(datos);

        return ResponseEntity.ok(cursoCreado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleCurso>> obtenerTodosLosCursos(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(cursoService.obtenerTodosLosCursos(paginacion).map(DatosDetalleCurso::new));
    }
}
