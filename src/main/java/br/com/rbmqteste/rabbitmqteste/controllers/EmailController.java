package br.com.rbmqteste.rabbitmqteste.controllers;

import br.com.rbmqteste.rabbitmqteste.models.dtos.EmailDTO;
import br.com.rbmqteste.rabbitmqteste.models.dtos.Message;
import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;
import br.com.rbmqteste.rabbitmqteste.producers.EmailProducer;
import br.com.rbmqteste.rabbitmqteste.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;
    private final EmailProducer emailProducer;

    public EmailController(EmailService emailService, EmailProducer emailProducer) {
        this.emailService = emailService;
        this.emailProducer = emailProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        Message sentEmail =  emailProducer.send(emailDTO);

        return new ResponseEntity<>(sentEmail, HttpStatus.CREATED);
    }

    @GetMapping("/box")
    public ResponseEntity<Page<EmailEntity>> getAllEmails(
            @PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC)
            Pageable pageable)
    {
        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") Long emailId){
        Optional<EmailEntity> emailModelOptional = emailService.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }

}
