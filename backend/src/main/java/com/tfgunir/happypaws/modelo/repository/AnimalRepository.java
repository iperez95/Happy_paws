package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

    // Query para buscar por el nombre del municipio
    @Query("select a from Animal a where a.municipio.municipio= ?1")
    List<Animal> buscarPorMunicipio(String municipio);

    //Query para buscar por el nombre de la provincia
    @Query("select a from Animal a where a.municipio.provincia.provincia= ?1")
    List<Animal> buscarPorProvincia(String provincia);

    // Qyuery para buscar todos los animales de una protectora
    @Query("select a from Animal a where a.protectora.nombre= ?1")
    List<Animal> buscarPorProtectora(String nombre);    

    // Query para buscar animales por especie
    @Query("select a from Animal a join a.valoresAtributo v where v.idtipo.especie = ?1")
    List<Animal> buscarPorEspecie(String especie);

    // Query para buscar animales por sexo
    @Query("select a from Animal a join a.valoresAtributo v"
            + "where v.idtipo.tipo = 'sexo' and v.valor = ?1")
    List<Animal> buscarPorSexo(String sexo);

    // Query para buscar animales por raza
    @Query("select a from Animal a join a.valoresAtributo v"
            + "where v.idtipo.tipo = 'raza' and v.valor = ?1")
    List<Animal> buscarPorRaza(String raza);

    // Query para buscar animales por tamaño
    @Query("select a from Animal a join a.valoresAtributo v" 
            + "where v.idtipo.tipo = 'tamaño' and v.valor = ?1")
    List<Animal> buscarPorTamaño(String tamaño);

    // Query para buscar animales por envío
    @Query("select a from Animal a join a.valoresAtributo v"
            + "where v.idtipo.tipo = 'envío' and v.valor = ?1")
    List<Animal> buscarPorEnvío(String envío);
}
