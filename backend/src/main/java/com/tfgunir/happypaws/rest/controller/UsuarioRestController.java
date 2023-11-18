package com.tfgunir.happypaws.rest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.IUsuarioDao;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.entities.Rol;
import com.tfgunir.happypaws.modelo.entities.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioAuthProvider usuarioAuthProvider;

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") int id) {
        Usuario u = usuarioDao.buscarUnUsuario(id);
        if (u!=null)
            return ResponseEntity.ok(u);
        else
            return ResponseEntity.notFound().build();
    }


    /**
     * This method receives a Usuario object and returns a ResponseEntity with the created Usuario object.
     * This endpoint is used for user registration.
     * @param usuario
     * @return
     */
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setEmailNormalizado(usuario.getEmail());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuario.setRol(new Rol(3,"Adoptante"));
            usuario.setFechaAlta(new Date());
            usuario.setFechaenabled(new Date());
            byte enabled = 1; 
            usuario.setEnabled(enabled);
            UsuarioDto usuarioDto = usuarioDao.altaUsuario(usuario);
            if (usuarioDto != null) {
                usuarioDto.setToken(usuarioAuthProvider.createToken(usuarioDto));
                return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
            }

            return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Ha ocurrido un error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
