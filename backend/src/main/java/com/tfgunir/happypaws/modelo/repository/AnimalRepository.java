package com.tfgunir.happypaws.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tfgunir.happypaws.modelo.entities.Protectora;

public interface AnimalRepository extends JpaRepository<Protectora, Integer>{
    
}
