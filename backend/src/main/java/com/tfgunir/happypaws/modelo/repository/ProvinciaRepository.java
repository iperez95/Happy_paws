package com.tfgunir.happypaws.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfgunir.happypaws.modelo.entities.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    
}
