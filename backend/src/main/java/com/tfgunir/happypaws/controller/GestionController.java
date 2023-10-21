package com.tfgunir.happypaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;
import com.tfgunir.happypaws.modelo.entities.Protectora;

@Controller
@RequestMapping ("/gestion")
public class GestionController {

    @Autowired
    ProtectoraDao protdao;

     //TODOdav Security - Este listado solo debe ser accesible por administradores.
    @GetMapping("/listado")
    public String listadoProtectoras (Model model){
        List<Protectora> protectora = protdao.buscarTodas();
        model.addAttribute("protectora", protectora);
        return "protectora/listado";
    }
    
}
