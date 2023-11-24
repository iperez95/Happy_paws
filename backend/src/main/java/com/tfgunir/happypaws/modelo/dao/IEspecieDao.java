package com.tfgunir.happypaws.modelo.dao;
import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Especie;

public interface IEspecieDao {

    Especie buscarEspecieId (int idespecie);
    List<Especie> buscarTodos();
    
} 
