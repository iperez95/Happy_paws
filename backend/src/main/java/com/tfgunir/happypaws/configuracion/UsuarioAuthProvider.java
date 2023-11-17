package com.tfgunir.happypaws.configuracion;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;

import lombok.RequiredArgsConstructor;

/**
 * This class provides methods to create and validate JWT tokens for a given UsuarioDto.
 */
@RequiredArgsConstructor
@Component
public class UsuarioAuthProvider {
    /**
     * The secret key used to sign the JWT token.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    /**
     * This method encodes the secret key to Base64.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Creates a JWT token for the given UsuarioDto.
     * @param dto
     * @return
     */
    public String createToken(UsuarioDto dto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);
        return JWT.create()
            .withIssuer(dto.getEmail())
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withClaim("nombre", dto.getNombre())
            .withClaim("apellidos", dto.getApellidos())
            .withClaim("rol", dto.getRol())
            .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * Validates a JWT token and returns the authentication object for the user.
     * @param token
     * @return
     */
    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
            .build();

        DecodedJWT decoded = verifier.verify(token);

        UsuarioDto usuario = UsuarioDto.builder()
            .email(decoded.getIssuer())
            .nombre(decoded.getClaim("nombre").asString())
            .apellidos(decoded.getClaim("apellidos").asString())
            .build();
        
        return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
    }
}
