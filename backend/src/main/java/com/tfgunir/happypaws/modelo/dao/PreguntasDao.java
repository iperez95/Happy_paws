package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.repository.PreguntasRepository;

@Service
public class PreguntasDao implements IPreguntasDao     {

    @Autowired
    PreguntasRepository prerepo;
    @Override
    public List<PreguntasAdoptante> buscarTodas() {
        return prerepo.findAll();
    }
    
}
