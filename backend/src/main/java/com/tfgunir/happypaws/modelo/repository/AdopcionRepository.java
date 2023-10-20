package com.tfgunir.happypaws.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfgunir.happypaws.modelo.entities.Adopcion;

public interface AdopcionRepository extends JpaRepository<Adopcion, Integer>{
    
}
