package com.tfgunir.happypaws.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;

public interface RespuestasRepository extends JpaRepository<RespuestasAdoptante, Integer> {
    @Query("SELECT r FROM RespuestasAdoptante r WHERE r.usuario.idusuario = ?1")
    List<RespuestasAdoptante> findByUserId(int idUsuario);

    @Modifying
    @Transactional
    @Query("DELETE FROM RespuestasAdoptante r WHERE r.usuario.idusuario = ?1")
    void deleteByUserId(int idusuario);
}
