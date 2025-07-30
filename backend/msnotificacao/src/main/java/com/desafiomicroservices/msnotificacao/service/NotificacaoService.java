package com.desafiomicroservices.msnotificacao.service;

import com.desafiomicroservices.msnotificacao.enums.StatusEmail;
import com.desafiomicroservices.msnotificacao.model.Notificacao;
import com.desafiomicroservices.msnotificacao.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    public Notificacao sendEmailNotificacao(Notificacao notificacao){
        try{
            notificacao.setDataEnvioEmail(LocalDateTime.now());
            notificacao.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notificacao.getEmailTutor());
            message.setSubject(notificacao.getAssunto());
            message.setText(notificacao.getTexto());
            javaMailSender.send(message);

            notificacao.setStatusEmail(StatusEmail.ENVIADO);
        } catch (MailException e){
            notificacao.setStatusEmail(StatusEmail.ERRO);
        } finally {
            return notificacaoRepository.save(notificacao);
        }
    }

    public List<Notificacao> listarNotificacoes(){
        return notificacaoRepository.findAll();
    }

}