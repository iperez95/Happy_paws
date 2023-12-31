package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Animal;

public interface IAnimalDao {
    
    boolean altaAnimal (Animal animal);
    boolean modificarAnimal (Animal animal);
    boolean enabledAnimal (Animal animal);
    boolean borrarAnimal (int idanimal);

    Animal buscarAnimalId (int idanimal);
    List<Animal> buscarTodos();
    List<Animal> buscarPorNombreContiene(String nombre);
    
    List<Animal> buscarPorIdMunicipio (int idmunicipio);
    List<Animal> buscarPorIdProvincia (int idprovincia);
    List<Animal> buscarPorIdProtectora (int idprotectora);

    List<Animal> buscarPorEspecie (int idespecie);
    List<Animal> buscarPorSexo (int idsexo);
    List<Animal> buscarPorRaza (int idraza);
    List<Animal> buscarPorTamaño (int idtamano);
    List<Animal> buscarPorEnvio (boolean envio);

    List<Animal> buscarSoloPerros ();
    List<Animal> buscarSoloGatos ();

    List<Animal> filtrarAnimales(String especie, String raza, String sexo, String tamano, String provincia, Boolean envio);
    
}
