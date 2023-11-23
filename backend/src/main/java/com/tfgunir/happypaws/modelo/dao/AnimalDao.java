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
    public boolean enabledAnimal(Animal animal) {
        try {
            aniRepo.save(animal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
    public List<Animal> buscarPorIdMunicipio(int idmunicipio) {
        return aniRepo.buscarPorIdMunicipio(idmunicipio);
    }

    @Override
    public List<Animal> buscarPorProvincia(String provincia) {
        return aniRepo.buscarPorMunicipio(provincia);
    }
    
    @Override
    public List<Animal> buscarPorIdProvincia(int idprovincia) {
        return aniRepo.buscarPorIdProvincia(idprovincia);
    }
    
    @Override
    public List<Animal> buscarPorProtectora(String nombre) {
        return aniRepo.buscarPorProtectora(nombre);
    }

    @Override
    public List<Animal> buscarPorIdProtectora(int idprotectora) {
        return aniRepo.buscarPorIdProtectora(idprotectora);
    }

    @Override
    public List<Animal> buscarPorEspecie(int idespecie) {
        return aniRepo.buscarPorEspecie(idespecie);
    }

    @Override
    public List<Animal> buscarPorSexo(int idsexo) {
        return aniRepo.buscarPorSexo(idsexo);
    }

    @Override
    public List<Animal> buscarPorRaza(int idraza) {
        return aniRepo.buscarPorRaza(idraza);
    }

    @Override
    public List<Animal> buscarPorTamaño(int idtamaño) {
        return aniRepo.buscarPorTamaño(idtamaño);
    }

    @Override
    public List<Animal> buscarPorEnvio(boolean envio) {
        return aniRepo.buscarPorEnvio(envio);
    }
}
