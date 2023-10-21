package com.tfgunir.happypaws.controller;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cuestionario")
public class CuestionarioController {

    @GetMapping("/cuestionario")
    public String cuestionario() {
        return null;
    }
    
}
