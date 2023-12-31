package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.dto.CuestionarioDto;
import com.tfgunir.happypaws.modelo.dto.RespuestasAdoptanteDto;
import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;

public interface ICuestionarioDao {
    List<PreguntasAdoptante> todasLasPreguntas();
    PreguntasAdoptante buscarPregunta(int idpregunta);
    void guardarRespuestas(List<RespuestasAdoptante> respuestasList);
    void guardarRespuesta(RespuestasAdoptante respuesta);
    RespuestasAdoptante respuestasAdoptante (RespuestasAdoptante respuestasAdoptante);
    List<RespuestasAdoptanteDto> preguntasYRespuestasPorUsuario(int idUsuario);
    Boolean borrarRespuestasDeUsuario(int idusuario);
}
