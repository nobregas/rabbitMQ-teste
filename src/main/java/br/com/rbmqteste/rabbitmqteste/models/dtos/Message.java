package br.com.rbmqteste.rabbitmqteste.models.dtos;

import java.time.LocalDateTime;

public record Message(
        String message,
        LocalDateTime timestamp
) {
}
