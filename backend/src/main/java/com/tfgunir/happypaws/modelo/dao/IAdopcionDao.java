package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import com.tfgunir.happypaws.modelo.dto.AdopcionDto;
import com.tfgunir.happypaws.modelo.entities.Adopcion;

public interface IAdopcionDao {
    Adopcion altaAdopcion(Adopcion adopcion);
    
    int enCursoAdopcion(Adopcion adopcion);
    int realizarAdopcion(Adopcion adopcion);
    int rechazarAdopcion(Adopcion adopcion);
    int borrarAdopcion(Adopcion adopcion);

    List<Adopcion> buscarTodasAdopciones();
    List<Adopcion> adopcionesPorIDProtectora(int IdProtectora);

    Adopcion buscarAdopcionId(int id);
    
    // List<AdopcionDto> adopcionesProtectoraPorIdDto(int IdProtectora);
    
    AdopcionDto convertirAdopcionDto(Adopcion adopcion);
    
}
