package com.tfgunir.happypaws.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.EspecieDao;
import com.tfgunir.happypaws.modelo.entities.Especie;

@RestController
@RequestMapping("/atributos")
@CrossOrigin(origins ="*")

public class EspecieRestController {

    @Autowired
    private EspecieDao espDao;

     //Controlador para ver listado de especies
     @GetMapping(path="/especies", produces = "application/json")
     public ResponseEntity<List<Especie>> listadoEspecie() {
        
         List<Especie> listado = espDao.buscarTodos();
         if (listado!= null &&!listado.isEmpty()) {
             return ResponseEntity.ok(listado);
         } else {
             return ResponseEntity.notFound().build();
         }
     }

     //Controlador para buscar especie por id
     @GetMapping(path="/especies/buscar/{id}", produces = "application/json")
     public ResponseEntity<Especie> buscarEspecie(@PathVariable("idespecie") int idespecie) {
        
         Especie especie = espDao.buscarEspecieId(idespecie);
         if (especie!= null) {
             return ResponseEntity.ok(especie);
         } else {
             return ResponseEntity.notFound().build();
         }
     }
    
}
