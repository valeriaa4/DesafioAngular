package com.desafiomicroservices.msagendamento.clients;

import com.desafiomicroservices.msagendamento.dtos.CadastroPetsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@FeignClient(name = "mscadastropets", path = "/pets")
public interface CadastroPetsClient {

    @GetMapping("{id}")
    Optional<CadastroPetsDto> readById(@PathVariable Long id);

}
