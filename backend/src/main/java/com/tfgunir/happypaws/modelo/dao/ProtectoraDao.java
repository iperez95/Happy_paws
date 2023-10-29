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
            //Se asigna estado 3 pendiente de validacion, en el alta de la protectora.
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(3);
            protectora.setEstadosprotectora(estadoProtectoraTemporal);
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
    public List<Protectora> listadoProtectoras() {
        return protrepo.findAll();
    }

    @Override
    public List<Protectora> buscarPorProvincia(int IdProvincia) {
        return protrepo.protetorasPorIdProvincia(IdProvincia);
    }

    @Override
    public int borrarProtectora(Protectora protectora) {
        try{
            protrepo.delete(protectora);
            return 1;}
         catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
    }
 

}
