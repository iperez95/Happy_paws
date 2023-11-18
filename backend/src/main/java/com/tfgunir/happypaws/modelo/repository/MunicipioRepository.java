package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tfgunir.happypaws.modelo.entities.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer>{

    @Query(value = "SELECT muni.* FROM MUNICIPIOS muni \r\n"
            + "inner join PROVINCIAS prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n"
            + "where prov.IDPROVINCIA = ?1",
			nativeQuery = true)    
    List<Municipio> municipiosPorIdProvincia(int idProvincia);
    
}
