package com.desafiomicroservices.msnotificacao.model;

import com.desafiomicroservices.msnotificacao.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notificações")
@Data
public class Notificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idNotif;
    private Long idPet;
    private String emailFrom;
    private String emailTutor;
    private String assunto;
    @Column(columnDefinition = "TEXT")
    private String texto;
    private LocalDateTime dataEnvioEmail;
    private StatusEmail statusEmail;

}
