package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tfgunir.happypaws.modelo.dto.AdopcionDto;
import com.tfgunir.happypaws.modelo.entities.Adopcion;


public interface AdopcionRepository extends JpaRepository<Adopcion, Integer>{

    /**
     * Obtiene una lista de adopciones por protectora.
     * 
     * @param idProtectora El ID de la protectora.
     * @return Una lista de adopciones realizadas por la protectora especificada.
     */
    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where adop.idprotectora = ?1",
                nativeQuery = true)  
    List<Adopcion> adopcionesPorProtectora(int idProtectora);

    /**
     * Obtiene una lista de adopciones en curso por protectora.
     * 
     * @param idProtectora El ID de la protectora.
     * @return Una lista de adopciones en curso realizadas por la protectora especificada.
     */
    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where adop.idestadoadopcion = 1 and adop.idprotectora = ?1",
                nativeQuery = true)  
    List<Adopcion> adopcionesEnCursoPorProtectora(int idProtectora);

    /**
     * Obtiene una lista de adopciones realizadas por protectora.
     * 
     * @param idProtectora El ID de la protectora.
     * @return Una lista de adopciones realizadas por la protectora especificada.
     */
    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where adop.idestadoadopcion = 2 and adop.idprotectora = ?1",
                nativeQuery = true)  
    List<Adopcion> adopcionesRealizadasPorProtectora(int idProtectora);

    /**
     * Obtiene una lista de adopciones rechazadas por protectora.
     * 
     * @param idProtectora El ID de la protectora.
     * @return Una lista de adopciones rechazadas por la protectora especificada.
     */
    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where adop.idestadoadopcion = 3 and adop.idprotectora = ?1",
                nativeQuery = true)  
    List<Adopcion> adopcionesRechazadasPorProtectora(int idProtectora);

    
    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join PROTECTORAS prote on prote.idprotectora = adop.idprotectora \r\n"
                + "where adop.idestadoadopcion IN(3,2) and adop.idprotectora = ?1",
                nativeQuery = true) 
    List<Adopcion> adopcionesCompletadasPorIdProtectora(int idProtectora);

    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "inner join USUARIOS usu on usu.idusuario = adop.idusuario \r\n"
                + "where adop.idusuario = ?1",
                nativeQuery = true)
    List<Adopcion> findByIdUsuario(int idAdoptante);

    @Query(value = "SELECT adop.* FROM ADOPCIONES adop \r\n"
                + "where adop.idanimal = ?1 and adop.idusuario = ?2",
                nativeQuery = true)
    List<Adopcion> findByIdAnimalAndIdUsuario(int idanimal, int idusuario);
}
