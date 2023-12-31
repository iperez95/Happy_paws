package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.dto.ProtectoraDto;
import com.tfgunir.happypaws.modelo.entities.Estadosprotectora;
import com.tfgunir.happypaws.modelo.entities.Protectora;
import com.tfgunir.happypaws.modelo.repository.ProtectoraRepository;

@Service
public class ProtectoraDao implements IProtectoraDao {

    @Autowired
    ProtectoraRepository protrepo;

    @Override
    public int altaProtectora(Protectora protectora) {
        try {
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIsActivated();
            protectora.setEstadosprotectora(estadoProtectoraTemporal);
            protrepo.save(protectora);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

      @Override
    public List<Protectora> buscarPorNombreContiene(String nombre) {
        try{
            return protrepo.buscarPorNombreContiene(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     //TODO dav añadir al return mensaje protectora no encontrada
    @Override
    public Protectora actualizarProtectora(Protectora protectora) {
        try{
        if (buscarProtectoraId(protectora.getIdprotectora())!=null){}
            protrepo.save(protectora);
           return protectora;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     @Override
    public int cambiarEstadoInactivoProtectora(Protectora protectora) {
        if (protectora!=null){
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(2);
            protectora.setEstadosprotectora(estadoProtectoraTemporal);
            return 1;
        }
        else 
            return 0;
    }

     @Override
    public int enabledProtectora(Protectora protectora) {
        if (buscarProtectoraId(protectora.getIdprotectora())!=null){
            Estadosprotectora estadoProtectoraTemporal = new Estadosprotectora();
            estadoProtectoraTemporal.setIdestadoprotectora(1);
            return 1;
        }
        else 
            return 0;
    }

    public Protectora buscarProtectoraPorUsuario(int idUsuario) {
        return protrepo.findByUsuario(idUsuario);
    }

    @Override
    public Protectora buscarProtectoraId(int id) {
        return protrepo.findById(id).orElse(null);
    }

    @Override
    public List<Protectora> listadoProtectoras() {
        return protrepo.findAll();
    }

    @Override
    public List<Protectora> listadoProtectorasMunicProv() {
        return protrepo.listadoProtectorasMuniProv();
    }

    @Override
    public List<Protectora> buscarPorIdProvincia(int IdProvincia) {
        return protrepo.protetorasPorIdProvincia(IdProvincia);
    }

    @Override
    public List<Protectora> buscarPorNombreProvincia(String nombre) {
        return protrepo.protetorasPorNombreProvincia(nombre);
    }

    @Override
    public int borrarProtectora(Protectora protectora) {
        try{
            protrepo.delete(protectora);
            return 1;}
         catch (Exception e) {
             e.printStackTrace();
             return 0;
         }
    }

    @Override
    public void subirLogo(int id, String urlLogo) {
        //Obtenemos la protectora por su ID
        Protectora protectora = buscarProtectoraId(id);
        if (protectora!=null){
            //Asignamos el logo
            protectora.setUrlLogo(urlLogo);
            //Guardamos la protectora
            protrepo.save(protectora);
        }
    }

    public ProtectoraDto convertirProtectoraDto (Protectora protectora){
        ProtectoraDto protectoraDto = new ProtectoraDto();
        protectoraDto.setIdprotectora(protectora.getIdprotectora());
        protectoraDto.setDescripcion(protectora.getDescripcion());
        protectoraDto.setDireccion(protectora.getDireccion());
        protectoraDto.setEmail(protectora.getEmail());
        protectoraDto.setNombre(protectora.getNombre());
        protectoraDto.setTelefono(protectora.getTelefono());
        protectoraDto.setUrlLogo(protectora.getUrlLogo());
        protectoraDto.setIdEstadoProtectora(protectora.getEstadosprotectora().getIdestadoprotectora());
        protectoraDto.setNombreEstadoProtectora(protectora.getEstadosprotectora().getEstado());
        protectoraDto.setIdProvincia(protectora.getMunicipio().getProvincia().getIdprovincia());
        protectoraDto.setNombreProvincia(protectora.getMunicipio().getProvincia().getProvincia());
        protectoraDto.setIdMunicipio(protectora.getMunicipio().getIdmunicipio());
        protectoraDto.setNombreMunicipio(protectora.getMunicipio().getMunicipio());
        return protectoraDto;
    }

  
 

}
