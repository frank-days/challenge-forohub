package com.raulo.foro.domain.entities;

import com.raulo.foro.domain.dtos.topicos.DatosCrearTopico;
import com.raulo.foro.domain.dtos.topicos.DatosModificarTopico;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "topico")
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    LocalDateTime fechaDeCreacion;
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Transient
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    public Topico() {
    }

    public Topico(String titulo, String mensaje, LocalDateTime fechaDeCreacion, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = fechaDeCreacion;
        this.autor = autor;
        this.curso = curso;

        this.estado = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void actualizarDatos(DatosModificarTopico datos){
        if(datos.titulo() != null) {
            this.titulo = datos.titulo();
        }

        if(datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if(datos.estado() != null) {
            this.estado = datos.estado();
        }
    }
}
