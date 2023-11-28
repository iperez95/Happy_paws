package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfgunir.happypaws.modelo.entities.Raza;

public interface RazaRepository extends JpaRepository<Raza, Integer>{

    @Query(value = "SELECT r FROM Raza r WHERE r.especie.idespecie = :idespecie")
        List<Raza> razasPorIdEspecie(int idespecie);

}
