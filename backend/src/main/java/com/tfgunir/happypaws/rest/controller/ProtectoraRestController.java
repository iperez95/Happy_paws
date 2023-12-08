package com.tfgunir.happypaws.rest.controller;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.dto.ProtectoraDto;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.ContactForm;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/protectora")
public class ProtectoraRestController {

    @Autowired
    ProtectoraDao protdao;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender emailSender; 

    private final UsuarioAuthProvider usuarioAuthProvider;

    public ProtectoraRestController(UsuarioAuthProvider usuarioAuthProvider){
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    // DETALLE PROTECTORA SIN DTO
    // @GetMapping(path="/{id}", produces = "application/json")
    // public ResponseEntity<Protectora> buscarProtectoraId (@PathVariable("id")int id){
    //     System.out.println("Buscando protectora con id: "+id);
    //     Protectora p = protdao.buscarProtectoraId(id);
    //     if (p!=null)
    //         return ResponseEntity.ok(p);
    //     else
    //         return ResponseEntity.notFound().build();
    // }

    // DETALLE PROTECTORA DTO
    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<ProtectoraDto> buscarProtectoraId (@PathVariable("id")int id){
        System.out.println("Buscando protectora con id: "+id);

        Protectora protectora = protdao.buscarProtectoraId(id);

        if (protectora!=null){
            ProtectoraDto protectoraDto = protdao.convertirProtectoraDto(protectora);
            return ResponseEntity.ok(protectoraDto);
        } else {
            return ResponseEntity.notFound().build();
        }
            
    }

    @GetMapping(path="/usuario/{id}", produces = "application/json")
    public ResponseEntity<Protectora> buscarPorUsuarioId(@PathVariable("id")int id) {
        Protectora p = protdao.buscarProtectoraPorUsuario(id);
        if (p!=null)
            return ResponseEntity.ok(p);
        else
            return ResponseEntity.notFound().build();

    }

    // LISTADO PROTECTORAS
    // @GetMapping(path="/gestion/listado", produces = "application/json")
    // public ResponseEntity<Iterable<Protectora>> listadoProtectoras (){
       
    //     Iterable<Protectora> listado = protdao.listadoProtectoras();
    //     if (listado!=null)
    //         return ResponseEntity.ok(listado);
    //     else
    //         return ResponseEntity.notFound().build();
    // }


     // LISTADO PROTECTORAS SIN DTO  CON MUNICIPIO Y PROVINCIA
    // @GetMapping(path="/listadofront", produces = "application/json")
    // public ResponseEntity<Iterable<Protectora>> listadoProtectorasMunicProv (){
       
    //     Iterable<Protectora> listado = protdao.listadoProtectorasMunicProv();
    //     if (listado!=null)
    //         return ResponseEntity.ok(listado);
    //     else
    //         return ResponseEntity.notFound().build();
    // }

    // LISTADO PROTECTORAS DTO CON MUNICIPIO Y PROVINCIA
    @GetMapping(path="/listadofront", produces = "application/json")
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

    // LISTADO PROTECTORAS SIN DTO POR ID PROVINCIA
    // @GetMapping(path="/porprovincia/{id}", produces = "application/json")
    // public ResponseEntity<Iterable<Protectora>> buscarPorProvincia (@PathVariable("id") int id){
       
    //     Iterable<Protectora> listado = protdao.buscarPorIdProvincia(id);
    //     if (listado!=null)
    //         return ResponseEntity.ok(listado);
    //     else
    //         return ResponseEntity.notFound().build();
    // }

    // LISTADO PROTECTORAS CON DTO POR ID PROVINCIA
    @GetMapping(path="/porprovincia/{id}", produces = "application/json")
    public ResponseEntity<Iterable<ProtectoraDto>> buscarPorProvincia (@PathVariable("id") int id){
       
        Iterable<Protectora> protectoras = protdao.buscarPorIdProvincia(id);
        if (protectoras!=null){
            List<ProtectoraDto> listadoDto = new ArrayList<>();
            for (Protectora protectora : protectoras) {                
                listadoDto.add(protdao.convertirProtectoraDto(protectora));
            }
            return new ResponseEntity<>(listadoDto, HttpStatus.OK);     
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }   
    }


    // LISTADO PROTECTORAS SIN DTO POR NOMBRE PROVINCIA
    // @GetMapping(path="/listadofront/provincia/{nombre}", produces = "application/json")
    // public ResponseEntity<Iterable<Protectora>> porNombreProvincia (@PathVariable("nombre") String nombre){
       
    //     Iterable<Protectora> listado = protdao.buscarPorNombreProvincia(nombre);
    //     if (listado!=null)
    //         return ResponseEntity.ok(listado);
    //     else
    //         return ResponseEntity.notFound().build();
    // }

    // LISTADO PROTECTORAS DTO POR NOMBRE PROVINCIA
    @GetMapping(path="/listadofront/provincia/{nombre}", produces = "application/json")
    public ResponseEntity<Iterable<ProtectoraDto>> porNombreProvincia (@PathVariable("nombre") String nombre){
        Iterable<Protectora> protectoras = protdao.buscarPorNombreProvincia(nombre);
        if (protectoras!=null){
            List<ProtectoraDto> listadoDto = new ArrayList<>();
            for (Protectora protectora : protectoras) {                
                listadoDto.add(protdao.convertirProtectoraDto(protectora));
            }
            return new ResponseEntity<>(listadoDto, HttpStatus.OK);     
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }


    //TODO DAV comprobar que solo los usuarios tipo protectora pueden hacer esto
    // ALTA PROTECTORA QUE AGREGA EL USUARIO EN SESIÓN
    @PostMapping("/alta")
    public ResponseEntity<Protectora> altaProtectora(@RequestBody Protectora p,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.substring(7);

            // Valida el token y obtén el objeto Authentication
            Authentication auth = usuarioAuthProvider.validateToken(token);

            // Obtén el correo electrónico del usuario
            UsuarioDto usuarioDto = (UsuarioDto) auth.getPrincipal();
            System.out.println("Usuario en token: " + usuarioDto);
            String email = usuarioDto.getEmail();
            System.out.println("email en token: " + email);

            // Encuentra el usuario en la base de datos
            Usuario usuario = usuarioRepository.findByEmail(email);
            System.out.println("Usuario en BD: " + usuario);

            p.setUsuario(usuario);

            System.out.println("Email del usuario que da de alta la protectora: " + email);

            protdao.altaProtectora(p);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //TODO DAV comprobar que solo el usuario que gestiona la protectora hacer esto
    // MODIFICAR PROTECTORA
    @PutMapping(path="/gestion/modificar/{id}", consumes = "application/json")
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

   /*BORRAR UNA PROTECTORA
    Este método no esta implementado porque se ha decidido que no se pueda borrar una protectora para 
    poder mantener los históricos de las adopciones o animales.*/
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
            protdao.actualizarProtectora(p);
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
            protdao.actualizarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

    //CAMBIAR ESTADO PROTECTORA >> INACTIVO
    @PutMapping(path="/gestion/inactivar/{id}")
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

    // FORMULARIO CONTACTO PROTECTORA
    @PostMapping("/contacto/{idProtectora}")
    public ResponseEntity<Map<String, String>> manejoEnvioformulario(@RequestBody ContactForm form, @PathVariable int idProtectora) {
        Map<String, String> response = new HashMap<>();
        try {
            // Esto proceso y envía el formulario
            sendEmail(form,idProtectora);
            response.put("message", "¡Tu email ha sido enviado correctamente!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MailException e) {
            // manejo de la excepción
            response.put("message", "Error al enviar el mensaje");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	//Método para enviar el formulario al email de HappyPaws
    private void sendEmail(ContactForm form, int idProtectora) {
        Protectora protectora = protdao.buscarProtectoraId(idProtectora);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(protectora.getEmail());
        message.setSubject("Mensaje " + form.getName());
        message.setText("Correo electrónico: " + form.getEmail() + "\n\n" + form.getMessage());
        emailSender.send(message);
    }   

    // SUBIR FOTO PROTECTORA 
    @GetMapping(path="/gestion/subirfoto/{id}", produces = "application/json")
    public ResponseEntity<Protectora> subirFoto (@PathVariable("id")int id){
        System.out.println("Buscando protectora con id: "+id);
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null)
            return ResponseEntity.ok(p);
        else
            return ResponseEntity.notFound().build();
    }

    //AÑADE EL LOGO SUBIDO A LA PROTECTRA
    @PostMapping(path="/gestion/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") int id) {
        Map<String, Object> response = new HashMap<>();

        Protectora protectora = protdao.buscarProtectoraId(id);

        if (!archivo.isEmpty()) {
        
            String nombreArchivo = UUID.randomUUID().toString() +"_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("..//frontend//src//assets//images//protectora//").resolve(nombreArchivo).toAbsolutePath();

            try {
                //Files.createDirectories(rutaArchivo.getParent());
                Files.copy(archivo.getInputStream(), rutaArchivo);
                protectora.setUrlLogo("/assets/images/protectora/"+nombreArchivo);
                protdao.actualizarProtectora(protectora);

                response.put("protectora", protectora);
                response.put("mensaje", "Has subido correctamente el logo: " + nombreArchivo);

            } catch (IOException e) {
                response.put("mensaje", "Error al subir el logo: " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    
    
}
