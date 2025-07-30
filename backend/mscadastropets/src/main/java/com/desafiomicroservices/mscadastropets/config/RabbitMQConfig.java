package com.desafiomicroservices.mscadastropets.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${mq.queues.pet-created}")
    private String queue;

    @Bean
    public Queue queueCadastroPets(){
        return new Queue(queue, true);
    }

}