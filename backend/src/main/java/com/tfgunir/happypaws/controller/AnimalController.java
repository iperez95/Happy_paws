package com.tfgunir.happypaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfgunir.happypaws.modelo.dao.AnimalDao;
import com.tfgunir.happypaws.modelo.entities.Animal;

@Controller
@RequestMapping("/animales")
public class AnimalController {
    
     @Autowired
     private AnimalDao aniDao;

    //      @GetMapping("/listado")
    // public String listadoAnimales(Model model) {
    //     model.addAttribute("animal", aniDao.buscarTodos());
    //     return "animal/listado";
    // }
    
    // Controlador para el listado de animales
    @GetMapping(path="/listado", produces = "application/json")
    public ResponseEntity<List<Animal>> listadoAnimales() {

    List<Animal> listado = aniDao.buscarTodos();
    if (listado != null && !listado.isEmpty()) {
        return ResponseEntity.ok(listado);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    // @GetMapping("/verUno/{id}")
    // public String verPorId(Model model, @PathVariable("idanimal") int idanimal) {
    //     model.addAttribute("animal", aniDao.buscarAnimalId(idanimal));
    //     return "animal/verUno";
    // }

    // Controlador para ver un animal
    @GetMapping(path="/verUno/{id}", produces = "application/json")
    public ResponseEntity<Animal> verPorId(@PathVariable("id") int id) {

    Animal animal = aniDao.buscarAnimalId(id);
    if (animal != null) {
        return ResponseEntity.ok(animal);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    // @GetMapping("/alta")
    // public String altaAnimal() {
    //     return "animal/alta";
    // }

    // @PostMapping("/alta")
    // public String altaAnimal(Animal animal, RedirectAttributes flash) {

    //     if (aniDao.altaAnimal(animal)) {
    //         flash.addFlashAttribute("mensajeOk", "Animal creado correctamente");
    //         return "redirect:/animal/listado";
    //     } else {
    //         flash.addFlashAttribute("mensajeError", "Error al crear el animal");
    //         return "redirect:/animal/listado";
    //     }
    // }

    // Controlador para dar de alta un animal
    @PostMapping(path="/alta", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> altaAnimal(@RequestBody Animal animal) {
    if (aniDao.altaAnimal(animal)) {
        return ResponseEntity.ok("Animal creado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el animal");
    }
}

    // @PostMapping("/eliminar/{idanimal}")
    // public String eliminarAnimal(@PathVariable("idanimal") int idanimal, RedirectAttributes flash) {

    //     if (aniDao.borrarAnimal(idanimal)) {
    //         flash.addFlashAttribute("mensajeOk", "Animal eliminado correctamente");
    //         return "redirect:/animal/listado/";
    //     } else {
    //         flash.addFlashAttribute("mensajeError", "Error al eliminar el animal");
    //         return "redirect:/animal/listado/";
    //     }
    // }

    // Controlador para eliminar un animal
    @DeleteMapping(path="/eliminar/{id}", consumes = "application/json")
    public ResponseEntity<?> eliminarAnimal(@PathVariable("id") int id) {

    if (aniDao.borrarAnimal(id)) {
        return ResponseEntity.ok("Animal eliminado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el animal");
    }
}

    // @GetMapping("/modificar/{idanimal}")
    // public String modificarAnimal(Model model, @PathVariable("idanimal") int idanimal) {
    //     model.addAttribute("animal", aniDao.buscarAnimalId(idanimal));
    //     return "/animal/modificar/";
    // }

    // @PostMapping("/modificar/{idanimal}")
    // public String modificarAnimal(@PathVariable("idanimal") int idanimal, Animal animal, RedirectAttributes flash) {
    //     animal.setIdanimal(idanimal);
    //     if (aniDao.modificarAnimal(animal)) {
    //         flash.addFlashAttribute("mensajeOk", "Animal editado correctamente");
    //         return "redirect:/animal/listado/";
    //     } else {
    //         flash.addFlashAttribute("mensajeError", "Error al editar el animal");
    //         return "redirect:/animal/listado/" + idanimal;
    //     }
    // }

    // Controlador para modificar un animal
    @GetMapping(path="/modificar/{id}", produces = "application/json")
    public ResponseEntity<Animal> obtenerAnimal(@PathVariable("id") int id) {
    Animal animal = aniDao.buscarAnimalId(id);
    if (animal != null) {
        return ResponseEntity.ok(animal);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @PutMapping(path="/modificar/{id}", consumes = "application/json")
    public ResponseEntity<?> modificarAnimal(@PathVariable("id") int id, @RequestBody Animal animal) {
    animal.setIdanimal(id);
    if (aniDao.modificarAnimal(animal)) {
        return ResponseEntity.ok("Animal editado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el animal");
    }
}

    // Controlador para buscar animales por municipio
    @GetMapping(path="/buscarmunicipio/{municipio}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorMunicipio(@PathVariable("municipio") String municipio) {

        List<Animal> listado = aniDao.buscarPorMunicipio(municipio);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por provincia



    // Controlador para buscar animales por protectora



    // Controlador para buscar animales por especie




    // Controlador para buscar animales por sexo




    // Controlador para buscar animales por raza




    // Controlador para buscar animales por tamaño




    // Controlador para buscar animales por envío



}