package com.tfgunir.happypaws.rest.controller;

import java.util.List;

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


import com.tfgunir.happypaws.modelo.dao.AnimalDao;
import com.tfgunir.happypaws.modelo.entities.Animal;

@Controller
@RequestMapping("/animales")
@CrossOrigin(origins ="*")
public class AnimalRestController {
    
     @Autowired
     private AnimalDao aniDao;

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

    // Controlador para dar de alta un animal
    @PostMapping(path="/gestion/alta", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> altaAnimal(@RequestBody Animal animal) {
    if (aniDao.altaAnimal(animal)) {
        return ResponseEntity.ok("Animal creado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el animal");
    }
}

    // Controlador para eliminar un animal
    @DeleteMapping(path="/gestion/eliminar/{id}", consumes = "application/json")
    public ResponseEntity<?> eliminarAnimal(@PathVariable("id") int id) {

    if (aniDao.borrarAnimal(id)) {
        return ResponseEntity.ok("Animal eliminado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el animal");
    }
}
    // Controlador para modificar un animal
    @GetMapping(path="/gestion/modificar/{id}", produces = "application/json")
    public ResponseEntity<Animal> obtenerAnimal(@PathVariable("id") int id) {
    Animal animal = aniDao.buscarAnimalId(id);
    if (animal != null) {
        return ResponseEntity.ok(animal);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @PutMapping(path="/gestion/modificar/{id}", consumes = "application/json")
    public ResponseEntity<?> modificarAnimal(@PathVariable("id") int id, @RequestBody Animal animal) {
    animal.setIdanimal(id);
    if (aniDao.modificarAnimal(animal)) {
        return ResponseEntity.ok("Animal editado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el animal");
    }
}

    // Controlador para cambiar enabled a un animal
    @PutMapping(path="/gestion/enabled/{id}", consumes = "application/json")    
    public ResponseEntity<?> enabledAnimal(@PathVariable("id") int id, @RequestBody Animal animal) {    

    animal.setIdanimal(id);
    if (aniDao.enabledAnimal(animal) == true) {
        return ResponseEntity.ok("Animal habilitado correctamente");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al habilitar el animal");
    }
}

    // Controlador para buscar animales por municipio
    @GetMapping(path="/buscar/pormunicipio/{municipio}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorMunicipio(@PathVariable("municipio") String municipio) {

        List<Animal> listado = aniDao.buscarPorMunicipio(municipio);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por ID municipio
    @GetMapping(path="/buscar/poridmunicipio/{idmunicipio}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorIdMunicipio(@PathVariable("idmunicipio") int idmunicipio) {

        List<Animal> listado = aniDao.buscarPorIdMunicipio(idmunicipio);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por provincia
    @GetMapping(path="/buscar/porprovincia/{provincia}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorProvincia(@PathVariable("provincia") String provincia) {

        List<Animal> listado = aniDao.buscarPorProvincia(provincia);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        // Controlador para buscar animales por ID provincia
    @GetMapping(path="/buscar/poridprovincia/{idprovincia}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorIDProvincia(@PathVariable("idprovincia") int idprovincia) {

        List<Animal> listado = aniDao.buscarPorIdProvincia(idprovincia);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por protectora
    @GetMapping(path="/buscar/porprotectora/{nombre}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorProtectora(@PathVariable("nombre") String nombre) {

        List<Animal> listado = aniDao.buscarPorProtectora(nombre);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por id protectora
    @GetMapping(path="/buscar/poridprotectora/{idprotectora}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorIdProtectora(@PathVariable("idprotectora") int idprotectora) {

     List<Animal> listado = aniDao.buscarPorIdProtectora(idprotectora);
     if (listado != null && !listado.isEmpty()) {
        return ResponseEntity.ok(listado);
    } else {
         return ResponseEntity.notFound().build();
     }
  }

    // Controlador para buscar animales por especie
    @GetMapping(path="/buscar/porespecie/{idespecie}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorEspecie(@PathVariable("idespecie") int idespecie) {

        List<Animal> listado = aniDao.buscarPorEspecie(idespecie);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por sexo
    @GetMapping(path="/buscar/porsexo/{idsexo}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorSexo(@PathVariable("idsexo") int idsexo) {

        List<Animal> listado = aniDao.buscarPorSexo(idsexo);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por raza
    @GetMapping(path="/buscar/porraza/{idraza}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorRaza(@PathVariable("idraza") int idraza) {

        List<Animal> listado = aniDao.buscarPorRaza(idraza);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por tamaño
    @GetMapping(path="/buscar/portamano/{idtamano}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorTamaño(@PathVariable("idtamano") int idtamano) {

        List<Animal> listado = aniDao.buscarPorTamaño(idtamano);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controlador para buscar animales por envío
    @GetMapping(path="/buscar/porenvio/{idenvio}", produces = "application/json")
    public ResponseEntity<List<Animal>> buscarPorEnvío(@PathVariable("idenvio") boolean envio) {

        List<Animal> listado = aniDao.buscarPorEnvio(envio);
        if (listado != null && !listado.isEmpty()) {
            return ResponseEntity.ok(listado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
}