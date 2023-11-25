package com.tfgunir.happypaws.rest.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tfgunir.happypaws.modelo.dao.AnimalDao;
import com.tfgunir.happypaws.modelo.dao.MultimediaDao;
import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.entities.Multimedia;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/multimedia")
public class MultimediaRestController {
    @Autowired
    MultimediaDao multdao;

    @Autowired
    AnimalDao anidao;

    @GetMapping(path = "/animal/{id}", produces = "application/json")
    public ResponseEntity<List<Multimedia>> listMultimediasAnimal(Model model,
            @PathVariable(name = "id") int idAnimal) {
        List<Multimedia> listMultimedia = multdao.multimediasAnimal(idAnimal);
        if (listMultimedia != null) {
            return new ResponseEntity<>(listMultimedia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // SUBIR FOTO ANIMAL
    @GetMapping(path = "/gestion/subirfoto/{id}", produces = "application/json")
    public ResponseEntity<List<Multimedia>> subirFoto(@PathVariable("id") int id) {
        System.out.println("Buscando protectora con id: " + id);
        List<Multimedia> multimedias = multdao.multimediasAnimal(id);
        if (multimedias != null)
            return new ResponseEntity<>(multimedias, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // AÑADE FOTO AL ANIMAL
    @PostMapping(path = "/gestion/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") int id) {
        Map<String, Object> response = new HashMap<>();

        Animal animal = anidao.buscarAnimalId(id);

        Multimedia multimedia = new Multimedia();

        if (!archivo.isEmpty()) {

            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("..//frontend//src//assets//images//animal//" + id + "//")
                    .resolve(nombreArchivo).toAbsolutePath();

            try {
                Files.createDirectories(rutaArchivo.getParent());
                Files.copy(archivo.getInputStream(), rutaArchivo);
                multimedia.setEnlace("/assets/images/animal/" + id + "/" + nombreArchivo);
                multimedia.setAnimal(animal);
                multdao.altaMultimedia(multimedia);

                response.put("multimedia", multimedia);
                response.put("mensaje", "Has subido correctamente la foto: " + nombreArchivo);

            } catch (IOException e) {
                response.put("mensaje", "Error al subir la foto: " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}