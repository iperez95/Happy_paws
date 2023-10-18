package com.tfgunir.happypaws.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;

@Controller
@RequestMapping ("/protectora")
public class ProtectoraController {

    @Autowired
    ProtectoraDao protdao;

    @GetMapping("/listado")
    public String listadoProtectoras (Model model){
        List<Protectora> protectora = protdao.buscarTodas();
        model.addAttribute("protectora", protectora);
        return "protectora/listado";
    }

    @GetMapping("/alta")
    public String darAltaProtectora (){
        return "protectora/alta";
    }

    @PostMapping ("/alta")
    public String altaProtectora (Model model, Protectora protectora){
        // if (protdao.buscarUnaProtectora(protectora.getIdprotectora())==null){
        //     // if (protdao.altaProtectora(protectora)==1) {

        //     // }
                        
        //     return (protdao.altaProtectora(protectora)==1)?"Alta de protectora realizada":"Alta de protectora NO REALIZADA";
        // } 
       
        Estadosprotectora estadoprotectoratemporal = new Estadosprotectora();
        estadoprotectoratemporal.setIdestadoprotectora(3);
        protectora.setEstadosprotectora(estadoprotectoratemporal);  
        protdao.altaProtectora(protectora);
        return "redirect:/protectora/listado";
    }

    
}
