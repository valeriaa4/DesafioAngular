package com.desafiomicroservices.msagendamento.repositories;

import com.desafiomicroservices.msagendamento.models.AgendamentoManual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoManualRepository extends JpaRepository<AgendamentoManual, Long> {
}
