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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tfgunir.happypaws.modelo.dao.AnimalDao;
import com.tfgunir.happypaws.modelo.dao.MultimediaDao;
import com.tfgunir.happypaws.modelo.dto.AnimalDto;
import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.entities.Multimedia;

@Controller
@RequestMapping("/animales")
@CrossOrigin(origins = "*")
public class AnimalRestController {

    @Autowired
    private AnimalDao aniDao;

    @Autowired
    MultimediaDao multiDao;

    // Controlador para el listado de animales
    @GetMapping(path = "/listado", produces = "application/json")
    public ResponseEntity<List<Animal>> listadoAnimales() {

        List<Animal> listado = aniDao.buscarTodos();
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listado de animales DTO
    @GetMapping(path = "/listadodto", produces = "application/json")
    public ResponseEntity<List<AnimalDto>> listadoAnimalesDto() {

        List<Animal> animales = aniDao.buscarTodos();
        List<AnimalDto> animalesDto = new ArrayList<>();
        
        if (animales != null && !animales.isEmpty()) {
           
            for (Animal animal : animales) {
                animalesDto.add(aniDao.convertirAnimalDto(animal));
            }
            List<Multimedia> multimedias = multiDao.multimediasTodos();

        if (multimedias != null && !multimedias.isEmpty()) {
            for(AnimalDto animalDto : animalesDto){
                for (Multimedia multimedia : multimedias) {
                    if (multimedia.getAnimal().getIdanimal() == animalDto.getIdanimal() && animalDto.getEnlacePrimeraFoto() == null) {
                            animalDto.setEnlacePrimeraFoto(multimedia.getEnlace());
                    }
                }
            }
        }
            return new ResponseEntity<>(animalesDto, HttpStatus.OK);     
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }      

    }


    // Controlador para ver un animal
    @GetMapping(path = "/verUno/{id}", produces = "application/json")
    public ResponseEntity<Animal> verPorId(@PathVariable("id") int id) {

        Animal animal = aniDao.buscarAnimalId(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para dar de alta un animal
    @PostMapping(path = "/gestion/alta")
    public ResponseEntity<Map<String, String>> altaAnimal(@RequestBody Animal animal) {
        Map<String, String> response = new HashMap<>();
        if (aniDao.altaAnimal(animal)) {
            response.put("message", "Animal creado correctamente");
            response.put("idAnimal", String.valueOf(animal.getIdanimal()));
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Error al crear el animal");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Controlador para eliminar un animal
    @DeleteMapping(path = "/gestion/eliminar/{id}")
    public ResponseEntity<?> eliminarAnimal(@PathVariable("id") int id) {
        Map<String, String> response = new HashMap<>();
        if (aniDao.borrarAnimal(id)) {
            return ResponseEntity.ok(response.put("message", "Animal eliminado correctamente"));
        } else {
            response.put("message", "Error al eliminar el animal");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Controlador para modificar un animal
            // Recupera el id del animal
    @GetMapping(path = "/gestion/modificar/{id}", produces = "application/json")
    public ResponseEntity<Animal> obtenerAnimal(@PathVariable("id") int id) {
        Animal animal = aniDao.buscarAnimalId(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
            // Cambia el animal
    @PutMapping(path = "/gestion/modificar/{id}", consumes = "application/json")
    public ResponseEntity<?> modificarAnimal(@PathVariable("id") int id, @RequestBody Animal animal) {
        Map<String, String> response = new HashMap<>();
        animal.setIdanimal(id);
        if (aniDao.modificarAnimal(animal)) {
            return ResponseEntity.ok(response.put("message", "Animal editado correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el animal");
        }
    }

    // Controlador para cambiar enabled a un animal
    @PutMapping(path = "/gestion/enabled/{id}", consumes = "application/json")
    public ResponseEntity<?> enabledAnimal(@PathVariable("id") int id, @RequestBody Animal animal) {

        animal.setIdanimal(id);
        if (aniDao.enabledAnimal(animal) == true) {
            return ResponseEntity.ok("Animal habilitado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al habilitar el animal");
        }
    }

    // Controlador para buscar animales por ID municipio
    @GetMapping(path = "/buscar/poridmunicipio/{idmunicipio}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorIdMunicipio(@PathVariable("idmunicipio") int idmunicipio) {

        List<Animal> listado = aniDao.buscarPorIdMunicipio(idmunicipio);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por ID provincia
    @GetMapping(path = "/buscar/poridprovincia/{idprovincia}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorIDProvincia(@PathVariable("idprovincia") int idprovincia) {

        List<Animal> listado = aniDao.buscarPorIdProvincia(idprovincia);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por ID protectora
    @GetMapping(path = "/buscar/poridprotectora/{idprotectora}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorIdProtectora(@PathVariable("idprotectora") int idprotectora) {

        List<Animal> listado = aniDao.buscarPorIdProtectora(idprotectora);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por especie
    @GetMapping(path = "/buscar/porespecie/{idespecie}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable("idespecie") int idespecie) {

        List<Animal> listado = aniDao.buscarPorEspecie(idespecie);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por sexo
    @GetMapping(path = "/buscar/porsexo/{idsexo}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorSexo(@PathVariable("idsexo") int idsexo) {

        List<Animal> listado = aniDao.buscarPorSexo(idsexo);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por raza
    @GetMapping(path = "/buscar/porraza/{idraza}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorRaza(@PathVariable("idraza") int idraza) {

        List<Animal> listado = aniDao.buscarPorRaza(idraza);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por tamaño
    @GetMapping(path = "/buscar/portamano/{idtamano}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorTamaño(@PathVariable("idtamano") int idtamano) {

        List<Animal> listado = aniDao.buscarPorTamaño(idtamano);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por envío
    @GetMapping(path = "/buscar/porenvio/{idenvio}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorEnvío(@PathVariable("idenvio") boolean envio) {

        List<Animal> listado = aniDao.buscarPorEnvio(envio);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar Perros
    @GetMapping(path = "/buscar/soloperros", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarSoloPerros() {
        List<Animal> listado = aniDao.buscarSoloPerros();
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar Gatos
    @GetMapping(path = "/buscar/sologatos", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarSoloGatos() {

        List<Animal> listado = aniDao.buscarSoloGatos();
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Controlador para subir foto
    @GetMapping(path = "/gestion/subirfoto/{id}", produces = "application/json")
    public ResponseEntity<Animal> subirFoto(@PathVariable("id") int id) {
        System.out.println("Buscando animal con id: " + id);
        Animal animal = aniDao.buscarAnimalId(id);
        if (animal != null)
            return ResponseEntity.ok(animal);
        else
            return ResponseEntity.notFound().build();
    }

    // Controlador que añade las fotos al objeto animal
    @PostMapping(path = "/gestion/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") int id) {
        Map<String, Object> response = new HashMap<>();

        Animal animal = aniDao.buscarAnimalId(id);

        Multimedia multimedia = new Multimedia();

        if (!archivo.isEmpty()) {

            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("..//frontend//src//assets//images//animales//" + id).resolve(nombreArchivo).toAbsolutePath();

            try {
                if (!Files.exists(rutaArchivo.getParent())) {
                    Files.createDirectories(rutaArchivo.getParent());
                }
                Files.copy(archivo.getInputStream(), rutaArchivo);
                multimedia.setEnlace("/assets/images/animales/" + id + "/" + nombreArchivo);
                multimedia.setAnimal(animal);
                multiDao.altaMultimedia(multimedia);

                response.put("multimedia", multimedia);
                response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen: " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // Controlador para borrar foto animal
    @DeleteMapping(path = "/gestion/borrarfoto/{id}")
    public ResponseEntity<?> borrarFoto(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Multimedia multimedia = multiDao.buscarMultimediaId(id);
            if (multimedia != null) {
                String nombreArchivo = multimedia.getEnlace();
                Path rutaArchivo = Paths.get("..//frontend//src//" + nombreArchivo);
                if (Files.exists(rutaArchivo)) {
                    Files.delete(rutaArchivo);
                }
                if (multiDao.borrarMultimedia(id) == 1) {
                    response.put("mensaje", "Foto eliminada correctamente");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                } else {
                    response.put("mensaje", "No se encontró la foto");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                response.put("mensaje", "No se encontró la foto");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("mensaje", "Error al eliminar la foto");
            response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   

    // Controlador para el filtro
    @GetMapping("/filtrar")
    public ResponseEntity<List<Animal>> filtrarAnimales(
            @RequestParam(name = "especie", required = false) String especie,
            @RequestParam(name = "raza", required = false) String raza,
            @RequestParam(name = "sexo", required = false) String sexo,
            @RequestParam(name = "tamano", required = false) String tamano,
            @RequestParam(name = "provincia", required = false) String provincia,
            @RequestParam(name = "envio", required = false) Boolean envio
    ) {
        System.out.println(envio);
        List<Animal> animalesFiltrados = aniDao.filtrarAnimales(especie, raza, sexo, tamano, provincia, envio);
        System.out.println(animalesFiltrados);
        return new ResponseEntity<>(animalesFiltrados, HttpStatus.OK);
    }



}
