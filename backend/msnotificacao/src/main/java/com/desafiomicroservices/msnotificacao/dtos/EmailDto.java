package com.desafiomicroservices.msnotificacao.dtos;

import lombok.Data;

@Data
public class EmailDto {

    private Long idPet;
    private String emailTutor;
    private String assunto;
    private String texto;

}