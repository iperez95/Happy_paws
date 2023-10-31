package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Municipio;

public interface IMunicipio {

    List<Municipio> listadoMunicipios();
    List<Municipio> municipiosPorIdProvincia(int idProvincia);
    
}
