package com.desafiomicroservices.msagendamento.producer;

import com.desafiomicroservices.msagendamento.dtos.EmailDto;
import com.desafiomicroservices.msagendamento.models.AgendamentoAutomatico;
import com.desafiomicroservices.msagendamento.models.AgendamentoManual;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.queues.appointment-created}")
    private String queueNotif;

    public void publishEmailAgendamentoAutomatico(AgendamentoAutomatico agendamentoAutomatico){
        var emailDto = new EmailDto();
        emailDto.setIdPet(agendamentoAutomatico.getIdPet());
        emailDto.setEmailTutor(agendamentoAutomatico.getEmailTutor());
        emailDto.setAssunto("Pet Cadastrado com Sucesso! ✨");
        emailDto.setTexto("Seja muito bem-vindo! Estamos felizes que você chegou!\n"+
                "\n"+
                "Seu Pet ganhou um bônus: Primeiro Banho Grátis! Válido até 30 dias, contando a partir da data de hoje.\n"+
                "\n"+
                "Além disso, também agendamos o primeiro cuidado para o seu Pet: \n"+
                agendamentoAutomatico.getCuidado()+" | "+agendamentoAutomatico.getData()+
                "\n"+
                "\n"+
                "Até mais! Para mais informações acesse nosso site: DevPet.com");
        rabbitTemplate.convertAndSend("", queueNotif, emailDto);
        System.out.println("Notificação enviada para a fila.");
    }

    public void publishEmailAgendamentoManual(AgendamentoManual agendamentoManual){
        var emailDto = new EmailDto();
        emailDto.setIdPet(agendamentoManual.getIdPet());
        emailDto.setEmailTutor(agendamentoManual.getEmailTutor());
        emailDto.setAssunto("Confirmação de Agendamento ⌚");
        emailDto.setTexto("Olá, seu agendamento foi realizado com sucesso!"+
                "\n"+
                "\n"+
                "Dados do Agendamento:"+
                "\n"+
                "Tutor: "+agendamentoManual.getNomeTutor()+
                "\n"+
                "Pet: "+agendamentoManual.getNomePet()+
                "\n"+
                "Cuidado: "+agendamentoManual.getCuidado()+
                "\n"+
                "Data: "+agendamentoManual.getData()+
                "\n"+
                "Horário: "+agendamentoManual.getHorario()+
                "\n"+
                "\n"+
                "DevPet.com");
        rabbitTemplate.convertAndSend("", queueNotif, emailDto);
        System.out.println("Notificação enviada para a fila.");
    }
}
