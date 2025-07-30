package com.desafiomicroservices.msagendamento.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "AgendamentosManuais")
@Data
public class AgendamentoManual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPet;
    private String nomePet;
    private String raca;
    private String nomeTutor;
    private String emailTutor;
    private LocalDate data;
    private LocalTime horario;
    private String cuidado;

}
