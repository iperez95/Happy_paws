package com.tfgunir.happypaws.modelo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfgunir.happypaws.modelo.dto.AnimalDto;
import com.tfgunir.happypaws.modelo.entities.Animal;
import com.tfgunir.happypaws.modelo.entities.Multimedia;
import com.tfgunir.happypaws.modelo.repository.AnimalRepository;
import com.tfgunir.happypaws.modelo.repository.MultimediaRepository;

@Service
public class AnimalDao implements IAnimalDao{

    @Autowired
    AnimalRepository aniRepo;
    @Autowired
    MultimediaRepository multiRepo;

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
            Optional<Animal> optionalAnimal = aniRepo.findById(id);

            if (optionalAnimal.isPresent()) {
                Animal animal = optionalAnimal.get();

                // Eliminar fotos asociadas antes de eliminar el animal
                eliminarFotosAsociadas(animal);

                // Ahora puedes eliminar el animal
                aniRepo.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void eliminarFotosAsociadas(Animal animal) {
                    // Recupera y eliminar fotos asociadas en multimedia
        List<Multimedia> fotos = multiRepo.todosMultimediasAnimal(animal.getIdanimal());

        for (Multimedia foto : fotos) {
            // Elimina las fotos del sistema
            // Eliminar la entrada de multimedia
            multiRepo.delete(foto);
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

      @Override
    public List<Animal> buscarPorNombreContiene(String nombre) {
        try{
            return aniRepo.buscarPorNombreContiene(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        animalDto.setNombreEspecie(animal.getRaza().getEspecie().getEspecie());
        animalDto.setIdRaza(animal.getRaza().getIdraza());
        animalDto.setNombreRaza(animal.getRaza().getRaza());
        animalDto.setIdSexo(animal.getSexo().getIdsexo());
        animalDto.setNombreSexo(animal.getSexo().getSexo());
        animalDto.setIdTamano(animal.getTamano().getIdtamano());
        animalDto.setNombreTamano(animal.getTamano().getTamano());
        return animalDto;
    }

    @Override
    public List<Animal> filtrarAnimales(String especie, String raza, String sexo, String tamano, String provincia, Boolean envio) {
        return aniRepo.filtrarAnimales(especie, raza, sexo, tamano, provincia, envio);
    }
}