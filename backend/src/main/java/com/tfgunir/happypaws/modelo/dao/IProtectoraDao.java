package com.tfgunir.happypaws.modelo.dao;



import java.util.List;

import com.tfgunir.happypaws.modelo.entities.Protectora;

public interface IProtectoraDao {
    
    int altaProtectora (Protectora protectora);
    Protectora actualizarProtectora (Protectora protectora);
    int bajaProtectora (Protectora protectora);
    int enabledProtectora (Protectora protectora);
    int borrarProtectora (Protectora protectora);
    
    Protectora buscarProtectoraId (int id);
    List<Protectora> listadoProtectoras();
    List<Protectora> listadoProtectorasMunicProv();
    List<Protectora> buscarPorIdProvincia(int IdProvincia);
    List<Protectora> buscarPorNombreProvincia(String nombre);

    void subirLogo(int id, String urlLogo);
    
}
