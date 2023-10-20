package com.tfgunir.happypaws.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tfgunir.happypaws.modelo.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{
    
}
