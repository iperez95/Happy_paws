package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Tamano;


    public interface ITamanoDao {

    Tamano buscarTamanoId (int id);
    List<Tamano> buscarTodos();

}
