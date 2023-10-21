package com.tfgunir.happypaws.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;

public interface PreguntasRepository extends JpaRepository<PreguntasAdoptante, Integer> {
    
}
