package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.repository.AnimalRepository;

@Service
public class AnimalDao implements IAnimalDao{

    @Autowired
    AnimalRepository aniRepo;

    @Override
    public boolean altaAnimal(Animal animal) {
       try{
        aniRepo.save(animal);
        return true;
       } catch (Exception e){
        e.printStackTrace();
        return false;
       }
    }

    @Override
    public boolean modificarAnimal(Animal animal) {
        try {
            aniRepo.save(animal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int enabledAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enabledAnimal'");
    }

    @Override
    public boolean borrarAnimal(int idanimal) {
        try {
            aniRepo.deleteById(idanimal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Animal buscarUnAnimal(int id) {
        return aniRepo.findById(id).orElse(null);
    }

    @Override
    public List<Animal> buscarTodos() {
        return aniRepo.findAll();
    }

    @Override
    public List<Animal> buscarPorProvincia(int IdProvincia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorProvincia'");
    }

    @Override
    public List<Animal> buscarPorMunicipio(int IdMunicipio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorMunicipio'");
    }

    @Override
    public List<Animal> buscarPorProtectora(int IdProtectora) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorProtectora'");
    }

    @Override
    public List<Animal> buscarPorAtributos(int valoresAtributo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorAtributos'");
    }
    

}
