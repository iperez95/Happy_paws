package com.tfgunir.happypaws.rest.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.dto.ProtectoraDto;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.repository.ProtectoraRepository;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/")
public class PanelAdminRestController {

    @Autowired
    ProtectoraDao protdao;

    @Autowired
    ProtectoraRepository protrepo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioAuthProvider usuarioAuthProvider;
    
    public PanelAdminRestController(UsuarioAuthProvider usuarioAuthProvider) {
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    // LISTADO PROTECTORAS DTO CON MUNICIPIO Y PROVINCIA
    @GetMapping(path="/protectoras/todas", produces = "application/json")
    public ResponseEntity<List<ProtectoraDto>> listadoProtectoras (){
       
        List<Protectora> protectoras = protrepo.findAll();

        if (protectoras!=null){
            List<ProtectoraDto> protectorasDto = new ArrayList<>();
            for (Protectora protectora : protectoras) {                
                protectorasDto.add(protdao.convertirProtectoraDto(protectora));
            }

        //Ordena las protectoras por Provincia    
        protectorasDto.sort(Comparator.comparing(ProtectoraDto::getNombreProvincia)); 
           
        return new ResponseEntity<>(protectorasDto, HttpStatus.OK);     
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    // BUSQUEDA PROTECTORAS POR NOMBRE
    @GetMapping(path="/protectoras/busquedapornombre/{nombre}", produces = "application/json")
    public ResponseEntity<List<ProtectoraDto>> busquedaProtectoraPorNombre (@PathVariable ("nombre") String nombre){
       
        List<Protectora> protectoras = protdao.buscarPorNombreContiene("%" + nombre + "%");

        if (protectoras!=null){
            List<ProtectoraDto> protectorasDto = new ArrayList<>();
            for (Protectora protectora : protectoras) {                
                protectorasDto.add(protdao.convertirProtectoraDto(protectora));
            }

        //Ordena las protectoras por Provincia    
        protectorasDto.sort(Comparator.comparing(ProtectoraDto::getNombreProvincia)); 
           
        return new ResponseEntity<>(protectorasDto, HttpStatus.OK);     
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

     // MODIFICAR PROTECTORA
    @PutMapping(path="/protectora/modificar/{id}", consumes = "application/json")
    public ResponseEntity<Protectora> modificarUnaProtectora(@PathVariable("id")int id, @RequestBody Protectora detalleProtectora){ 
        
        System.out.println("Buscando protectora con id: "+id);
        Protectora protectora = protdao.buscarProtectoraId(id);

        protectora.setNombre(detalleProtectora.getNombre());
        protectora.setDireccion(detalleProtectora.getDireccion());
        protectora.setDescripcion(detalleProtectora.getDescripcion());
        protectora.setEmail(detalleProtectora.getEmail());
        protectora.setTelefono(detalleProtectora.getTelefono());

        Protectora protectoraActualizada = protdao.actualizarProtectora(protectora);

        if (protectoraActualizada!=null)
            return ResponseEntity.ok(protectoraActualizada);
        else
            return ResponseEntity.notFound().build();
    }

    //CAMBIAR ESTADO PROTECTORA >> INACTIVO
    @PutMapping(path="/protectora/inactivar/{id}")
    public ResponseEntity<Protectora> inactivarProtectora (@PathVariable("id") int id){

        System.out.println("Buscando protectora con id: "+id);

        Protectora protectora = protdao.buscarProtectoraId(id);
        System.out.println("Protectora encontrada: "+protectora);

        if (protectora!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(2);
            System.out.println("Estado protectora temporal: "+estadoProtectoraTemporal);

            protectora.setEstadosprotectora(estadoProtectoraTemporal);
            System.out.println("Protectora con estado actualizado: "+protectora);

            protdao.actualizarProtectora(protectora);
            return ResponseEntity.ok(protectora);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

        //CAMBIAR ESTADO PROTECTORA >> ACTIVO
    @PutMapping(path="/protectora/activar/{id}")
    public ResponseEntity<Protectora> activarProtectora (@PathVariable("id") int id){

        System.out.println("Buscando protectora con id: "+id);

        Protectora protectora = protdao.buscarProtectoraId(id);
        System.out.println("Protectora encontrada: "+protectora);

        if (protectora!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(1);
            System.out.println("Estado protectora temporal: "+estadoProtectoraTemporal);

            protectora.setEstadosprotectora(estadoProtectoraTemporal);
            System.out.println("Protectora con estado actualizado: "+protectora);

            protdao.actualizarProtectora(protectora);
            return ResponseEntity.ok(protectora);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

        //CAMBIAR ESTADO PROTECTORA >> PENDIENTE
    @PutMapping(path="/protectora/pendiente/{id}")
    public ResponseEntity<Protectora> pendienteProtectora (@PathVariable("id") int id){

        System.out.println("Buscando protectora con id: "+id);

        Protectora protectora = protdao.buscarProtectoraId(id);
        System.out.println("Protectora encontrada: "+protectora);

        if (protectora!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(3);
            System.out.println("Estado protectora temporal: "+estadoProtectoraTemporal);

            protectora.setEstadosprotectora(estadoProtectoraTemporal);
            System.out.println("Protectora con estado actualizado: "+protectora);

            protdao.actualizarProtectora(protectora);
            return ResponseEntity.ok(protectora);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

}
