package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.entities.Protectora;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

        // Query para buscar por el Id del municipio
        @Query(value = "SELECT a FROM Animal a WHERE a.municipio.id = :idmunicipio")
                List<Animal> buscarPorIdMunicipio(int idmunicipio);

        //Query para buscar por el Id de la provincia
        @Query(value = "SELECT a FROM Animal a INNER JOIN a.municipio m INNER JOIN m.provincia p WHERE p.provincia.idprovincia = :idprovincia")
                List<Animal> buscarPorIdProvincia(int idprovincia);

        // Query para buscar todos los animales de una protectora por ID
        @Query(value = "SELECT a FROM Animal a WHERE a.protectora.idprotectora = :idprotectora")
                List<Animal> buscarPorIdProtectora(int idprotectora);
        
        // Query para buscar todos los animales por especie
        @Query(value = "SELECT a FROM Animal a INNER JOIN a.raza r INNER JOIN r.especie e WHERE e.especie.idespecie = :idespecie")
                List<Animal> buscarPorEspecie(int idespecie);

        // Query para buscar todos los animales por sexo
        @Query(value = "SELECT a FROM Animal a WHERE a.sexo.idsexo = :idsexo")
                List<Animal> buscarPorSexo(int idsexo);
        
        // Query para buscar todos los animales por raza
        @Query(value = "SELECT a FROM Animal a WHERE a.raza.idraza = :idraza")
                List<Animal> buscarPorRaza(int idraza);
        
        // Query para buscar todos los animales por tamaño
        @Query(value = "SELECT a FROM Animal a WHERE a.tamano.idtamano = :idtamano")
                List<Animal> buscarPorTamaño(int idtamano);
        
        // Query para buscar todos los animales por envio
        @Query(value = "SELECT a FROM Animal a WHERE a.envio =?1")
                List<Animal> buscarPorEnvio(boolean envio);

        // Query para buscar todos los animales que son perros
        @Query(value = "SELECT a FROM Animal a INNER JOIN a.raza r INNER JOIN r.especie e WHERE e.especie.idespecie = 1")
                List<Animal> buscarSoloPerros();
        
        // Query para buscar todos los animales que son gatos
        @Query(value = "SELECT a FROM Animal a INNER JOIN a.raza r INNER JOIN r.especie e WHERE e.especie.idespecie = 2")
                List<Animal> buscarSoloGatos();

         @Query(value = "SELECT * from ANIMALES where nombre like ?1 ",
                nativeQuery = true)
        List<Animal> buscarPorNombreContiene(String nombre);
}

