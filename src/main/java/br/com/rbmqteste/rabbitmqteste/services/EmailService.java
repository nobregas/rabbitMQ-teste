package br.com.rbmqteste.rabbitmqteste.services;

import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;
import br.com.rbmqteste.rabbitmqteste.models.enums.StatusEmail;
import br.com.rbmqteste.rabbitmqteste.repositories.EmailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    public EmailEntity sendEmail(EmailEntity email) {
        email.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            mailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);

        } finally {
            return emailRepository.save(email);
        }

    }

    public Page<EmailEntity> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }

    public Optional<EmailEntity> findById(Long id) {
        return emailRepository.findById(id);
    }
}
