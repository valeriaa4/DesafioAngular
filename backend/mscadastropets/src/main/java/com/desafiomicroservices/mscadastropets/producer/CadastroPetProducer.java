package com.desafiomicroservices.mscadastropets.producer;

import com.desafiomicroservices.mscadastropets.models.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CadastroPetProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.queues.pet-created}")
    private String queue;

    public void petCreated(Pet dadosPetCadastrado) throws JsonProcessingException {
        var json = convertIntoJson(dadosPetCadastrado);
        rabbitTemplate.convertAndSend("", queue, json);
        System.out.println("Evento Publicado: " + json);
    }

    private String convertIntoJson(Pet dadosPetCadastrado) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dadosPetCadastrado);
    }
}