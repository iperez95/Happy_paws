package com.tfgunir.happypaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfgunir.happypaws.modelo.dao.AnimalDao;
import com.tfgunir.happypaws.modelo.entities.Animal;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    
     @Autowired
     private AnimalDao aniDao;

         @GetMapping("/listado")
    public String listadoAnimales(Model model) {
        model.addAttribute("animal", aniDao.buscarTodos());
        return "animal/listado";
    }

    @GetMapping("/verUno/{id}")
    public String verUnAnimal(Model model, @PathVariable("id") int id) {
        model.addAttribute("animal", aniDao.buscarUnAnimal(id));
        return "animal/verUno";
    }

    @GetMapping("/alta")
    public String altaAnimal() {
        return "animal/alta";
    }

    @PostMapping("/alta")
    public String altaAnimal(Animal animal, RedirectAttributes flash) {

        if (aniDao.altaAnimal(animal)) {
            flash.addFlashAttribute("mensajeOk", "Animal creado correctamente");
            return "redirect:/animal/listado";
        } else {
            flash.addFlashAttribute("mensajeError", "Error al crear el animal");
            return "redirect:/animal/listado";
        }
    }

    @PostMapping("/eliminar/{idanimal}")
    public String eliminarAnimal(@PathVariable("idanimal") int idanimal, RedirectAttributes flash) {

        if (aniDao.borrarAnimal(idanimal)) {
            flash.addFlashAttribute("mensajeOk", "Animal eliminado correctamente");
            return "redirect:/animal/listado/";
        } else {
            flash.addFlashAttribute("mensajeError", "Error al eliminar el animal");
            return "redirect:/animal/listado/";
        }
    }

    @GetMapping("/modificar/{idanimal}")
    public String modificarAnimal(Model model, @PathVariable("idanimal") int idanimal) {
        model.addAttribute("animal", aniDao.buscarUnAnimal(idanimal));
        return "/animal/modificar/";
    }

    @PostMapping("/modificar/{idanimal}")
    public String modificarAnimal(@PathVariable("idanimal") int idanimal, Animal animal, RedirectAttributes flash) {
        animal.setIdanimal(idanimal);
        if (aniDao.modificarAnimal(animal)) {
            flash.addFlashAttribute("mensajeOk", "Animal editado correctamente");
            return "redirect:/animal/listado/";
        } else {
            flash.addFlashAttribute("mensajeError", "Error al editar el animal");
            return "redirect:/animal/listado/" + idanimal;
        }
    }
}
