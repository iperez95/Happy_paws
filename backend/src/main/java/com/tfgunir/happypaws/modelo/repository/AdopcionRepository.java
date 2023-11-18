package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Adopcion;

public interface AdopcionRepository extends JpaRepository<Adopcion, Integer>{

    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where prote.idprotectora = ?1",
                nativeQuery = true)  
    List<Adopcion> adopcionesPorProtectora(int idAdopcion);
}
