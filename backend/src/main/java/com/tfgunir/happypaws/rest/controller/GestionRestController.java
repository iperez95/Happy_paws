package com.tfgunir.happypaws.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfgunir.happypaws.modelo.dao.ProtectoraDao;

@Controller
@RequestMapping ("/gestion")
public class GestionRestController {

    @Autowired
    ProtectoraDao protdao;

  

    
    
}
