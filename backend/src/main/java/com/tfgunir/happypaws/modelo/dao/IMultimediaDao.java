package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Multimedia;

public interface IMultimediaDao {

    int altaMultimedia(Multimedia multimedia);
    Multimedia modificarMultimedia(Multimedia multimedia);
    int borrarMultimedia(Multimedia multimedia);
    
    List<Multimedia> multimediasAnimal(int idAnimal);
    List<Multimedia> multimediasTodos();
    Multimedia multimediaPorId(int idMultimedia);

    
}
