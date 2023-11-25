package com.tfgunir.happypaws.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfgunir.happypaws.modelo.dao.MultimediaDao;
import com.tfgunir.happypaws.modelo.entities.Multimedia;




@Controller
@CrossOrigin(origins="*")
@RequestMapping("/multimedia")
public class MultimediaRestController {
    @Autowired
    MultimediaDao multdao;

    @GetMapping(path="/animal/{id}", produces = "application/json")
    public ResponseEntity<List<Multimedia>> listMultimediasAnimal(Model model, @PathVariable (name= "id")int idAnimal) {
        List<Multimedia> listMultimedia = multdao.multimediasAnimal(idAnimal);
        if (listMultimedia != null) {
            return new ResponseEntity<>(listMultimedia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
