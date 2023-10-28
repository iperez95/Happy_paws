package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfgunir.happypaws.modelo.entities.Multimedia;
import com.tfgunir.happypaws.modelo.repository.MultimediaRepository;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarMultimedia'");
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
