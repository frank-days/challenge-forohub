package com.raulo.foro.presentation.controllers;

import com.raulo.foro.domain.dtos.auth.DatosAuthUsuario;
import com.raulo.foro.domain.dtos.auth.DatosJWToken;
import com.raulo.foro.domain.entities.Usuario;
import com.raulo.foro.infrastructure.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody DatosAuthUsuario usuario) {
        Authentication token = new UsernamePasswordAuthenticationToken(usuario.username(), usuario.password());
        authenticationManager.authenticate(token);

        var usuarioAutenticado = authenticationManager.authenticate(token);

        var jwt = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWToken(jwt));
    }
}
