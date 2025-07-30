package com.desafiomicroservices.mscadastropets.services;

import com.desafiomicroservices.mscadastropets.clients.TheDogAPIClient;
import com.desafiomicroservices.mscadastropets.dtos.DogsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.DogsImagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheDogAPIService {

    @Value("${external.api.key-dog}")
    private String apiKey;

    @Autowired
    private TheDogAPIClient theDogAPIClient;


    public List<DogsBreedsDto> listarRacas(){
        return theDogAPIClient.listarRacas();
    }

    public List<DogsImagesDto> pegarUrl(Integer id, String apiKey){
        return theDogAPIClient.pegarUrl(id, apiKey,"application/json");
    }

    public boolean isValidApiKey(String key) {
        String validApiKey = apiKey;
        return validApiKey.equals(key);
    }
}
