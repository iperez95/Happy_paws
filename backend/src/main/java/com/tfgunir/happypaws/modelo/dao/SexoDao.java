package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Sexo;
import com.tfgunir.happypaws.modelo.repository.SexoRepository;

@Service
public class SexoDao implements ISexoDao {

    @Autowired
    SexoRepository sexRepo;

    @Override
    public Sexo buscarSexoId(int id) {
        return sexRepo.findById(id).orElse(null);
    }

    @Override
    public List<Sexo> buscarTodos() {
        return sexRepo.findAll();
    }
    
}
