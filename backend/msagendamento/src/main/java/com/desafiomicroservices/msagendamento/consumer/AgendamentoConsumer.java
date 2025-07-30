package com.desafiomicroservices.msagendamento.consumer;

import com.desafiomicroservices.msagendamento.dtos.CadastroPetsDto;
import com.desafiomicroservices.msagendamento.models.AgendamentoAutomatico;
import com.desafiomicroservices.msagendamento.producer.NotificacaoProducer;
import com.desafiomicroservices.msagendamento.repositories.AgendamentoAutomaticoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Component
public class AgendamentoConsumer {

    @Autowired
    private AgendamentoAutomaticoRepository agendamentoAutomaticoRepository;
    @Autowired
    private NotificacaoProducer notificacaoProducer;

    @RabbitListener(queues = "${mq.queues.pet-created}")
    public void receberPetCreated(@Payload String payload){
        System.out.println("Evento Recebido: Pet Created - "+ payload);
        try{
            var mapper = new ObjectMapper();
            CadastroPetsDto cadastroPetsDto = mapper.readValue(payload, CadastroPetsDto.class);
            AgendamentoAutomatico agendamentoAutomatico = new AgendamentoAutomatico();
            agendamentoAutomatico.setIdPet(cadastroPetsDto.getIdPet());
            agendamentoAutomatico.setNomePet(cadastroPetsDto.getNomePet());
            agendamentoAutomatico.setRaca(cadastroPetsDto.getRaca());
            agendamentoAutomatico.setEspecie(cadastroPetsDto.getEspecie());
            agendamentoAutomatico.setIdade(cadastroPetsDto.getIdade());
            agendamentoAutomatico.setNomeTutor(cadastroPetsDto.getNomeTutor());
            agendamentoAutomatico.setEmailTutor(cadastroPetsDto.getEmailTutor());

            // primeiro banho grátis - bônus para todos os pets que se cadastrarem
            agendamentoAutomatico.setBonus("Primeiro Banho Grátis");
            agendamentoAutomatico.setValidadeBonus("Válido até 30 dias após cadastro.");

            // regra de negócio agendamentos automáticos - vacina inicial ou check-up inicial
            if(agendamentoAutomatico.getIdade() < 7){
                agendamentoAutomatico.setCuidado("Vacinação Inicial");
                agendamentoAutomatico.setData("Agendada para daqui 15 dias úteis.");
                agendamentoAutomaticoRepository.save(agendamentoAutomatico);
                System.out.println("Agendamento Automático Criado");
                notificacaoProducer.publishEmailAgendamentoAutomatico(agendamentoAutomatico);
            } else{
                agendamentoAutomatico.setCuidado("Check-Up Inicial");
                agendamentoAutomatico.setData("Agendado para daqui 20 dias úteis.");
                agendamentoAutomaticoRepository.save(agendamentoAutomatico);
                System.out.println("Agendamento Automático Criado");
                notificacaoProducer.publishEmailAgendamentoAutomatico(agendamentoAutomatico);
            }
        } catch (Exception e){
            log.error("Erro ao criar agendamento automático: {} ", e.getMessage());
        }
    }
}