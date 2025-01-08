package com.raulo.foro.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.raulo.foro.domain.entities.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {
    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            String token = JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generaFechaExpiracion())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456"); // validando firma
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build()
                    .verify(token);
            return verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error al verificar el token: " + exception.getMessage());
        }
    }

    private Instant generaFechaExpiracion(){
        ZoneId mexicoCityZone = ZoneId.of("America/Mexico_City");

        return LocalDateTime.now().plusHours(5).atZone(mexicoCityZone).toInstant();
    }
}
