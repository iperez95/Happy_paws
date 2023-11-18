package com.tfgunir.happypaws.rest.controller;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.AdopcionDao;
import com.tfgunir.happypaws.modelo.dto.AdopcionDto;
import com.tfgunir.happypaws.modelo.entities.Adopcion;
import com.tfgunir.happypaws.modelo.repository.AdopcionRepository;

import java.util.ArrayList;
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
    @Autowired
    AdopcionRepository adoprepo;

    

    private final UsuarioAuthProvider usuarioAuthProvider; 

    public AdopcionRestController(UsuarioAuthProvider usuarioAuthProvider) {
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    //Obtiene un listado de todas las adopciones.
    @GetMapping("/todas")
    public ResponseEntity<Iterable<AdopcionDto>> todasAdopciones() {
        Iterable<Adopcion> adopciones = adopdao.buscarTodasAdopciones();
        
        if (adopciones != null){
            List<AdopcionDto> adopcionesDto = new ArrayList<>();
            for (Adopcion adopcion : adopciones) {
                adopcionesDto.add(adopdao.convertirAdopcionDto(adopcion));
            }
            return new ResponseEntity<>(adopcionesDto, HttpStatus.OK);     
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        }
    }

    //Obtiene todas las adopciones de una protectora por el id de la protectora
    @GetMapping(path="/protectora/{id}", produces = "application/json")
    public ResponseEntity<Iterable<AdopcionDto>> todasAdopcionesProtectoraId(@PathVariable("id") int idProtectora) {
        Iterable<Adopcion> adopciones = adopdao.adopcionesPorIDProtectora(idProtectora);
     
        if (adopciones != null){
            List <AdopcionDto> adopcionesDto = new ArrayList<>();
            for (Adopcion adopcion : adopciones) {
                adopcionesDto.add(adopdao.convertirAdopcionDto(adopcion));
            }
            return new ResponseEntity<>(adopcionesDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Obtiene una adopcion por su id de adopcion.
    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<AdopcionDto> adopcionPorID(@PathVariable("id") int idAdopcion) {
        Adopcion adopcion = adopdao.buscarAdopcionId(idAdopcion);
        if (adopcion != null){
            AdopcionDto adopcionDto = adopdao.convertirAdopcionDto(adopcion);
            return ResponseEntity.ok(adopcionDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

 

    


  
}
