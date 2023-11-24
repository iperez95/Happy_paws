package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Tamano;
import com.tfgunir.happypaws.modelo.repository.TamanoRepository;

@Service
public class TamanoDao implements ITamanoDao{

    @Autowired
    TamanoRepository tamRepo;

    @Override
    public Tamano buscarTamanoId(int id) {
        return tamRepo.findById(id).orElse(null);
    }

    @Override
    public List<Tamano> buscarTodos() {
        return tamRepo.findAll();}
}