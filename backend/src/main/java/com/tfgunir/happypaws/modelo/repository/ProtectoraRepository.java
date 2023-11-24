package com.tfgunir.happypaws.modelo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.entities.Protectora;


public interface ProtectoraRepository extends JpaRepository<Protectora, Integer> {
    @Query(value = "SELECT prote.*, muni.MUNICIPIO, prov.PROVINCIA FROM PROTECTORAS prote\r\n"
                + "inner join MUNICIPIOS muni on muni.IDMUNICIPIO = prote.IDMUNICIPIO \r\n"
                + "inner join PROVINCIAS prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n"
                + "where prov.PROVINCIA = ?1 prote.idestadoprotectora = 1",
                nativeQuery = true)    
    List<Protectora> protetorasPorNombreProvincia(String nombre);

    @Query(value = "SELECT prote.*, muni.MUNICIPIO, prov.PROVINCIA FROM PROTECTORAS prote\r\n"
                + "inner join MUNICIPIOS muni on muni.IDMUNICIPIO = prote.IDMUNICIPIO \r\n"
                + "inner join PROVINCIAS prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n"
                + "where prote.idestadoprotectora = 1",
                            nativeQuery = true)    
    List<Protectora> listadoProtectorasMuniProv();

    @Query(value = "SELECT prote.*, muni.MUNICIPIO, prov.PROVINCIA FROM PROTECTORAS prote\r\n"
                + "inner join MUNICIPIOS muni on muni.IDMUNICIPIO = prote.IDMUNICIPIO \r\n"
                + "inner join PROVINCIAS prov on prov.IDPROVINCIA = muni.IDPROVINCIA \r\n"
                + "where prov.IDPROVINCIA = ?1 AND prote.idestadoprotectora = 1",
                nativeQuery = true)    
    List<Protectora> protetorasPorIdProvincia(int idprovincia);

    @Query("SELECT p FROM Protectora p WHERE p.usuario.idusuario = ?1")
    Protectora findByUsuario(int idUsuario);
}
