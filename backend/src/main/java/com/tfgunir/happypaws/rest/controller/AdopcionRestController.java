package com.tfgunir.happypaws.rest.controller;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.AdopcionDao;
import com.tfgunir.happypaws.modelo.entities.Adopcion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/adopcion")
public class AdopcionRestController {

    @Autowired
    AdopcionDao adopdao;

    

    private final UsuarioAuthProvider usuarioAuthProvider; 

    public AdopcionRestController(UsuarioAuthProvider usuarioAuthProvider) {
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    @GetMapping("/todas")
    public ResponseEntity<Iterable<Adopcion>> listadoAdopciones() {

        Iterable<Adopcion> adopciones = adopdao.buscarTodas();
        if (adopciones != null){
            return new ResponseEntity<>(adopciones, HttpStatus.OK);     
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        }
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Adopcion> getAdopcion(@PathVariable("id") int id) {
        Adopcion adopcion = adopdao.buscarAdopcionId(id);
        return new ResponseEntity<>(adopcion, HttpStatus.OK);
    }



  
}
