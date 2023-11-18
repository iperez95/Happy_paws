package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.dto.AdopcionDto;
import com.tfgunir.happypaws.modelo.entities.Adopcion;
import com.tfgunir.happypaws.modelo.entities.Estadosadopcion;
import com.tfgunir.happypaws.modelo.repository.AdopcionRepository;

@Service
public class AdopcionDao implements IAdopcionDao {

    @Autowired
    AdopcionRepository adopRepo;

    

    @Override
    public List<Adopcion> buscarTodasAdopciones() {
        return adopRepo.findAll();
    }

      @Override
    public Adopcion buscarAdopcionId(int id) {
        return adopRepo.findById(id).orElse(null);
    }

      @Override
    public List<Adopcion> adopcionesPorIDProtectora(int IdProtectora) {
        return adopRepo.adopcionesPorProtectora(IdProtectora);
    }


    
    @Override
    public int altaAdopcion(Adopcion adopcion) {
        try{
            //Asignamos el estado de adopcion en curso
            Estadosadopcion estadoAdopcionTemporal = new Estadosadopcion();
            estadoAdopcionTemporal.setEnCurso();
            adopcion.setEstadosadopcion(estadoAdopcionTemporal);
            //Guardamos la adopcion
            adopRepo.save(adopcion);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

   


  

    @Override
    public int borrarAdopcion(Adopcion adopcion) {
        try{
            adopRepo.delete(adopcion);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    



    @Override
    public int enCursoAdopcion(Adopcion adopcion) {
        if (buscarAdopcionId(adopcion.getIdadopcion())!=null){
            try{
                Estadosadopcion estadoAdopcionTemporal = new Estadosadopcion();
                estadoAdopcionTemporal.setEnCurso();
                adopcion.setEstadosadopcion(estadoAdopcionTemporal);
                adopRepo.save(adopcion);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
            return 0;        
    }



    @Override
    public int realizarAdopcion(Adopcion adopcion) {
         if (buscarAdopcionId(adopcion.getIdadopcion())!=null){
            try{
                Estadosadopcion estadoAdopcionTemporal = new Estadosadopcion();
                estadoAdopcionTemporal.setRealizada();
                adopcion.setEstadosadopcion(estadoAdopcionTemporal);
                adopRepo.save(adopcion);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
            return 0;   
    }



    @Override
    public int rechazarAdopcion(Adopcion adopcion) {
         if (buscarAdopcionId(adopcion.getIdadopcion())!=null){
            try{
                Estadosadopcion estadoAdopcionTemporal = new Estadosadopcion();
                estadoAdopcionTemporal.setRechazada();
                adopcion.setEstadosadopcion(estadoAdopcionTemporal);
                adopRepo.save(adopcion);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
            return 0;   
    }

  

    // @Override
    // public List<Adopcion> adopcionesProtectoraPorId(int IdProtectora) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'adopcionesProtectoraPorId'");
    // }

    // @Override
    // public List<AdopcionDto> adopcionesProtectoraPorIdDto(int IdProtectora) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'adopcionesProtectoraPorIdDto'");
    // }
    

    public AdopcionDto convertirAdopcionDto(Adopcion adopcion){
        AdopcionDto adopcionDto = new AdopcionDto();
        adopcionDto.setIdAdopcion(adopcion.getIdadopcion());
        adopcionDto.setIdProtectora(adopcion.getProtectora().getIdprotectora());
        adopcionDto.setIdUsuario(adopcion.getUsuario().getIdusuario());
        adopcionDto.setIdAnimal(adopcion.getAnimal().getIdanimal());
        adopcionDto.setIdEstadoAdopcion(adopcion.getEstadosadopcion().getIdestadoadopcion());
        return adopcionDto;
    }

  


    
}
