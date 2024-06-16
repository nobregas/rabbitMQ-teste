package br.com.rbmqteste.rabbitmqteste.repositories;

import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository
    extends JpaRepository<EmailEntity, Long> {
}
