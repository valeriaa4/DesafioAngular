package com.desafiomicroservices.mscadastropets.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DogsBreedsDto {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "name")
    private String nome;
    @JsonProperty(value = "reference_image_id")
    private String idImagem;

}
