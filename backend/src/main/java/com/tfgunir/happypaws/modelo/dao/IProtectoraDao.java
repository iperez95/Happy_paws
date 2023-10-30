package com.tfgunir.happypaws.modelo.dao;



import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Protectora;

public interface IProtectoraDao {
    
    int altaProtectora (Protectora protectora);
    Protectora modificarProtectora (Protectora protectora);
    int bajaProtectora (Protectora protectora);
    int enabledProtectora (Protectora protectora);
    int borrarProtectora (Protectora protectora);
    
    Protectora buscarProtectoraId (int id);
    List<Protectora> listadoProtectoras();
    List<Protectora> listadoProtectorasMunicProv();
    List<Protectora> buscarPorProvincia(int IdProvincia);
    
}
