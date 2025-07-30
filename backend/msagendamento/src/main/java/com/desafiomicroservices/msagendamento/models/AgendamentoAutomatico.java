package com.desafiomicroservices.msagendamento.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "AgendamentosAutomáticos")
@Data
public class AgendamentoAutomatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPet;
    private String nomePet;
    private String raca;
    private String especie;
    private Integer idade;
    private String nomeTutor;
    private String emailTutor;
    private String bonus;
    private String validadeBonus;
    private String cuidado;
    private String data;

}
