package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import com.tfgunir.happypaws.modelo.entities.Adopcion;

public interface IAdopcionDao {
    int altaAdopcion(Adopcion adopcion);
    int aceptarAdopcion(Adopcion adopcion);
    int borrarAdopcion(Adopcion adopcion);
    int denegarAdopcion(Adopcion adopcion);
    int buscarUnaAdopcion(int id);
    List<Adopcion> buscarTodas();
    List<Adopcion> buscarTodasPorProtectora(int IdProtectora);
}
