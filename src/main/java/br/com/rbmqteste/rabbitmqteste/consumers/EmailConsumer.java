package br.com.rbmqteste.rabbitmqteste.consumers;

import br.com.rbmqteste.rabbitmqteste.models.dtos.EmailDTO;
import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;
import br.com.rbmqteste.rabbitmqteste.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        EmailEntity email = emailDTO.toEntity();

        emailService.sendEmail(email);

        System.out.println("Email Status: "+ email.getStatusEmail().toString());
    }

}
