package com.desafiomicroservices.msagendamento.controller;

import com.desafiomicroservices.msagendamento.dtos.AgendamentoRequestDto;
import com.desafiomicroservices.msagendamento.models.AgendamentoAutomatico;
import com.desafiomicroservices.msagendamento.models.AgendamentoManual;
import com.desafiomicroservices.msagendamento.services.AgendamentoAutomaticoService;
import com.desafiomicroservices.msagendamento.services.AgendamentoManualService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoManualService agendamentoManualService;
    @Autowired
    private AgendamentoAutomaticoService agendamentoAutomaticoService;

    @Operation(summary = "Cadastrar agendamento", description = "Agende um cuidado para o seu pet. Obs.: Insira a data e o horário no seguinte formato: data: yyyy-mm-dd, horario: 10:00 (dentro de aspas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento realizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na realização do agendamento / Requisição incorreta.")
    })
    @PostMapping
    AgendamentoManual createAgendamento(@Valid @RequestBody AgendamentoRequestDto agendamentoRequestDto){
        return agendamentoManualService.createAgendamento(agendamentoRequestDto);
    }

    @Operation(summary = "Listar agendamentos manuais", description = "Acesse a lista de todos os agendamentos manuais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso realizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na obtenção de dados / Requisição incorreta."),
    })
    @GetMapping("/manual")
    List<AgendamentoManual> listAllManual(){
        return agendamentoManualService.listAll();
    }

    @Operation(summary = "Listar agendamento por ID", description = "Acesse um agendamento manual informando seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso realizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na obtenção de dados / Requisição incorreta."),
    })
    @GetMapping("/manual/{id}")
    Optional<AgendamentoManual> listByIdManual(@PathVariable Long id){
        return agendamentoManualService.listByIdManual(id);
    }

    @Operation(summary = "Atualizar agendamento", description = "Atualize informações de um agendamento manual informando seu ID e todos os seus dados. Obs.: Insira a data e o horário no seguinte formato: data: yyyy-mm-dd, horario: 10:00 (dentro de aspas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na atualizacão do agendamento / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.")
    })
    @PutMapping("{id}")
    List<AgendamentoManual> updateAgendamento(@PathVariable Long id, @RequestBody AgendamentoManual agendamentoManual){
        return agendamentoManualService.updateAgendamento(agendamentoManual);
    }

    @Operation(summary = "Cancelar agendamento", description = "Cancele um agendamento informando seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado!"),
            @ApiResponse(responseCode = "400", description = "Falha no cancelamento do agendamento / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.")
    })
    @DeleteMapping("{id}")
    List<AgendamentoManual> deleteById(@PathVariable Long id){
        return agendamentoManualService.deleteById(id);
    }

    // AGENDAMENTO AUTOMÁTICO
    @Operation(summary = "Listar agendamentos automáticos", description = "Acesse a lista de todos os agendamentos automáticos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso realizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na obtenção de dados / Requisição incorreta.")
    })
    @GetMapping("/automatico")
    List<AgendamentoAutomatico> listAllAutomatico(){
        return agendamentoAutomaticoService.listAll();
    }

    @Operation(summary = "Atualizar agendamento automatico", description = "Atualize informações de um agendamento manual informando seu ID e todos os seus dados. Obs.: Insira a data e o horário no seguinte formato: data: yyyy-mm-dd, horario: 10:00 (dentro de aspas).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Falha na atualizacão do agendamento / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.")
    })
    @PutMapping("/automatico")
    List<AgendamentoAutomatico> updateAgendamento(@RequestBody AgendamentoAutomatico agendamentoAutomatico){
        return agendamentoAutomaticoService.updateAgendamento(agendamentoAutomatico);
    }

    @Operation(summary = "Cancelar agendamento automatico", description = "Cancele um agendamento informando seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento cancelado!"),
            @ApiResponse(responseCode = "400", description = "Falha no cancelamento do agendamento / Requisição incorreta."),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.")
    })
    @DeleteMapping("/automatico/{id}")
    List<AgendamentoAutomatico> deleteByIdAutomatico(@PathVariable Long id){
        return agendamentoAutomaticoService.deleteById(id);
    }

}