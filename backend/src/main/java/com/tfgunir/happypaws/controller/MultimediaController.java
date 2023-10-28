package com.tfgunir.happypaws.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfgunir.happypaws.modelo.dao.MultimediaDao;
import com.tfgunir.happypaws.modelo.entities.Multimedia;




@Controller
@RequestMapping("/multimedia")
public class MultimediaController {
    @Autowired
    MultimediaDao multdao;

    @GetMapping("/animal/{id}")
    public List<Multimedia> listMultimediasAnimal(Model model, @PathVariable (name= "id")int idAnimal) {
        List<Multimedia> listMultimedia = multdao.multimediasAnimal(idAnimal);
        model.addAttribute("multimedia", listMultimedia);
        return listMultimedia;   
    }
    
}
