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
import com.tfgunir.happypaws.modelo.dao.AnimalDao;
import com.tfgunir.happypaws.modelo.dao.EspecieDao;
import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.dao.RazaDao;
import com.tfgunir.happypaws.modelo.dao.SexoDao;
import com.tfgunir.happypaws.modelo.dao.TamanoDao;
import com.tfgunir.happypaws.modelo.dao.UsuarioDao;
import com.tfgunir.happypaws.modelo.dto.AnimalDto;
import com.tfgunir.happypaws.modelo.dto.ProtectoraDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.entities.Especie;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Municipio;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.entities.Provincia;
import com.tfgunir.happypaws.modelo.entities.Raza;
import com.tfgunir.happypaws.modelo.entities.Sexo;
import com.tfgunir.happypaws.modelo.entities.Tamano;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.ProtectoraRepository;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class PanelAdminRestController {

    @Autowired
    ProtectoraDao protdao;

    @Autowired
    ProtectoraRepository protrepo;

    @Autowired
    AnimalDao anidao;

    @Autowired
    RazaDao razadao;

    @Autowired
    EspecieDao espedao;

    @Autowired
    SexoDao sexodao;

    @Autowired
    TamanoDao tamaDao;

    @Autowired
    private UsuarioDao usuarioDao;

    private final UsuarioAuthProvider usuarioAuthProvider;
    
    public PanelAdminRestController(UsuarioAuthProvider usuarioAuthProvider) {
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

// ****************************** PROTECTORAS ************************************

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

    // LISTADO PROTECTORAS DTO CON MUNICIPIO Y PROVINCIA
    @GetMapping(path="/usuarios/todos", produces = "application/json")
    public ResponseEntity<List<UsuarioDto>> listadoUsuarios (){
       
        List<Usuario> usuarios = usuarioDao.buscarTodos();

        if (usuarios!=null){
            List<UsuarioDto> usuariosDto = new ArrayList<>();
            for (Usuario usuario : usuarios) {                
                usuariosDto.add(usuarioDao.convertirADto(usuario));
            }

            return new ResponseEntity<>(usuariosDto, HttpStatus.OK);     
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    // BUSQUEDA PROTECTORAS POR NOMBRE
    @GetMapping(path="/usuarios/busquedaporemail/{email}", produces = "application/json")
    public ResponseEntity<List<UsuarioDto>> busquedaUsuarioPorEmail(@PathVariable ("email") String email){
       
        List<Usuario> usuarios = usuarioDao.buscarPorEmailContiene("%" + email + "%");

        if (usuarios!=null){
            List<UsuarioDto> usuariosDtos = new ArrayList<>();
            for (Usuario usuario : usuarios) {                
                usuariosDtos.add(usuarioDao.convertirADto(usuario));
            }
           
            return new ResponseEntity<>(usuariosDtos, HttpStatus.OK);     
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

    // MODIFICAR Usuarios
    @PutMapping(path="/usuario/modificar/{id}", consumes = "application/json")
    public ResponseEntity<?> modificarUnUsuario(@PathVariable("id")int id, @RequestBody Usuario detalleUsuario) {
        System.out.println("Buscando usuario con id: "+id);
        Usuario usuario = usuarioDao.buscarUnUsuario(id);

        if (usuario==null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setNombre(detalleUsuario.getNombre());
        usuario.setDireccion(detalleUsuario.getDireccion());
        usuario.setDni(detalleUsuario.getDni());
        usuario.setEmail(detalleUsuario.getEmail());
        usuario.setTelefono(detalleUsuario.getTelefono());

        int usuarioActualizado = usuarioDao.actualizar(usuario);

        if (usuarioActualizado == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();            
        }
    }

    @PutMapping(path="/usuario/cambiarestado/{id}")
    public ResponseEntity<UsuarioDto> cambiarEstadoUsuario(@PathVariable("id") int idusuario){
        Usuario usuario = usuarioDao.buscarUnUsuario(idusuario);
        if (usuario==null) {
            return ResponseEntity.notFound().build();
        }
        if (usuario.getEnabled()==1) {
            usuario.setEnabled((byte) 0);
        } else {
            usuario.setEnabled((byte) 1);
        }
        int usuarioActualizado = usuarioDao.actualizar(usuario);
        if (usuarioActualizado == 1) {
            return ResponseEntity.ok(usuarioDao.convertirADto(usuario));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();            
        }
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

// ****************************** ANIMALES ************************************
 @PutMapping(path="/animal/activar/{id}")
    public ResponseEntity<Animal> activarAnimal (@PathVariable("id") int id){

        Animal animal = anidao.buscarAnimalId(id);

        if (animal!=null) {
            animal.setEnabled(true);
            anidao.modificarAnimal(animal);


            return ResponseEntity.ok(animal);    
        }
        else
            return ResponseEntity.notFound().build();        
    }


 @PutMapping(path="/animal/inactivar/{id}")
    public ResponseEntity<Animal> inactivarAnimal (@PathVariable("id") int id){

        Animal animal = anidao.buscarAnimalId(id);

        if (animal!=null) {
            animal.setEnabled(false);
            anidao.modificarAnimal(animal);

            return ResponseEntity.ok(animal);    
        }
        else
            return ResponseEntity.notFound().build();        
    }


    //MODIFICAR ANIMAL
    @PutMapping(path="/animal/modificar/{id}")
    public ResponseEntity<Animal> modificarAnimal(@PathVariable("id")int id, @RequestBody Animal animalRecibido){
           System.out.println("************* AnimalRecibido: "+animalRecibido);

        Animal animal = anidao.buscarAnimalId(id);
        System.out.println("************* Animal encontrado: "+animal);

        if (animal!=null){
            animal.setNombre(animalRecibido.getNombre());
            animal.setDescripcion(animalRecibido.getDescripcion());
            // animal.setFechaNacimiento(animalDtoRecibido.getFechaNacimiento());
            // animal.setFechaAlta(animalDtoRecibido.getFechaAlta());
            // animal.setEnvio(animalDtoRecibido.isEnvio());
            animal.setRaza(razadao.buscarRazaId(animalRecibido.getRaza().getIdraza()));
            animal.setSexo(sexodao.buscarSexoId(animalRecibido.getSexo().getIdsexo()));
            animal.setTamano(tamaDao.buscarTamanoId(animalRecibido.getTamano().getIdtamano()));
            System.out.println("************ Animal modificado: "+animal);       

        }        
        if (anidao.modificarAnimal(animal))
            return ResponseEntity.ok(animal);
        else
            return ResponseEntity.notFound().build();

    }      
    
    // BUSQUEDA ANIMALES POR NOMBRE
    @GetMapping(path="/animales/busquedapornombre/{nombre}", produces = "application/json")
    public ResponseEntity<List<AnimalDto>> busquedaAnimalPorNombre (@PathVariable ("nombre") String nombre){
        
        List<Animal> animales = anidao.buscarPorNombreContiene("%" + nombre + "%");

        if (animales!=null){
            List<AnimalDto> animalesDto = new ArrayList<>();
            for (Animal animal : animales) {                
                animalesDto.add(anidao.convertirAnimalDto(animal));
            }

        // //Ordena l por Provincia    
        // protectorasDto.sort(Comparator.comparing(ProtectoraDto::getNombreProvincia)); 
           
        return new ResponseEntity<>(animalesDto, HttpStatus.OK);     
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
    



}
