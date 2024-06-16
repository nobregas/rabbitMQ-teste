package br.com.rbmqteste.rabbitmqteste.models.dtos;

import br.com.rbmqteste.rabbitmqteste.models.entities.EmailEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO {

    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    //Retorna a entidade Email
    public EmailEntity toEntity() {
        EmailEntity email = new EmailEntity();

        email.setOwnerRef(ownerRef);
        email.setEmailFrom(emailFrom);
        email.setEmailTo(emailTo);
        email.setSubject(subject);
        email.setText(text);

        return email;
    }
}