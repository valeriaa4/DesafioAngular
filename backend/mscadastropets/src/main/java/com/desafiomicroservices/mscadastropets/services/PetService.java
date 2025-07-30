package com.desafiomicroservices.mscadastropets.services;

import com.desafiomicroservices.mscadastropets.clients.TheCatAPIClient;
import com.desafiomicroservices.mscadastropets.clients.TheDogAPIClient;
import com.desafiomicroservices.mscadastropets.dtos.CatsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.DogsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.PetDtoRequest;
import com.desafiomicroservices.mscadastropets.enums.Especie;
import com.desafiomicroservices.mscadastropets.models.Pet;
import com.desafiomicroservices.mscadastropets.producer.CadastroPetProducer;
import com.desafiomicroservices.mscadastropets.repository.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TheDogAPIClient theDogAPIClient;
    @Autowired
    private TheCatAPIClient theCatAPIClient;
    @Autowired
    private CadastroPetProducer cadastroPetProducer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Pet createPet(PetDtoRequest petDtoRequest) {
        Pet pet = new Pet();
        pet.setTutor(petDtoRequest.getTutor());
        pet.setEmailTutor(petDtoRequest.getEmailTutor());
        pet.setNome(petDtoRequest.getNome());
        pet.setEspecie(petDtoRequest.getEspecie());
        pet.setRaca(petDtoRequest.getRaca());
        pet.setIdade(petDtoRequest.getIdade());
        pet.setPeso(petDtoRequest.getPeso());
        pet.setCor(petDtoRequest.getCor());
        pet.setDescricao(petDtoRequest.getDescricao());

        // implementar imagem
        if(pet.getEspecie().equals(Especie.Cachorro)){
            List<DogsBreedsDto> response = theDogAPIClient.listarRacas();
            for(DogsBreedsDto breeds : response){
                if(breeds.getNome().equals(pet.getRaca())){
                    pet.setImagem(breeds.getIdImagem());
                    pet.setImagem("https://cdn2.thedogapi.com/images/"+pet.getImagem()+".jpg");
                    petRepository.save(pet);
                    petCreated(pet);
                    return pet;
                } else{
                    pet.setImagem("Imagem não disponível");
                    petRepository.save(pet);
                }
            }
        } else {
            List<CatsBreedsDto> response = theCatAPIClient.listarRacas();
            for(CatsBreedsDto breeds : response){
                if(breeds.getNome().equals(pet.getRaca())){
                    pet.setImagem(breeds.getIdImagem());
                    pet.setImagem("https://cdn2.thecatapi.com/images/"+pet.getImagem()+".jpg");
                    petRepository.save(pet);
                    petCreated(pet);
                    return pet;
                } else{
                    pet.setImagem("Imagem não disponível");
                    petRepository.save(pet);
                }
            }
        }
        petCreated(pet);
        return pet;
    }

    public String petCreated(Pet dadosPetCadastrado) {
        try{
            cadastroPetProducer.petCreated(dadosPetCadastrado);
        } catch (JsonProcessingException e) {
            return "Erro ao cadastrar Pet. " + e.getMessage();
        }
        return "Pet cadastrado com sucesso!";
    }

    public List<Pet> listAll(){
        return petRepository.findAll();
    }

    public Optional<Pet> listById(Long id){
        return petRepository.findById(id);
    }

    public List<Pet> updatePet(Long id, Pet pet){
        petRepository.save(pet);
        return listAll();
    }

    public List<Pet> deleteById(Long id){
        petRepository.deleteById(id);
        return listAll();
    }

    public List<Pet> findByRaca(String raca){
        return petRepository.findByRaca(raca);
    }

    public List<Pet> findByEspecie(Especie especie){
        return petRepository.findByEspecie(especie);
    }
}
