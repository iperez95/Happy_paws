package com.tfgunir.happypaws.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.configuracion.UsuarioAuthProvider;
import com.tfgunir.happypaws.modelo.dao.CuestionarioDao;
import com.tfgunir.happypaws.modelo.dto.UsuarioDto;
import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;
import com.tfgunir.happypaws.modelo.entities.Usuario;
import com.tfgunir.happypaws.modelo.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/cuestionario")
public class CuestionarioRestController {

    private final UsuarioAuthProvider usuarioAuthProvider;

    public CuestionarioRestController(UsuarioAuthProvider usuarioAuthProvider){
        this.usuarioAuthProvider = usuarioAuthProvider;
    }

    @Autowired
    CuestionarioDao cuestDao;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/preguntas")
    public ResponseEntity<List<PreguntasAdoptante>> todasPreguntas() {
        List<PreguntasAdoptante> preguntas = cuestDao.todasLasPreguntas();
        if (preguntas!=null)
            return ResponseEntity.ok(preguntas);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(path="/pregunta/{idPregunta}" , produces = "application/json")
    public ResponseEntity<PreguntasAdoptante> buscarPregunta(@PathVariable("idPregunta") int idPregunta) {
        PreguntasAdoptante pregunta = cuestDao.buscarPregunta(idPregunta);
        if (pregunta!=null)
            return ResponseEntity.ok(pregunta);
        else
            return ResponseEntity.notFound().build();
    }

    
    //GUARDA LAS RESPUESTAS DE UN USUARIO
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarCuestionario(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody List<RespuestasAdoptante> respuestas) {
        try {
            // Obtén el token del encabezado de la solicitud
            String token = authorizationHeader.substring("Bearer ".length());
    
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
    
            // Añade el usuario a cada respuesta y guarda la respuesta
            for (RespuestasAdoptante respuesta : respuestas) {
                respuesta.setUsuario(usuario);
                cuestDao.guardarRespuesta(respuesta);

            }
            //  cuestDao.guardarRespuestas(respuestas);
            respuestas.forEach(System.out::println);
    
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    // ESTE POST ESTA FUNCIONANDO PERO NO RECOGE EL USUARIO DE SESION
    // @PostMapping("/guardar")
    // public ResponseEntity<RespuestasAdoptante> recibirRespuestas(@RequestBody List<RespuestasAdoptante> respuestas) {
    //     try {
    //         // Lógica para guardar las respuestas
    //         cuestDao.guardarRespuestas(respuestas);
    //         return ResponseEntity.ok().build();
    //             } 
    //         catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();                }
    // }


}



 

   
    
    

    
    

