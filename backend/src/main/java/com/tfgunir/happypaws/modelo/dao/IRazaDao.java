package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Raza;

public interface IRazaDao {


    Raza buscarRazaId (int idraza);
    List<Raza> buscarTodos();

} 
