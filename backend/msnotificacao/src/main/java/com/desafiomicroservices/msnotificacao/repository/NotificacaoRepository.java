package com.desafiomicroservices.msnotificacao.repository;

import com.desafiomicroservices.msnotificacao.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {
}
