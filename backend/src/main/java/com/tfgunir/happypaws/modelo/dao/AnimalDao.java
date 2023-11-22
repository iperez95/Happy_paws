package com.tfgunir.happypaws.modelo.dao;

import java.util.List;

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
        try {
            aniRepo.save(animal);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean borrarAnimal(int id) {
        try {
            aniRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Animal buscarAnimalId(int id) {
        return aniRepo.findById(id).orElse(null);
    }

    @Override
    public List<Animal> buscarTodos() {
        return aniRepo.findAll();
    }

    @Override
    public List<Animal> buscarPorMunicipio(String municipio) {
        return aniRepo.buscarPorMunicipio(municipio);
    }

    @Override
    public List<Animal> buscarPorProvincia(String provincia) {
        return aniRepo.buscarPorMunicipio(provincia);
    }
    
    @Override
    public List<Animal> buscarPorProtectora(String nombre) {
        return aniRepo.buscarPorProtectora(nombre);
    }

    // @Override
    // public List<Animal> buscarPorEspecie(String especie) {
    //     return aniRepo.buscarPorEspecie(especie);
    // }

    // @Override
    // public List<Animal> buscarPorSexo(String sexo) {
    //     return aniRepo.buscarPorSexo(sexo);
    // }

    // @Override
    // public List<Animal> buscarPorRaza(String raza) {
    //     return aniRepo.buscarPorRaza(raza);
    // }

    // @Override
    // public List<Animal> buscarPorTamano(String tamaño) {
    //     return aniRepo.buscarPorTamaño(tamaño);
    // }

    // @Override
    // public List<Animal> buscarPorEnvío(String envio) {
    //     return aniRepo.buscarPorEnvío(envio);
    //     }
}
