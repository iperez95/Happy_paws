package com.tfgunir.happypaws.modelo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Protectora;


public interface ProtectoraRepository extends JpaRepository<Protectora, Integer> {
       
@Query(value = "SELECT prote.*, muni.MUNICIPIO, prov.PROVINCIA FROM protectoras prote\r\n"
            + "inner join municipios muni on muni.IDMUNICIPIO = prote.IDMUNICIPIO \r\n"
            + "inner join provincias prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n"
            + "where prov.PROVINCIA = ?1",
			nativeQuery = true)    
List<Protectora> protetorasPorNombreProvincia(String nombre);

@Query(value = "SELECT prote.*, muni.MUNICIPIO, prov.PROVINCIA FROM protectoras prote\r\n"
            + "inner join municipios muni on muni.IDMUNICIPIO = prote.IDMUNICIPIO \r\n"
            + "inner join provincias prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n",
			nativeQuery = true)    
List<Protectora> listadoProtectorasMuniProv();

@Query(value = "SELECT prote.*, muni.MUNICIPIO, prov.PROVINCIA FROM protectoras prote\r\n"
            + "inner join municipios muni on muni.IDMUNICIPIO = prote.IDMUNICIPIO \r\n"
            + "inner join provincias prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n"
            + "where prov.IDPROVINCIA = ?1",
			nativeQuery = true)    
List<Protectora> protetorasPorIdProvincia(int idprovincia);
}
