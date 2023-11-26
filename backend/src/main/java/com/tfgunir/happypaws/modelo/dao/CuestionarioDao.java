package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.dto.RespuestasAdoptanteDto;
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

    public List<RespuestasAdoptanteDto> preguntasYRespuestasPorUsuario(int idUsuario) {
        List<RespuestasAdoptante> respuestas = resprepo.findByUserId(idUsuario);

        if (respuestas.size() == 0) {
            return null;
        }

        List<RespuestasAdoptanteDto> respuestasDTO = respuestas.stream()
            .map(r -> {
                RespuestasAdoptanteDto dto = new RespuestasAdoptanteDto();
                dto.setRespuesta(r.getRespuesta());
                dto.setPregunta(r.getPreguntasAdoptante().getPregunta());
                return dto;
            })
            .collect(Collectors.toList());
        return respuestasDTO;
    }
    public RespuestasAdoptante updateRespuesta(RespuestasAdoptante respuesta) {
        return resprepo.save(respuesta);
    }

    public Boolean borrarRespuestasDeUsuario(int idusuario) {
        try {
            resprepo.deleteByUserId(idusuario);
            return true;
        } catch (Exception e) {
            System.out.println("Error al borrar las respuestas del usuario: " + e);
            return false;
        }
    }
}
