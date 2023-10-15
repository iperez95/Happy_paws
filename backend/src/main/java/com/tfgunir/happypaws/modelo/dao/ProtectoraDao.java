package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.repository.ProtectoraRepository;

@Service
public class ProtectoraDao implements IProtectoraDao{

    @Autowired
    ProtectoraRepository protrepo;

    @Override
    public int altaProtectora(Protectora protectora) {
        int filas=0;
        try{
            protrepo.save(protectora);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return filas;
    }

    @Override
    public int modificarProtectora(Protectora protectora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarProtectora'");
    }

    @Override
    public int enabledProtectora(Protectora protectora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enabledProtectora'");
    }

    @Override
    public int borrarProtectora(int protectora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarProtectora'");
    }

    @Override
    public Protectora buscarUnaProtectora(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnaProtectora'");
    }

    @Override
    public List<Protectora> buscarTodas() {
        return protrepo.findAll();
    }

    @Override
    public List<Protectora> buscarPorProvincia(int IdProvincia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorProvincia'");
    }

    @Override
    public List<Protectora> buscarPorMunicipio(int IdMunicipio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorMunicipio'");
    }
    
}
