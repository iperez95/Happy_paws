package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Animal;

public interface IAnimalDao {
    
    int altaAnimal (Animal animal);
    int modificarAnimal (Animal animal);
    int enabledAnimal (Animal animal);
    int borrarAnimal (Animal animal);
    Animal buscarUnAnimal (int id);
    List<Animal> buscarTodas();
    List<Animal> buscarPorProvincia(int IdProvincia);
    List<Animal> buscarPorMunicipio (int IdMunicipio);
    List<Animal> buscarPorProtectora (int IdProtectora);
    List<Animal> buscarPorAtributos (int valoresAtributo); // ya veremos como hago esto
    
}
