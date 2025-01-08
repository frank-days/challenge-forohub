CREATE TABLE cursos
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nombre    VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    CONSTRAINT pk_cursos PRIMARY KEY (id)
);

CREATE TABLE respuestas
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    mensaje        VARCHAR(255) NOT NULL,
    fecha_creacion datetime NOT NULL,
    solucion       BIT(1) NOT NULL,
    topico_id      BIGINT NOT NULL,
    autor_id       BIGINT NOT NULL,
    CONSTRAINT pk_respuestas PRIMARY KEY (id)
);

CREATE TABLE topicos
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    titulo            VARCHAR(255) NOT NULL,
    mensaje           VARCHAR(255) NOT NULL,
    fecha_de_creacion datetime NOT NULL,
    estado            tinyint NOT NULL,
    usuario_id        BIGINT NOT NULL,
    curso_id          BIGINT NOT NULL,
    CONSTRAINT pk_topicos PRIMARY KEY (id)
);

CREATE TABLE usuarios
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    nombre   VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (id)
);

ALTER TABLE respuestas
    ADD CONSTRAINT FK_RESPUESTAS_ON_AUTOR FOREIGN KEY (autor_id) REFERENCES usuarios (id);

ALTER TABLE respuestas
    ADD CONSTRAINT FK_RESPUESTAS_ON_TOPICO FOREIGN KEY (topico_id) REFERENCES topicos (id);

ALTER TABLE topicos
    ADD CONSTRAINT FK_TOPICOS_ON_CURSO FOREIGN KEY (curso_id) REFERENCES cursos (id);

ALTER TABLE topicos
    ADD CONSTRAINT FK_TOPICOS_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuarios (id);