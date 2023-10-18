package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.repository.AnimalRepository;

@Service
public class AnimalDao implements IAnimalDao{

    @Autowired
    AnimalRepository Anirepo;

    @Override
    public int altaAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altaAnimal'");
    }

    @Override
    public int modificarAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarAnimal'");
    }

    @Override
    public int enabledAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enabledAnimal'");
    }

    @Override
    public int borrarAnimal(Animal animal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarAnimal'");
    }

    @Override
    public Animal buscarUnAnimal(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnAnimal'");
    }

    @Override
    public List<Animal> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
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
