package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Provincia;
import com.tfgunir.happypaws.modelo.repository.ProvinciaRepository;

@Service
public class ProvinciaDao implements IProvincia{

    @Autowired
    ProvinciaRepository provrepo;

    @Override
    public List<Provincia> listadoProvincias() {
        return provrepo.findAll();
    }
    
}
