package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

        // Query para buscar por el nombre del municipio
        @Query(value = "Select a.* from animales a" +
                        "inner join municipios m on a.IDMUNICIPIO = m.IDMUNICIPIO" + 
                        "where m.municipio = ?1", 
                        nativeQuery = true)
                List<Animal> buscarPorMunicipio(String municipio);

        //Query para buscar por el nombre de la provincia
        @Query(value = "Select a.* from animales a" +
                        "inner join municipios m on a.IDMUNICIPIO = m.IDMUNICIPIO" + 
                        "inner join provincias p on m.IDPROVINCIA = p.IDPROVINCIA" + 
                        "where p.provincia = ?1", 
                        nativeQuery = true)
                List<Animal> buscarPorProvincia(String provincia);

        // Query para buscar todos los animales de una protectora
        @Query(value = "Select a.* from animales a" +
                        "inner join protectoras p on a.IDPROTECTORA = p.IDPROTECTORA" + 
                        "where p.nombre = ?1", 
                        nativeQuery = true)
                List<Animal> buscarPorProtectora(String nombre);
        
        // // Query para buscar animales por especie
        // @Query(value = "Select a.* from animales a" +
        //                 "inner join atributos_animales aa on a.IDANIMAL = aa.IDANIMAL" + 
        //                 "inner join valores_atributos va on aa.IDVALOR = va.IDVALOR" + 
        //                 "inner join tipos_atributos ta on va.IDTIPO = ta.IDTIPO" +
        //                 "where ta.especie = ?1", 
        //                 nativeQuery = true)
        //         List<Animal> buscarPorEspecie(String especie);

        // // Query para buscar animales por sexo
        // @Query(value = "Select a.* from animales a" +
        //                 "inner join atributos_animales aa on a.IDANIMAL = aa.IDANIMAL" + 
        //                 "inner join valores_atributos va on aa.IDVALOR = va.IDVALOR" + 
        //                 "inner join tipos_atributos ta on va.IDTIPO = ta.IDTIPO" +
        //                 "where ta.tipo = 'sexo' and va.valor = ?1", 
        //                 nativeQuery = true)
        // List<Animal> buscarPorSexo(String sexo);

        // //Query para buscar animales por raza
        // @Query(value = "Select a.* from animales a" +
        //                 "inner join atributos_animales aa on a.IDANIMAL = aa.IDANIMAL" + 
        //                 "inner join valores_atributos va on aa.IDVALOR = va.IDVALOR" + 
        //                 "inner join tipos_atributos ta on va.IDTIPO = ta.IDTIPO" +
        //                 "where ta.tipo = 'raza' and va.valor = ?1", 
        //                 nativeQuery = true)    
        // List<Animal> buscarPorRaza(String raza);

        // // Query para buscar animales por tamaño
        // @Query(value = "Select a.* from animales a" +
        //                 "inner join atributos_animales aa on a.IDANIMAL = aa.IDANIMAL" + 
        //                 "inner join valores_atributos va on aa.IDVALOR = va.IDVALOR" + 
        //                 "inner join tipos_atributos ta on va.IDTIPO = ta.IDTIPO" +
        //                 "where ta.tipo = 'tamaño' and va.valor = ?1", 
        //                 nativeQuery = true)
        // List<Animal> buscarPorTamaño(String tamaño);

        // // Query para buscar animales por envío
        // @Query(value = "Select a.* from animales a" +
        //                 "inner join atributos_animales aa on a.IDANIMAL = aa.IDANIMAL" + 
        //                 "inner join valores_atributos va on aa.IDVALOR = va.IDVALOR" + 
        //                 "inner join tipos_atributos ta on va.IDTIPO = ta.IDTIPO" +
        //                 "where ta.tipo = 'envío' and va.valor = ?1", 
        //                 nativeQuery = true)
        //         List<Animal> buscarPorEnvío(String envío);
}
