package br.com.rbmqteste.rabbitmqteste.services;

import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;

public interface IEmailService {
    EmailEntity sendEmail(EmailEntity email);
}
