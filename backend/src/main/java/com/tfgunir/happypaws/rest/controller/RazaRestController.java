package com.tfgunir.happypaws.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.RazaDao;
import com.tfgunir.happypaws.modelo.entities.Raza;

@RestController
@RequestMapping("/atributos")
@CrossOrigin(origins ="*")

public class RazaRestController {

         @Autowired
     private RazaDao razDao;

     //Controlador para listado de razas
     @GetMapping(path="/razas", produces = "application/json")
     public ResponseEntity<List<Raza>> listarRazas() {
        
         List<Raza> listado = razDao.buscarTodos();
         if (listado!= null &&!listado.isEmpty()) {
             return ResponseEntity.ok(listado);
         } else {
             return ResponseEntity.notFound().build();
         }
     }
    
     //Controlador para buscar raza por id
     @GetMapping(path="/razas/buscar/{id}", produces = "application/json")
     public ResponseEntity<Raza> buscarRazaId(@PathVariable int id) {
        
         Raza raza = razDao.buscarRazaId(id);
         if (raza!= null) {
             return ResponseEntity.ok(raza);
         } else {
             return ResponseEntity.notFound().build();
         }
     }

     //Controlador para buscar razas por Id de especie
    @GetMapping(path="/razasDeUnaEspecie/{idespecie}", produces = "application/json")
    public ResponseEntity<Iterable<Raza>> listadoRazasPorIdEspecie (@PathVariable("idespecie") int idespecie) {
       
        Iterable<Raza> listado = razDao.razasPorIdEspecie(idespecie);
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }

}
