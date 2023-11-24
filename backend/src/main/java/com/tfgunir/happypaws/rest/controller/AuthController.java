package com.tfgunir.happypaws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.IProtectoraDao;
import com.tfgunir.happypaws.modelo.dao.IUsuarioDao;
import com.tfgunir.happypaws.modelo.dto.CredentialsDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Protectora;

@RestController
public class AuthController {
    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Autowired
    private IProtectoraDao protectoraDao;

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

        Protectora protectora = protectoraDao.buscarProtectoraPorUsuario(user.getId());
        Integer idProtectora = null;
        if (protectora != null) {
            idProtectora = protectora.getIdprotectora();
        }
        user.setToken(usuarioAuthProvider.createToken(user, idProtectora));
        return ResponseEntity.ok(user);
    }
}
