package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.dto.AdopcionDto;
import com.tfgunir.happypaws.modelo.entities.Adopcion;

public interface AdopcionRepository extends JpaRepository<Adopcion, Integer>{

     @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where adop.idprotectora = ?1",
                nativeQuery = true)  
    List<Adopcion> adopcionesPorProtectora(int idProtectora);

    // @Query(value = "SELECT usuario.idusuario, prote.idprotectora, ani.idanimal, est.idestadoadopcion FROM ADOPCIONES adop \r\n"
    //                 + " inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
    //                 + " inner join USUARIOS usu on usu.idusuario = adop.idusuario \r\n"
    //                 + " inner join ANIMALES ani on ani.idanimal = adop.idanimal \r\n"
    //                 + " inner join ESTADOSADOPCIONES est on est.idestadoadopcion = adop.idestadoadopcion \r\n"
    //                 + " where prote.idprotectora = ?1",
    //             nativeQuery = true)  
    // List<Adopcion> adopcionesPorProtectora(int idProtectora);

    // @Query(value = "SELECT * FROM ADOPCIONES \r\n"
    //                 + " where idAdopcion = ?1",
    //             nativeQuery = true)  
    // AdopcionDto adopcionPorID(int idAdopcion);

    

    // @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
    //             + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
    //             + "where adop.idprotectora = ?1",
    //             nativeQuery = true)  
    // List<AdopcionDto> adopcionesPorProtectoraDto(int idAdopcion);
}
