package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.dto.MultimediaDto;
import com.tfgunir.happypaws.modelo.entities.Multimedia;
import com.tfgunir.happypaws.modelo.repository.MultimediaRepository;

@Service
public class MultimediaDao implements IMultimediaDao {

    @Autowired
    MultimediaRepository multirepo;

    @Override
    public int altaMultimedia(Multimedia multimedia) {
        try{
           multirepo.save(multimedia);
           return 1;}
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

   

    @Override
    public Multimedia modificarMultimedia(Multimedia multimedia) {
        if(multimediaPorId(multimedia.getIdmultimedia())!=null)
            return multirepo.save(multimedia);
        else
            return null;
    }

    @Override
    public int borrarMultimedia(Multimedia multimedia) {
         try{
           multirepo.delete(multimedia);
           return 1;}
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Multimedia> multimediasAnimal(int idAnimal) {
       return multirepo.todosMultimediasAnimal(idAnimal);
    }

    @Override
    public List<Multimedia> multimediasTodos() {
        return multirepo.findAll();
    }

    @Override
    public Multimedia multimediaPorId(int idMultimedia) {
        return multirepo.findById(idMultimedia).get();
    }

    
           
}
