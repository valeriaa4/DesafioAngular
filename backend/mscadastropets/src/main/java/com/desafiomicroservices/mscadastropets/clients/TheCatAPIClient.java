package com.desafiomicroservices.mscadastropets.clients;

import com.desafiomicroservices.mscadastropets.dtos.CatsBreedsDto;
import com.desafiomicroservices.mscadastropets.dtos.CatsImagesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "the-cat-api", url = "https://api.thecatapi.com/v1")
public interface TheCatAPIClient {

    @GetMapping("/breeds")
    List<CatsBreedsDto> listarRacas();

    @GetMapping("/images/search")
    List<CatsImagesDto> pegarUrl(@RequestParam("breed_ids") String id,
                                 @RequestParam("api_key") String apiKey,
                                 @RequestHeader("Content-Type") String contentType);
}
