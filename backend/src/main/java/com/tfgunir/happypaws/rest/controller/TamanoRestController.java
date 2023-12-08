package com.tfgunir.happypaws.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tfgunir.happypaws.modelo.dao.TamanoDao;
import com.tfgunir.happypaws.modelo.entities.Tamano;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/atributos")
@CrossOrigin(origins ="*")

public class TamanoRestController {

    @Autowired
    private TamanoDao tamDao;

    // Controlador para listado de tamaños
    @GetMapping(path = "/tamanos", produces = "application/json")
    public ResponseEntity<List<Tamano>> listarTamaños() {
        List<Tamano> listado = tamDao.buscarTodos();
        if (listado!= null &&!listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar tamaño por id
    @GetMapping(path = "/tamanoa/buscar/{id}", produces = "application/json")
    public ResponseEntity<Tamano> buscarTamanoId(@PathVariable int id) {
        Tamano tamano = tamDao.buscarTamanoId(id);
        if (tamano != null) {
            return ResponseEntity.ok(tamano);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
    


    

