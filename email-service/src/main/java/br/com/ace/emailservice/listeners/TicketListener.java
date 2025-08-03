package br.com.ace.emailservice.listeners;


import br.com.ace.emailservice.models.enums.OperationEnum;
import br.com.ace.emailservice.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.dtos.TicketCreatedMessage;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.ace.emailservice.models.enums.OperationEnum.TICKET_CREATED;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketListener {

    private final EmailService emailService;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "supportflow", type = "topic"),
            value = @Queue(value = "queue.tickets"),
            key = "rk.tickets.create"
    ))
    public void listener(final TicketCreatedMessage message) throws MessagingException {
        log.info("Ticket received: {}", message);
        log.info("Sending email to customer: {}", message.getCustomer().email());
        emailService.sendHtmlMail(message, TICKET_CREATED);
    }
}
