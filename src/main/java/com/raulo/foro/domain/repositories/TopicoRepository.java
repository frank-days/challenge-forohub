package com.raulo.foro.domain.repositories;

import com.raulo.foro.domain.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloContainsIgnoreCase(String titulo);
    Boolean existsByMensajeContainsIgnoreCase(String mensaje);
}
