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

    /**
     * Busca todas las adopciones en la base de datos.
     * 
     * @return Una lista de objetos Adopcion que representan todas las adopciones encontradas.
     */
    @Override
    public List<Adopcion> buscarTodasAdopciones() {
        return adopRepo.findAll();
    }

    /**
     * Busca una adopcion por su ID en la base de datos.
     * 
     * @param id El ID de la adopcion a buscar.
     * @return El objeto Adopcion correspondiente al ID proporcionado, o null si no se encuentra.
     */
    @Override
    public Adopcion buscarAdopcionId(int id) {
        return adopRepo.findById(id).orElse(null);
    }

    /**
     * Busca todas las adopciones realizadas por una protectora en la base de datos.
     * 
     * @param IdProtectora El ID de la protectora.
     * @return Una lista de objetos Adopcion que representan las adopciones realizadas por la protectora.
     */
    @Override
    public List<Adopcion> adopcionesPorIDProtectora(int IdProtectora) {
        return adopRepo.adopcionesPorProtectora(IdProtectora);
    }

    /**
     * Guarda el objeto Adopcion dado en la base de datos.
     * 
     * @param adopcion El objeto Adopcion a guardar.
     * @return El objeto Adopcion guardado.
     */
    @Override
    public Adopcion altaAdopcion(Adopcion adopcion) {
        try{
            adopRepo.save(adopcion);
            return adopcion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Elimina un registro de adopción de la base de datos.
     * 
     * @param adopcion El registro de adopción a eliminar.
     * @return 1 si la eliminación es exitosa, 0 en caso contrario.
     */
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

    /**
     * Actualiza el estado de la adopción a "Realizada".
     * 
     * @param adopcion El objeto de adopción que se va a modificar.
     * @return 1 si la adopción se realizó correctamente, 0 en caso contrario.
     */
    @Override
    public int realizarAdopcion(Adopcion adopcion) {
        if (buscarAdopcionId(adopcion.getIdadopcion()) != null){
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

    /**
     * Rechaza una solicitud de adopción.
     * 
     * @param adopcion La solicitud de adopción que se va a rechazar.
     * @return 1 si la solicitud de adopción se rechaza correctamente, 0 en caso contrario.
     */
    @Override
    public int rechazarAdopcion(Adopcion adopcion) {
        if (buscarAdopcionId(adopcion.getIdadopcion()) != null){
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

    /**
     * Actualiza el estado de adopción de una adopción dada a "En Curso".
     * 
     * @param adopcion El objeto de adopción a actualizar.
     * @return 1 si el estado de adopción se actualiza correctamente, 0 en caso contrario.
     */
    @Override
    public int enCursoAdopcion(Adopcion adopcion) {
        if (buscarAdopcionId(adopcion.getIdadopcion()) != null){
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

    /**
     * Convierte una instancia de Adopcion a AdopcionDto.
     * 
     * @param adopcion El objeto Adopcion que se va a convertir.
     * @return El objeto AdopcionDto convertido.
     */
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
