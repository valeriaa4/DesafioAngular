package com.desafiomicroservices.msnotificacao.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CadastroPetsDto {

    @JsonProperty(value = "idPet")
    private Long idPet;
    @JsonProperty(value = "emailTutor")
    private String emailTutor;

}
