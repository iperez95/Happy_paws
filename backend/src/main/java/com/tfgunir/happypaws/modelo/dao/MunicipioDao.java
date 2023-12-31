package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Municipio;
import com.tfgunir.happypaws.modelo.repository.MunicipioRepository;

@Service
public class MunicipioDao implements IMunicipioDao {

    @Autowired
    MunicipioRepository munirepo;

    @Override
    public List<Municipio> listadoMunicipios() {
       return munirepo.findAll();
    }

    @Override
    public List<Municipio> municipiosPorIdProvincia(int idprov) {
       return munirepo.municipiosPorIdProvincia(idprov);
    }
    
    public Municipio municipioPorId(int id) {
        return munirepo.findById(id).orElse(null);
    }
}
