package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Multimedia;


public interface MultimediaRepository extends JpaRepository<Multimedia, Integer>  {

    @Query(value = "select mul.idmultimedia, mul.enlace, mul.idanimal from MULTIMEDIAS mul\r\n"
    + "inner join ANIMALES ani on ani.idanimal = mul.idanimal\r\n"
    + "where ani.idanimal = ?1",
			nativeQuery = true)    
    List<Multimedia> todosMultimediasAnimal(int idAnimal);
    
}
