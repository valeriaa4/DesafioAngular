package com.desafiomicroservices.msagendamento.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${mq.queues.pet-created}")
    private String queue;
    @Value("${mq.queues.appointment-created}")
    private String queueNotif;

    @Bean
    public Queue queueCadastroPets(){
        return new Queue(queue, true);
    }

    @Bean
    public Queue queueNotificacao(){
        return new Queue(queueNotif, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
