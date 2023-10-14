package com.tfgunir.happypaws.modelo.dao;



import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Protectora;

public interface IProtectoraDao {
    
    int altaProtectora (Protectora protectora);
    int modificarProtectora (Protectora protectora);
    int enabledProtectora (Protectora protectora);
    int borrarProtectora (int protectora);
    Protectora buscarUnaProtectora (int id);
    List<Protectora> buscarTodas();
    List<Protectora> buscarPorProvincia(int IdProvincia);
    List<Protectora> buscarPorMunicipio (int IdMunicipio);

}
