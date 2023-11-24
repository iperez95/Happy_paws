package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Especie;
import com.tfgunir.happypaws.modelo.repository.EspecieRepository;

@Service
public class EspecieDao implements IEspecieDao {
    
    @Autowired
    EspecieRepository espRepo;

    @Override
    public Especie buscarEspecieId(int id) {
        return espRepo.findById(id).orElse(null);
    }

    @Override
    public List<Especie> buscarTodos() {
        return espRepo.findAll();
    }

}
    
