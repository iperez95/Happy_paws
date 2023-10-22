package com.tfgunir.happypaws.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.entities.Usuario;

@Controller
@RequestMapping ("/protectora")
public class ProtectoraController {

    @Autowired
    ProtectoraDao protdao;

   

    

    
    //TODOdav Security - Solo accesible por usuarios tipo protetora
    @GetMapping("/gestion/alta")
    public String darAltaProtectora (HttpSession sesion, ModelMap modelMap){
       
        return "/protectora/gestion/alta" ;
    }

    
    //TODOdav comprobar que solo los usuarios tipo protectora pueden dar el alta.
    @PostMapping ("/gestion/alta")
    public String altaProtectora (Model model, Protectora protectora, HttpSession session){
        
        Usuario usuarioSesion = (Usuario) session.getAttribute("email");
       
        protectora.setUsuario(usuarioSesion);
        Estadosprotectora estadoprotectoratemporal = new Estadosprotectora();
        estadoprotectoratemporal.setIdestadoprotectora(3);
        protectora.setEstadosprotectora(estadoprotectoratemporal);
        protdao.altaProtectora(protectora);
        model.addAttribute("message", "Protectora saved successfully!");

        
        
        // Estadosprotectora estadoprotectoratemporal = new Estadosprotectora();
        // estadoprotectoratemporal.setIdestadoprotectora(3);
        // protectora.setEstadosprotectora(estadoprotectoratemporal);
         
        // protdao.altaProtectora(protectora);
        return "redirect:/index";

        // if (protdao.buscarUnaProtectora(protectora.getIdprotectora())==null){
        //     // if (protdao.altaProtectora(protectora)==1) {

        //     // }
                        
        //     return (protdao.altaProtectora(protectora)==1)?"Alta de protectora realizada":"Alta de protectora NO REALIZADA";
        // } 
    }
    
    
}
