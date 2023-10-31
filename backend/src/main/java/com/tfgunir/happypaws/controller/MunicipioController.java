package com.tfgunir.happypaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.MunicipioDao;
import com.tfgunir.happypaws.modelo.entities.Municipio;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    MunicipioDao munidao;

    @GetMapping(path="/todos", produces = "application/json")
    public ResponseEntity<Iterable<Municipio>> listadoMunicipios (){
       
        Iterable<Municipio> listado = munidao.listadoMunicipios();
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(path="/porprovincia/{idprov}", produces = "application/json")
    public ResponseEntity<Iterable<Municipio>> listadoMunicipiosPorIdProv (@PathVariable("idprov") int idprov){
       
        Iterable<Municipio> listado = munidao.municipiosPorIdProvincia(idprov);
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }


    
}
