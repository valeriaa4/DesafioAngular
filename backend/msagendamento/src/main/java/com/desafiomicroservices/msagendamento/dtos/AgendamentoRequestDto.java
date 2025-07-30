package com.desafiomicroservices.msagendamento.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AgendamentoRequestDto {

    @NotNull
    private Long idPet;
    @NotNull
    private LocalDate data;
    @NotNull
    private LocalTime horario;
    @NotBlank
    private String cuidado;

}
