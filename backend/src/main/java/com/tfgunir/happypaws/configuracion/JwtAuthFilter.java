package com.tfgunir.happypaws.configuracion;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

/**
 * Esta clase representa un filtro que intercepta las solicitudes entrantes y valida el token JWT en el encabezado de Autorización.
 * Si el token es válido, establece la autenticación en el SecurityContextHolder.
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UsuarioAuthProvider usuarioAuthProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] authElements = header.split(" ");
            if (authElements.length == 2 && authElements[0].equals("Bearer")) {
                try {
                    SecurityContextHolder
                        .getContext()
                        .setAuthentication(usuarioAuthProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
