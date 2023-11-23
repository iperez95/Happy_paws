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
    
    List<Animal> buscarPorMunicipio (String municipio);
    List<Animal> buscarPorIdMunicipio (int idmunicipio);
    List<Animal> buscarPorProvincia (String provincia);
    List<Animal> buscarPorIdProvincia (int idprovincia);
    List<Animal> buscarPorProtectora (String nombre);
    List<Animal> buscarPorIdProtectora (int idprotectora);

    List<Animal> buscarPorEspecie (int idespecie);
    List<Animal> buscarPorSexo (int idsexo);
    List<Animal> buscarPorRaza (int idraza);
    List<Animal> buscarPorTamaño (int idtamano);
    List<Animal> buscarPorEnvio (boolean envio);
}
