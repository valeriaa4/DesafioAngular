package com.desafiomicroservices.msagendamento.services;

import com.desafiomicroservices.msagendamento.models.AgendamentoAutomatico;
import com.desafiomicroservices.msagendamento.models.AgendamentoManual;
import com.desafiomicroservices.msagendamento.repositories.AgendamentoAutomaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoAutomaticoService {

    @Autowired
    private AgendamentoAutomaticoRepository agendamentoAutomaticoRepository;

    public List<AgendamentoAutomatico> listAll(){
        return agendamentoAutomaticoRepository.findAll();
    }

    public List<AgendamentoAutomatico> updateAgendamento(AgendamentoAutomatico agendamentoAutomatico) {
        agendamentoAutomaticoRepository.save(agendamentoAutomatico);
        return listAll();
    }

    public List<AgendamentoAutomatico> deleteById(Long id) {
        agendamentoAutomaticoRepository.deleteById(id);
        return listAll();
    }
}
