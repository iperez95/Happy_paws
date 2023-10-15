package com.tfgunir.happypaws.controller;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

  

    @GetMapping("/index")
    public String mostrarIndex(){
        return "index";
    }

    @GetMapping("/menu")
    public String mostrarMenu(){
        return "menu";
    }
    
}
