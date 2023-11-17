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
    public List<PreguntasAdoptante> todasLasPreguntas() {
        return prerepo.findAll();
    }

     @Override
    public PreguntasAdoptante buscarPregunta(int idpregunta) {
        return prerepo.findById(idpregunta).orElse(null);
    }

    @Override
    public RespuestasAdoptante respuestasAdoptante(RespuestasAdoptante respuestasAdoptante) {
        return resprepo.save(respuestasAdoptante);
    }

    @Override
    public void guardarRespuestas(List<RespuestasAdoptante> respuestas) {
        resprepo.saveAll(respuestas);
    }

    @Override
    public void guardarRespuesta(RespuestasAdoptante respuesta) {
        resprepo.save(respuesta);
    }

   
    
}
