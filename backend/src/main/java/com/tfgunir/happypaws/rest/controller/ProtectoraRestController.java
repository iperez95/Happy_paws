package com.tfgunir.happypaws.rest.controller;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.ContactForm;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

@RestController
// TODO DAV comprobar si realmente es necesario el CrossOrigin
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

    
    // ALTA PROTECTORA FUNCIONANDO PERO SIN AGREGAR EL USUARIO QUE LO DA DE ALTA
    // @PostMapping(path="/alta", produces = "application/json", consumes = "application/json")
    // public ResponseEntity<Protectora> altaProtectora (@RequestBody Protectora p ){
    //     protdao.altaProtectora(p);
    //     if (p!=null)
    //         return ResponseEntity.created(null).body(p);    
    //     else
    //         return ResponseEntity.badRequest().build();
    // }

    //TODO DAV comprobar que solo los usuarios tipo protectora pueden hacer esto
    // ALTA PROTECTORA INTENTANDO QUE AGREGUE EL USUARIO DE LA SESIÓN
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


    //Añadir logo protectora una vez creada
    @PutMapping(path="/gestion/subirlogo")
    public ResponseEntity<Protectora> subirLogo(@RequestParam("id") int id, @RequestParam("logo") MultipartFile logo) {
        if (!logo.isEmpty()) {
            String nombreLogo = String.valueOf(id);
            Path urlLogo = Paths.get("uploads/" + id + "/").resolve(nombreLogo).toAbsolutePath();
            try {
                Files.createDirectories(urlLogo.getParent());
                Files.copy(logo.getInputStream(), urlLogo);
                protdao.subirLogo(id, urlLogo.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   
        return ResponseEntity.ok().build();
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
        Protectora p = protdao.buscarProtectoraId(id);
        if (p!=null) {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(2);
            p.setEstadosprotectora(estadoProtectoraTemporal);
            protdao.actualizarProtectora(p);
            return ResponseEntity.ok(p);    
        }
        else
            return ResponseEntity.notFound().build();        
    }

    // FORMULARIO CONTACTO PROTECTORA
    @PostMapping("/contacto/{idProtectora}")
    public String manejoEnvioformulario(@RequestBody ContactForm form, @PathVariable int idProtectora) {
        // Aquí podemos añadir la validacion del back del formulario.

        // Llama al método para enviar el forulario a la protectora por su id
        sendEmail(form,idProtectora);

        return "¡Formulario enviado con éxito!";
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
    
    
}
