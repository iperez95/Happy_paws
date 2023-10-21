package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.repository.ProtectoraRepository;

@Service
public class ProtectoraDao implements IProtectoraDao {

    @Autowired
    ProtectoraRepository protrepo;

    @Override
    public int altaProtectora(Protectora protectora) {
        try {
            protrepo.save(protectora);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

     @Override
    public int bajaProtectora(Protectora protectora) {
        if (buscarProtectoraId(protectora.getIdprotectora())!=null){
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(2);
            return 1;
        }
        else 
            return 0;
    }

     @Override
    public int enabledProtectora(Protectora protectora) {
        if (buscarProtectoraId(protectora.getIdprotectora())!=null){
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(1);
            return 1;
        }
        else 
            return 0;
    }

    //TODO dav añadir al return mensaje protectora no encontrada
    @Override
    public Protectora modificarProtectora(Protectora protectora) {
        if (buscarProtectoraId(protectora.getIdprotectora())!=null)
            return protrepo.save(protectora);
        else
            return null;
        }

    @Override
    public Protectora buscarProtectoraId(int id) {
        return protrepo.findById(id).orElse(null);
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
    public int borrarProtectora(Protectora protectora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarProtectora'");
    }

    

}
