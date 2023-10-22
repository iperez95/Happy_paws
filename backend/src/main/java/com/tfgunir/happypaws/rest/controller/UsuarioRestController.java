package com.tfgunir.happypaws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.IUsuarioDao;
import com.tfgunir.happypaws.modelo.entities.Rol;
import com.tfgunir.happypaws.modelo.entities.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    @Autowired
    private IUsuarioDao usuarioDao;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setEmailNormalizado(usuario.getEmail());

            usuario.setRol(new Rol(3,"Adoptante"));
            if (usuarioDao.altaUsuario(usuario)) {
                return new ResponseEntity<>(usuario, HttpStatus.CREATED);
            }

            return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Ha ocurrido un error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
