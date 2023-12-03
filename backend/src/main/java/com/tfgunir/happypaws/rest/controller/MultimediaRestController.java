package com.tfgunir.happypaws.rest.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.tfgunir.happypaws.modelo.dto.MultimediaDto;
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

    //SIN DTO
    // @GetMapping(path = "/animal/{id}", produces = "application/json")
    // public ResponseEntity<List<Multimedia>> listMultimediasAnimal(Model model,
    //         @PathVariable(name = "id") int idAnimal) {
    //     List<Multimedia> listMultimedia = multdao.multimediasAnimal(idAnimal);
    //     if (listMultimedia != null) {
    //         return new ResponseEntity<>(listMultimedia, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    /**
     * Método que devuelve la lista de URls que pertenecen a un animal.
     */
    @GetMapping(path = "/animal/{id}", produces = "application/json")
    public ResponseEntity<List<MultimediaDto>> listMultimediasAnimal(Model model, @PathVariable(name = "id") int id) {

        List<Multimedia> multimedias = multdao.multimediasAnimal(id);

        if (multimedias != null) {
            List<MultimediaDto> multimediaDto = new ArrayList<>();
            for (Multimedia multimedia : multimedias) {
                multimediaDto.add(multdao.convertirMultimediaDto(multimedia));
            }
            return new ResponseEntity<>(multimediaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = "/gestion/subirfoto/{id}", produces = "application/json")
    public ResponseEntity<List<Multimedia>> subirFoto(@PathVariable("id") int id) {
        List<Multimedia> multimedias = multdao.multimediasAnimal(id);
        if (multimedias != null) return new ResponseEntity<>(multimedias, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Método que guarda las fotos físicamente en el servidor y guarda la ruta de las mismas en la bbdd.
     */
    @PostMapping(path = "/gestion/upload")
    public ResponseEntity<?> upload(@RequestParam("files") MultipartFile[] files, @RequestParam("id") int id) {
        Map<String, Object> response = new HashMap<>();
        Animal animal = anidao.buscarAnimalId(id);
        String path = "../frontend/src/assets/images/animales/" + id + "/";
        if (files != null && files.length > 0) {
            for (MultipartFile file: files) {
                Multimedia multimedia = new Multimedia();
                String nombreFoto = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
                Path rutaArchivo = Paths.get(path).resolve(nombreFoto).toAbsolutePath();
                try {
                    Files.createDirectories(rutaArchivo.getParent());
                    Files.copy(file.getInputStream(), rutaArchivo);
                    multimedia.setEnlace(nombreFoto);
                    multimedia.setAnimal(animal);
                    multdao.altaMultimedia(multimedia);
                } catch(IOException e) {
                    response.put("mensaje", "Error al subir una foto");
                    response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                response.put("mensaje", "Has subido correctamente las fotos.");
            }
        } else { response.put("mensaje", "No se proporcionaron archivos para subir."); }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Método que devuelve una lista con listas de todas las fotos de los animales indicados por su id.
     */
    @GetMapping("/fotos")
    public ResponseEntity<Map<String, Object>> recuperarFotosAnimal(
            @RequestParam("idsAnimales") List<Integer> idsAnimales
    ) {
        System.out.println(idsAnimales);
        Map<Integer, List<Multimedia>> fotosAnimales = new HashMap<>();

        for (int idAnimal: idsAnimales) {
            List<Multimedia> fotos = multdao.multimediasAnimal(idAnimal);
            fotosAnimales.put(idAnimal, fotos);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Fotos devueltas correctamente");
        response.put("fotosAnimales", fotosAnimales);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}