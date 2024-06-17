package br.com.rbmqteste.rabbitmqteste.producers;

import br.com.rbmqteste.rabbitmqteste.models.dtos.EmailDTO;
import br.com.rbmqteste.rabbitmqteste.models.dtos.Message;
import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailProducer {

    @Value("$spring.rabbitmq.template.exchange")
    private String exchange;

    @Value("$spring.rabbitmq.template.routing-key")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Message send(EmailDTO email) {
        EmailEntity mail = email.toEntity();

        rabbitTemplate.convertAndSend(exchange, routingKey, mail);

        return new Message(
                "Email enviado com sucesso",
                LocalDateTime.now()
        );
    }
}
