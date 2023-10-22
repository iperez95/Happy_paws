package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.entities.PreguntasAdoptante;
import com.tfgunir.happypaws.modelo.entities.RespuestasAdoptante;

public interface ICuestionarioDao {

List<PreguntasAdoptante> buscarTodas();
int buscarPregunta(int idpregunta);

int respuestasAdoptante (RespuestasAdoptante respuestasAdoptante);
    
}
