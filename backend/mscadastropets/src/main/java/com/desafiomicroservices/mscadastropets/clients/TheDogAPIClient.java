package com.desafiomicroservices.mscadastropets.clients;

import com.desafiomicroservices.mscadastropets.dtos.DogsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.DogsImagesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "the-dog-api", url = "https://api.thedogapi.com/v1")
public interface TheDogAPIClient {

    @GetMapping("/breeds")
    List<DogsBreedsDto> listarRacas();

    // obter imagem pelo ID da raça
    @GetMapping("/images/search")
    List<DogsImagesDto> pegarUrl(@RequestParam("breed_ids") Integer id,
                                 @RequestParam("api_key") String apiKey,
                                 @RequestHeader("Content-Type") String contentType);

}
