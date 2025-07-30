package com.desafiomicroservices.msnotificacao.controller;

import com.desafiomicroservices.msnotificacao.model.Notificacao;
import com.desafiomicroservices.msnotificacao.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    List<Notificacao> listarNotificacoes() {
        return notificacaoService.listarNotificacoes();
    }

}
