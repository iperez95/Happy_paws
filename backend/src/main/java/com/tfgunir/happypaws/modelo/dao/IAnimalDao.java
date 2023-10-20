package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Animal;

public interface IAnimalDao {
    
    boolean altaAnimal (Animal animal);
    boolean modificarAnimal (Animal animal);
    int enabledAnimal (Animal animal);
    boolean borrarAnimal (int idanimal);
    Animal buscarUnAnimal (int id);
    List<Animal> buscarTodos();
    List<Animal> buscarPorProvincia(int IdProvincia);
    List<Animal> buscarPorMunicipio (int IdMunicipio);
    List<Animal> buscarPorProtectora (int IdProtectora);
    List<Animal> buscarPorAtributos (int valoresAtributo); // ya veremos como hago esto
    
}
