package com.tfgunir.happypaws.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;

@RestController
@RequestMapping ("/gestion")
public class GestionRestController {

    @Autowired
    ProtectoraDao protdao;

  

    
    
}
