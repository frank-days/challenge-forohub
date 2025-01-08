package com.raulo.foro.domain.entities;

import com.raulo.foro.domain.enums.CategoriaCurso;
import jakarta.persistence.*;

@Entity(name = "curso")
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    public Curso(){ }

    public Curso(String nombre, CategoriaCurso categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public CategoriaCurso getCategoria() {
        return categoria;
    }
}
