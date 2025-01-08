package com.raulo.foro.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "respuesta")
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean solucion;

    @ManyToOne
    private Topico topico;

    @ManyToOne
    private Usuario autor;

}
