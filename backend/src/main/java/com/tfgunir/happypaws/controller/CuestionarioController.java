package com.tfgunir.happypaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.CuestionarioDao;
import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;
import com.tfgunir.happypaws.modelo.entities.Usuario;


import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/cuestionario")
public class CuestionarioController {
    @Autowired
    CuestionarioDao cuestDao;


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

    @PostMapping("/guardar")
    public ResponseEntity<RespuestasAdoptante> recibirRespuestas(@RequestBody List<RespuestasAdoptante> respuestas) {
        try {
            // Lógica para guardar las respuestas
            cuestDao.guardarRespuestas(respuestas);
            return ResponseEntity.ok().build();
                } 
            catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();                }
    }

    // @PostMapping(path="/respuestas",produces = "application/json", consumes = "application/json")
    // public ResponseEntity<RespuestasAdoptante> guardarRespuestas (@RequestBody List<RespuestasAdoptante> r){
    //     //TODO AQUI TIENE QUE COGER EL USUARIO DE SESION
    //     if (r!=null){
    //         for (RespuestasAdoptante respuestasAdoptante : r) {
    //             Usuario usuario = new Usuario();
    //             usuario.setIdusuario(1);
    //             respuestasAdoptante.setUsuario(usuario);

    //             System.out.println("Creando cuestionario: "+respuestasAdoptante);
    //             cuestDao.respuestasAdoptante(respuestasAdoptante);
    //         }
    //         return ResponseEntity.ok(r.get(0));            
    //     }
    //     else
    //         return ResponseEntity.notFound().build();
    // }



    // @PostMapping(path="/respuestas",produces = "application/json", consumes = "application/json")
    // public ResponseEntity<RespuestasAdoptante> nuevoCuestionario (@PathVariable("idPregunta") int idPregunta, @RequestBody RespuestasAdoptante respuestas){
    //        //TODO AQUI TIENE QUE COGER EL USUARIO DE SESION
           
    //     if (respuestas!=null){
    //          Usuario usuario = new Usuario();
    //         usuario.setIdusuario(1);
    //         respuestas.setUsuario(usuario);

    //         System.out.println("Creando cuestionario: "+respuestas);
    //         List<PreguntasAdoptante> preguntasList = cuestDao.todasLasPreguntas();
    //             String[] respuestaList = respuestas.getRespuesta().split(",");      
                            
    //                 for (int i = 0; i < respuestaList.length; i++) {
                        
    //                     PreguntasAdoptante preguntaTemporal = new PreguntasAdoptante();
    //                     preguntaTemporal.setIdpregunta(preguntasList.get(i).getIdpregunta());

    //                     RespuestasAdoptante respuestaTemporal = new RespuestasAdoptante();
    //                     respuestaTemporal.setRespuesta(respuestaList[i]);

    //                     respuestaTemporal.setUsuario(usuario);
    //                     respuestaTemporal.setPreguntasAdoptante(preguntaTemporal);
    //                     cuestDao.respuestasAdoptante(respuestaTemporal);
    //                 }   
    //             cuestDao.respuestasAdoptante(respuestas);
    //             return ResponseEntity.ok(respuestas);
    //     }
    //     else
    //         return ResponseEntity.notFound().build();
    // }
 

   
    
    
}
    
    

