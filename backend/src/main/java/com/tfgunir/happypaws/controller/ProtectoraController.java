package com.tfgunir.happypaws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;

@RestController
// TODO DAV comprobar si realmente es necesario el CrossOrigin
@CrossOrigin(origins ="*")
@RequestMapping("/protectora")
public class ProtectoraController {

    @Autowired
    ProtectoraDao protdao;    

    // DETALLE PROTECTORA 
    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<Protectora> buscarProtectoraId (@PathVariable("id")int id){
        System.out.println("Buscando protectora con id: "+id);
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null)
            return ResponseEntity.ok(p);
        else
            return ResponseEntity.notFound().build();
      
    }

    // LISTADO PROTECTORAS
    @GetMapping(path="/gestion/listado", produces = "application/json")
    public ResponseEntity<Iterable<Protectora>> listadoProtectoras (){
       
        Iterable<Protectora> listado = protdao.listadoProtectoras();
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }


     // LISTADO PROTECTORAS CON MUNICIPIO Y PROVINCIA
    @GetMapping(path="/listadofront", produces = "application/json")
    public ResponseEntity<Iterable<Protectora>> listadoProtectorasMunicProv (){
       
        Iterable<Protectora> listado = protdao.listadoProtectorasMunicProv();
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }

    // LISTADO PROTECTORAS POR ID PROVINCIA
    @GetMapping(path="/porprovincia/{id}", produces = "application/json")
    public ResponseEntity<Iterable<Protectora>> buscarPorProvincia (@PathVariable("id") int id){
       
        Iterable<Protectora> listado = protdao.buscarPorIdProvincia(id);
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }

    // LISTADO PROTECTORAS POR NOMBRE PROVINCIA
    @GetMapping(path="/listadofront/provincia/{nombre}", produces = "application/json")
    public ResponseEntity<Iterable<Protectora>> porNombreProvincia (@PathVariable("nombre") String nombre){
       
        Iterable<Protectora> listado = protdao.buscarPorNombreProvincia(nombre);
        if (listado!=null)
            return ResponseEntity.ok(listado);
        else
            return ResponseEntity.notFound().build();
    }

    //TODO DAV comprobar que solo los usuarios tipo protectora pueden hacer esto
    // ALTA PROTECTORA
    @PostMapping(path="/gestion/alta", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Protectora> altaProtectora (@RequestBody Protectora p){
        protdao.altaProtectora(p);
        if (p!=null)
            return ResponseEntity.created(null).body(p);    
        else
            return ResponseEntity.badRequest().build();
    }

    //TODO DAV comprobar que solo los usuarios tipo protectora pueden hacer esto
    // MODIFICAR PROTECTORA
    @PutMapping(path="/gestion/modificar", consumes = "application/json")
    public ResponseEntity<Protectora> modificarProtectora (@PathVariable("id") int id, @RequestBody Protectora p){
        Protectora protUpdate= protdao.buscarProtectoraId(id);
        if (protUpdate!=null) {
            p = protdao.modificarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

    //BORRAR UNA PROTECTORA
    @DeleteMapping(path="/gestion/borrar/{id}")
    public ResponseEntity<Protectora> borrarProtectora (@PathVariable("id") int id){
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null) {
            protdao.borrarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

    //CAMBIAR ESTADO PROTECTORA >> ACTIVO
    @PutMapping(path="/gestion/activar/{id}")
    public ResponseEntity<Protectora> activarProtectora (@PathVariable("id") int id){
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(1);
            p.setEstadosprotectora(estadoProtectoraTemporal);
            protdao.modificarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

    //CAMBIAR ESTADO PROTECTORA >> PENDIENTE
    @PutMapping(path="/gestion/pendiente/{id}")
    public ResponseEntity<Protectora> pendienteProtectora (@PathVariable("id") int id){
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(3);
            p.setEstadosprotectora(estadoProtectoraTemporal);
            protdao.modificarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

    //CAMBIAR ESTADO PROTECTORA >> INACTIVO
    @PutMapping(path="/gestion/inactivar/{id}")
    public ResponseEntity<Protectora> inactivarProtectora (@PathVariable("id") int id){
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(2);
            p.setEstadosprotectora(estadoProtectoraTemporal);
            protdao.modificarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }
    
    
    
}
