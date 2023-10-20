package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Adopcion;
import com.tfgunir.happypaws.modelo.repository.AdopcionRepository;

@Service
public class AdopcionDao implements IAdopcionDao {

    @Autowired
    AdopcionRepository AdopcionRepository;

    @Override
    public int altaAdopcion(Adopcion adopcion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altaAdopcion'");
    }

    @Override
    public int aceptarAdopcion(Adopcion adopcion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aceptarAdopcion'");
    }

    @Override
    public int borrarAdopcion(Adopcion adopcion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarAdopcion'");
    }

    @Override
    public int denegarAdopcion(Adopcion adopcion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'denegarAdopcion'");
    }

    @Override
    public int buscarUnaAdopcion(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnaAdopcion'");
    }

    @Override
    public List<Adopcion> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

    @Override
    public List<Adopcion> buscarTodasPorProtectora(int IdProtectora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodasPorProtectora'");
    }
}
