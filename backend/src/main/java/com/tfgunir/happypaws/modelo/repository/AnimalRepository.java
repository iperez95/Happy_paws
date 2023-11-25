package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

        // Query para buscar por el nombre del municipio
        @Query(value = "Select a.* from animales a" +
                        "inner join municipios m on a.IDMUNICIPIO = m.IDMUNICIPIO" + 
                        "where m.MUNICIPIO = ?1", 
                        nativeQuery = true)
                List<Animal> buscarPorMunicipio(String municipio);

        // Query para buscar por el Id del municipio
        @Query(value = "Select a.* from animales a" +
                        "inner join municipios m on a.IDMUNICIPIO = m.IDMUNICIPIO" + 
                        "where m.IDMUNICIPIO =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorIdMunicipio(int idmunicipio);

        //Query para buscar por el nombre de la provincia
        @Query(value = "Select a.* from animales a" +
                        "inner join municipios m on a.IDMUNICIPIO = m.IDMUNICIPIO" + 
                        "inner join provincias p on m.IDPROVINCIA = p.IDPROVINCIA" + 
                        "where p.PROVINCIA = ?1", 
                        nativeQuery = true)
                List<Animal> buscarPorProvincia(String provincia);

        //Query para buscar por el Id de la provincia
        @Query(value = "Select a.* from animales a" +
                        "inner join municipios m on a.IDMUNICIPIO = m.IDMUNICIPIO" + 
                        "inner join provincias p on m.IDPROVINCIA = p.IDPROVINCIA" + 
                        "where p.IDPROVINCIA =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorIdProvincia(int idprovincia);

        // Query para buscar todos los animales de una protectora
        @Query(value = "Select a.* from animales a" +
                        "inner join protectoras p on a.IDPROTECTORA = p.IDPROTECTORA" + 
                        "where p.NOMBRE = ?1", 
                        nativeQuery = true)
                List<Animal> buscarPorProtectora(String nombre);

        // Query para buscar todos los animales de una protectora por ID
        @Query(value = "Select a.* from animales a" +
                        "inner join protectoras p on a.IDPROTECTORA = p.IDPROTECTORA" + 
                        "where p.IDPROTECTORA =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorIdProtectora(int idprotectora);
        
        // Query para buscar todos los animales por especie
        @Query(value = "Select a.* from animales a" +
                        "inner join especie e on a.IDESPECIE = e.IDESPECIE" + 
                        "where e.ESPECIE =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorEspecie(int idespecie);

        // Query para buscar todos los animales por sexo
        @Query(value = "Select a.* from animales a" +
                        "inner join sexo s on a.IDSEXO = s.IDSEXO" +
                        "where s.SEXO =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorSexo(int idsexo);
        
        // Query para buscar todos los animales por raza
        @Query(value = "SELECT a FROM Animal a WHERE a.raza.idraza = :idraza")
                List<Animal> buscarPorRaza(int idraza);
        
        // Query para buscar todos los animales por tamaño
        @Query(value = "Select a.* from animales a" +
                        "inner join tamaño t on a.IDTAMAÑO = t.IDTAMAÑO" +
                        "where t.TAMAÑO =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorTamaño(int idtamano);
        
        // Query para buscar todos los animales por envio
        @Query(value = "Select a.* from animales a" +
                        "inner join envio e on a.IDENVIO = e.IDENVIO" +
                        "where e.ENVIO =?1", 
                        nativeQuery = true)
                List<Animal> buscarPorEnvio(boolean envio);
}

