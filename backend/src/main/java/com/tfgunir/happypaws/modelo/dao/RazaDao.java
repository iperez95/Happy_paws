package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Raza;
import com.tfgunir.happypaws.modelo.repository.RazaRepository;

@Service
public class RazaDao implements IRazaDao{

    @Autowired
        RazaRepository razRepo;

    @Override
    public Raza buscarRazaId(int id) {
        return razRepo.findById(id).orElse(null);
    }

    @Override
    public List<Raza> buscarTodos() {
        return razRepo.findAll();
    }

    @Override
    public List<Raza> razasPorIdEspecie(int idespecie) {
        return razRepo.razasPorIdEspecie(idespecie);
    
}
    
}
