package com.raulo.foro.domain.repositories;

import com.raulo.foro.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByUsername(String username);
}
