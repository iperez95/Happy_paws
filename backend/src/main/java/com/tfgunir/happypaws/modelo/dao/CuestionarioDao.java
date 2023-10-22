package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;
import com.tfgunir.happypaws.modelo.repository.PreguntasRepository;
import com.tfgunir.happypaws.modelo.repository.RespuestasRepository;

@Service
public class CuestionarioDao implements ICuestionarioDao     {

    @Autowired
    PreguntasRepository prerepo;

    @Autowired
    RespuestasRepository resprepo;

    
    
    @Override
    public List<PreguntasAdoptante> buscarTodas() {
        return prerepo.findAll();
    }

    @Override
    public int respuestasAdoptante(RespuestasAdoptante respuestasAdoptante) {
        try {
            resprepo.save(respuestasAdoptante);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int buscarPregunta(int idpregunta) {
        // TODO Auto-generated method stub
        return prerepo.findById(idpregunta).get().getIdpregunta();
    }
    
}
