package com.desafiomicroservices.msagendamento.repositories;

import com.desafiomicroservices.msagendamento.models.AgendamentoAutomatico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoAutomaticoRepository extends JpaRepository<AgendamentoAutomatico, Long> {
}
