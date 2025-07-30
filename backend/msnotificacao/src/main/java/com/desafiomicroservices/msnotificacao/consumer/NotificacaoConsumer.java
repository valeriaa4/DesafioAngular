package com.desafiomicroservices.msnotificacao.consumer;

import com.desafiomicroservices.msnotificacao.dtos.EmailDto;
import com.desafiomicroservices.msnotificacao.model.Notificacao;
import com.desafiomicroservices.msnotificacao.service.NotificacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {

    @Autowired
    private NotificacaoService notificacaoService;

    @RabbitListener(queues = "${mq.queues.appointment-created}")
    public void receberNotificacao(@Payload EmailDto emailDto){
        var notificacao = new Notificacao();
        BeanUtils.copyProperties(emailDto, notificacao);
        notificacaoService.sendEmailNotificacao(notificacao);
        System.out.println("Notificação Recebida.");
    }
}
