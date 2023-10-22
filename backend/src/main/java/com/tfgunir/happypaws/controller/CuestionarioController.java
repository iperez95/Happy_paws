package com.tfgunir.happypaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfgunir.happypaws.modelo.dao.CuestionarioDao;
import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;
import com.tfgunir.happypaws.modelo.entities.Usuario;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/cuestionario")
public class CuestionarioController {
    @Autowired
    CuestionarioDao cuestDao;

 

    @GetMapping("/cuestionario")
    public String cuestionario(Model model) {
        List<PreguntasAdoptante> preguntas = cuestDao.buscarTodas();
        model.addAttribute("preguntas", preguntas);
        return "/cuestionario/cuestionario";
    }

//       @PostMapping("/nuevo")
//     public String nuevoCuestionario(Model model, @RequestParam int idpregunta, @RequestParam String[] respuesta) 
//    {
//         //TODO AQUI TIENE QUE COGER EL USUARIO DE
//         Usuario usuario = new Usuario();
//         usuario.setIdusuario(1);


       

//         List<PreguntasAdoptante> preguntas = cuestDao.buscarTodas();
        
       

//         for ( PreguntasAdoptante pregunta : preguntas) {

//             //Construir pregunta y asignar la pregunta
//             PreguntasAdoptante preguntaTemporal = new PreguntasAdoptante();
//             preguntaTemporal.setIdpregunta(pregunta.getIdpregunta());
             
//             RespuestasAdoptante respuestaTemporal = new RespuestasAdoptante();
//             // respuestasAdoptante.setPreguntasAdoptante(preguntaTemporal);
//             // respuestasAdoptante.setUsuario(usuario);
//             // cuestDao.respuestasAdoptante(respuestasAdoptante);
//         }

//         return "redirect:/index";
//     }

    @PostMapping("/nuevo")
    public String nuevoCuestionario(Model model, RespuestasAdoptante respuestasAdoptante) 
    {
        //TODO AQUI TIENE QUE COGER EL USUARIO DE SESION
        Usuario usuario = new Usuario();
        usuario.setIdusuario(1);
        respuestasAdoptante.setUsuario(usuario);

        List<PreguntasAdoptante> preguntasList = cuestDao.buscarTodas();
        String[] respuestaList = respuestasAdoptante.getRespuesta().split(",");      
                       
            for (int i = 0; i < respuestaList.length; i++) {
                
                PreguntasAdoptante preguntaTemporal = new PreguntasAdoptante();
                preguntaTemporal.setIdpregunta(preguntasList.get(i).getIdpregunta());

                RespuestasAdoptante respuestaTemporal = new RespuestasAdoptante();
                respuestaTemporal.setRespuesta(respuestaList[i]);

                respuestaTemporal.setUsuario(usuario);
                respuestaTemporal.setPreguntasAdoptante(preguntaTemporal);
                cuestDao.respuestasAdoptante(respuestaTemporal);
            }   
        
   
        return "redirect:/index";
    }
    
    
}
    
    

