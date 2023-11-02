package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Animal;

public interface IAnimalDao {
    
    boolean altaAnimal (Animal animal);
    boolean modificarAnimal (Animal animal);
    int enabledAnimal (Animal animal);
    boolean borrarAnimal (int idanimal);

    Animal buscarAnimalId (int idanimal);
    List<Animal> buscarTodos();
    
    List<Animal> buscarPorMunicipio (String municipio);
    List<Animal> buscarPorProvincia (String provincia);
    List<Animal> buscarPorProtectora (String nombre);

    List<Animal> buscarPorEspecie (String especie);
    List<Animal> buscarPorSexo (String sexo);
    List<Animal> buscarPorRaza (String raza);
    List<Animal> buscarPorTamano (String tamano);
    List<Animal> buscarPorEnvío (String envio);
}
