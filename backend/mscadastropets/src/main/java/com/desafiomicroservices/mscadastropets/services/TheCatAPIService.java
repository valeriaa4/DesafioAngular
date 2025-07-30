package com.desafiomicroservices.mscadastropets.services;

import com.desafiomicroservices.mscadastropets.clients.TheCatAPIClient;
import com.desafiomicroservices.mscadastropets.dtos.CatsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.CatsImagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheCatAPIService {

    @Value("${external.api.key-cat}")
    private String apiKey;

    @Autowired
    private TheCatAPIClient theCatAPIClient;


    public List<CatsBreedsDto> listarRacas(){
        return theCatAPIClient.listarRacas();
    }

    public List<CatsImagesDto> pegarUrl(String id, String apiKey){
        return theCatAPIClient.pegarUrl(id, apiKey,"application/json");
    }

    public boolean isValidApiKey(String key) {
        String validApiKey = apiKey;
        return validApiKey.equals(key);
    }
}
