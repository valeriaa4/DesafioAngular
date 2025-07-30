package com.desafiomicroservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route("mscadastropets", r -> r.path("/pets/**").uri("lb://mscadastropets"))
				.route("msagendamento", r -> r.path("/agendamento/**").uri("lb://msagendamento"))
				.route("msnotificacao", r -> r.path("/notificacao/**").uri("lb://msnotificacao"))
				.build();
	}

}
