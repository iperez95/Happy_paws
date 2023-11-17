package com.tfgunir.happypaws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.IUsuarioDao;
import com.tfgunir.happypaws.modelo.dto.CredentialsDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;

@RestController
public class AuthController {
    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Autowired
    private UsuarioAuthProvider usuarioAuthProvider;

    /**
     * This method receives a CredentialsDto object and returns a UsuarioDto object with the token.
     * @param credentialsDto
     * @return
     */
    @PostMapping("/api/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody CredentialsDto credentialsDto) {
        UsuarioDto user = usuarioDao.login(credentialsDto);
        user.setToken(usuarioAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }
}
