package com.desafiomicroservices.mscadastropets.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DogsImagesDto {

    @JsonProperty(value = "breeds")
    private Object breeds;
    @JsonProperty(value = "url")
    private String url;

}
