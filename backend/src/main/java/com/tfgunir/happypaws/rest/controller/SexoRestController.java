package com.tfgunir.happypaws.rest.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.tfgunir.happypaws.modelo.dao.SexoDao;
import com.tfgunir.happypaws.modelo.entities.Sexo;

@Controller
@RequestMapping("/atributos")
@CrossOrigin(origins ="*")

public class SexoRestController {

    @Autowired
    private SexoDao sexDao;
    
    //Controlador para el listado de sexos
    @GetMapping(path = "/sexos", produces ="application/json")
    public ResponseEntity<List<Sexo>> listadosexos(){
        List<Sexo> listado = sexDao.buscarTodos();
            if (listado != null && !listado.isEmpty()) {
        return ResponseEntity.ok(listado);
    } else {
        return ResponseEntity.notFound().build();
    }
    }

    //Controlador para buscar sexo por id
    @GetMapping(path = "/sexoa/buscar/{id}", produces ="application/json")
    public ResponseEntity<Sexo> buscarSexoId(@PathVariable int id){
        Sexo sexo = sexDao.buscarSexoId(id);
        if (sexo!= null) {
            return ResponseEntity.ok(sexo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
