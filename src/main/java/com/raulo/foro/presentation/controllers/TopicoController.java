package com.raulo.foro.presentation.controllers;

import com.raulo.foro.domain.dtos.topicos.DatosCrearTopico;
import com.raulo.foro.domain.dtos.topicos.DatosDetalleTopico;
import com.raulo.foro.domain.dtos.topicos.DatosModificarTopico;
import com.raulo.foro.presentation.services.TopicoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity crearTopico(@RequestBody DatosCrearTopico datos) {
        var topicoCreado = topicoService.crearTopico(datos);

        return ResponseEntity.ok(topicoCreado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> obtenerTodosLosTopicos(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoService.obtenerTodosLosTopicos(paginacion).map(DatosDetalleTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopicoPorId(@PathVariable Long id) {
        var topico = topicoService.obtenerTopicoPorId(id);

        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> modificarTopico(@PathVariable Long id, @RequestBody DatosModificarTopico datos){
        var topicoModificado = topicoService.modificarTopico(id, datos);

        return ResponseEntity.ok(topicoModificado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);

        return ResponseEntity.noContent().build();
    }
}
