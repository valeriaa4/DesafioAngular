package com.desafiomicroservices.msagendamento.services;

import com.desafiomicroservices.msagendamento.clients.CadastroPetsClient;
import com.desafiomicroservices.msagendamento.dtos.AgendamentoRequestDto;
import com.desafiomicroservices.msagendamento.dtos.CadastroPetsDto;
import com.desafiomicroservices.msagendamento.models.AgendamentoManual;
import com.desafiomicroservices.msagendamento.producer.NotificacaoProducer;
import com.desafiomicroservices.msagendamento.repositories.AgendamentoManualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoManualService {

    @Autowired
    public AgendamentoManualRepository agendamentoManualRepository;
    @Autowired
    public CadastroPetsClient cadastroPetsClient;
    @Autowired
    private NotificacaoProducer notificacaoProducer;

    public AgendamentoManual createAgendamento(AgendamentoRequestDto agendamentoRequestDto) {
        AgendamentoManual agendamentoManual = new AgendamentoManual();
        agendamentoManual.setIdPet(agendamentoRequestDto.getIdPet());
        agendamentoManual.setData(agendamentoRequestDto.getData());
        agendamentoManual.setHorario(agendamentoRequestDto.getHorario());
        agendamentoManual.setCuidado(agendamentoRequestDto.getCuidado());
        System.out.println("Buscando dados do Pet...");

        Optional<CadastroPetsDto> cadastroPetsDto = cadastroPetsClient.readById(agendamentoManual.getIdPet());
        if (cadastroPetsDto.isEmpty()) {
            throw new RuntimeException("Erro ao buscar dados do ID solicitado.");
        } else {
            System.out.println("Dados do Pet Confirmado: " + cadastroPetsDto);
            String nomePet = cadastroPetsDto.map(CadastroPetsDto::getNomePet).orElseThrow();
            agendamentoManual.setNomePet(nomePet);
            String raca = cadastroPetsDto.map(CadastroPetsDto::getRaca).orElseThrow();
            agendamentoManual.setRaca(raca);
            String nomeTutor = cadastroPetsDto.map(CadastroPetsDto::getNomeTutor).orElseThrow();
            agendamentoManual.setNomeTutor(nomeTutor);
            String emailTutor = cadastroPetsDto.map(CadastroPetsDto::getEmailTutor).orElseThrow();
            agendamentoManual.setEmailTutor(emailTutor);
        }

        System.out.println("Agendamento Criado com Sucesso");
        notificacaoProducer.publishEmailAgendamentoManual(agendamentoManual);

        return agendamentoManualRepository.save(agendamentoManual);
    }

    public List<AgendamentoManual> listAll() {
        return agendamentoManualRepository.findAll();
    }

    public List<AgendamentoManual> updateAgendamento(AgendamentoManual agendamentoManual) {
        agendamentoManualRepository.save(agendamentoManual);
        return listAll();
    }

    public List<AgendamentoManual> deleteById(Long id) {
        agendamentoManualRepository.deleteById(id);
        return listAll();
    }

    public Optional<AgendamentoManual> listByIdManual(Long id) {
        return agendamentoManualRepository.findById(id);
    }
}