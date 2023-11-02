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
        return 0;
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

        @Override
        public List<Animal> buscarPorEspecie(String especie) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorEspecie'");
        }

        @Override
        public List<Animal> buscarPorSexo(String sexo) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorSexo'");
        }

        @Override
        public List<Animal> buscarPorRaza(String raza) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorRaza'");
        }

        @Override
        public List<Animal> buscarPorTamano(String tamano) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorTamano'");
        }

        @Override
        public List<Animal> buscarPorEnvío(String envio) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorEnvío'");
        }
    


}
