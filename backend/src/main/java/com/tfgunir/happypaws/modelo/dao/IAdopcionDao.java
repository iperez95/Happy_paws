package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Adopcion;

public interface IAdopcionDao {
    int altaAdopcion(Adopcion adopcion);
    int enCursoAdopcion(Adopcion adopcion);
    int realizarAdopcion(Adopcion adopcion);
    int rechazarAdopcion(Adopcion adopcion);
    int borrarAdopcion(Adopcion adopcion);
    Adopcion buscarAdopcionId(int id);
    List<Adopcion> buscarTodas();
    List<Adopcion> buscarTodasPorProtectora(int IdProtectora);
}
