package com.tfgunir.happypaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.ProvinciaDao;
import com.tfgunir.happypaws.modelo.entities.Provincia;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    ProvinciaDao provdao;

    @GetMapping(path="/todas", produces = "application/json")
    public ResponseEntity<Iterable<Provincia>> listadoProvincias (){
       
        Iterable<Provincia> listado = provdao.listadoProvincias();
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }
    
}
