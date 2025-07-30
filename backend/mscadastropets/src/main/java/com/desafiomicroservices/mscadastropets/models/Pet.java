package com.desafiomicroservices.mscadastropets.models;

import com.desafiomicroservices.mscadastropets.enums.Especie;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Pets")
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tutor;
    private String emailTutor;
    private String nome;
    private Especie especie;
    private String raca;
    private Integer idade;
    private Integer peso;
    private String cor;
    private String descricao;
    private String imagem;

}