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
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/")
public class PanelAdminRestController {

    @Autowired
    ProtectoraDao protdao;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioAuthProvider usuarioAuthProvider;
    
    public PanelAdminRestController(UsuarioAuthProvider usuarioAuthProvider) {
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    // LISTADO PROTECTORAS DTO CON MUNICIPIO Y PROVINCIA
    @GetMapping(path="/protectoras/todas", produces = "application/json")
    public ResponseEntity<List<ProtectoraDto>> listadoProtectorasMunicProv (){
       
        List<Protectora> protectoras = protdao.listadoProtectorasMunicProv();

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
}
