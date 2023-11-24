package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Especie;


public interface EspecieRepository extends JpaRepository<Especie, Integer>{

        @Query(value = "SELECT raza.* FROM RAZAS raza"
            + "inner join ESPECIE espe on espe.IDESPECIE = RAZA.IDESPECIE"
            + "where espe.IDESPECIE = ?1",
			nativeQuery = true)    
    List<Especie> EspeciesPorIdRaza(int idraza);
    
}
