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
    List<Adopcion> adopcionesEnCursoPorIdProtectora(int IdProtectora);
    List<Adopcion> adopcionesRealizadasPorIdProtectora(int IdProtectora);
    List<Adopcion> adopcionesRechazadasPorIdProtectora(int IdProtectora);
    List<Adopcion> adopcionesCompletadasPorIdProtectora(int IdProtectora);

    Adopcion buscarAdopcionId(int id);
    
    // List<AdopcionDto> adopcionesProtectoraPorIdDto(int IdProtectora);
    
    AdopcionDto convertirAdopcionDto(Adopcion adopcion);
    List<Adopcion> adopcionesPorIDAdoptante(int idAdoptante);
    Boolean existeAdopcionAnimalUsuario(int idanimal, int idusuario);   
}
