package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfgunir.happypaws.modelo.dto.AnimalDto;
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
            animal.setEnabled(!animal.getEnabled());
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
    public List<Animal> buscarPorIdMunicipio(int idmunicipio) {
        return aniRepo.buscarPorIdMunicipio(idmunicipio);
    }

    @Override
    public List<Animal> buscarPorIdProvincia(int idprovincia) {
        return aniRepo.buscarPorIdProvincia(idprovincia);
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
    public List<Animal> buscarPorTamaño(int idtamano) {
        return aniRepo.buscarPorTamaño(idtamano);
    }

    @Override
    public List<Animal> buscarPorEnvio(boolean envio) {
        return aniRepo.buscarPorEnvio(envio);
    }

    @Override
    public List<Animal> buscarSoloPerros() {
        return aniRepo.buscarSoloPerros();
    }

    @Override
    public List<Animal> buscarSoloGatos() {
        return aniRepo.buscarSoloGatos();
    }

    public AnimalDto convertirAnimalDto(Animal animal){
        AnimalDto animalDto = new AnimalDto();
        animalDto.setIdanimal(animal.getIdanimal());
        animalDto.setNombre(animal.getNombre());
        animalDto.setDescripcion(animal.getDescripcion());
        animalDto.setEnabled(animal.getEnabled());
        animalDto.setEnvio(animal.getEnvio());
        animalDto.setFechaAlta(animal.getFechaAlta());
        animalDto.setFechaNacimiento(animal.getFechaNacimiento());
        animalDto.setIdMunicipio(animal.getMunicipio().getIdmunicipio());
        animalDto.setNombreMunicipio(animal.getMunicipio().getMunicipio());
        animalDto.setIdProvincia(animal.getMunicipio().getProvincia().getIdprovincia());
        animalDto.setNombreProvincia(animal.getMunicipio().getProvincia().getProvincia());
        animalDto.setIdProtectora(animal.getProtectora().getIdprotectora());
        animalDto.setNombreProtectora(animal.getProtectora().getNombre());
        animalDto.setIdEspecie(animal.getRaza().getEspecie().getIdespecie());
        animalDto.setEspecie(animal.getRaza().getEspecie().getEspecie());
        animalDto.setIdRaza(animal.getRaza().getIdraza());
        animalDto.setRaza(animal.getRaza().getRaza());
        animalDto.setIdSexo(animal.getSexo().getIdsexo());
        animalDto.setSexo(animal.getSexo().getSexo());
        animalDto.setIdTamano(animal.getTamano().getIdtamano());
        animalDto.setTamano(animal.getTamano().getTamano());
        return animalDto;
    }

}