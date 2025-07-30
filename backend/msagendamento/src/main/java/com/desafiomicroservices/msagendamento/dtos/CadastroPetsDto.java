package com.desafiomicroservices.msagendamento.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CadastroPetsDto {

    @JsonProperty(value = "id")
    private Long idPet;
    @JsonProperty(value = "nome")
    private String nomePet;
    @JsonProperty(value = "especie")
    private String especie;
    @JsonProperty(value = "raca")
    private String raca;
    @JsonProperty(value = "idade")
    private Integer idade;
    @JsonProperty(value = "tutor")
    private String nomeTutor;
    @JsonProperty(value = "emailTutor")
    private String emailTutor;
    @JsonIgnoreProperties(ignoreUnknown = true, value = "peso")
    private Integer peso;
    @JsonIgnoreProperties(ignoreUnknown = true, value = "descricao")
    private String descricao;
    @JsonIgnoreProperties(ignoreUnknown = true, value = "imagem")
    private String imagem;
    @JsonIgnoreProperties(ignoreUnknown = true, value = "cor")
    private String cor;


}
