package com.desafiomicroservices.msnotificacao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MsnotificacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsnotificacaoApplication.class, args);
	}

}
