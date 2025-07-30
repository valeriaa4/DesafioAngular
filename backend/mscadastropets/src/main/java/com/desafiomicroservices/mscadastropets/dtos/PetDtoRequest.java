package com.desafiomicroservices.mscadastropets.dtos;

import com.desafiomicroservices.mscadastropets.enums.Especie;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PetDtoRequest { // classe de Dto para fazer a requisição "personalizada", apenas com os dados necessários

    @NotBlank
    private String tutor;
    @NotBlank
    @Email
    private String emailTutor;
    @NotBlank
    private String nome;
    @NotNull
    private Especie especie;
    @NotBlank
    private String raca;
    @NotNull
    private Integer idade;
    @NotNull
    private Integer peso;
    @NotBlank
    private String cor;
    @NotBlank
    private String descricao;

}
