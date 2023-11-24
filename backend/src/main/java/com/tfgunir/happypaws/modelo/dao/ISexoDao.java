package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Sexo;



public interface ISexoDao {

    Sexo buscarSexoId (int idsexo);
    List<Sexo> buscarTodos();
}
