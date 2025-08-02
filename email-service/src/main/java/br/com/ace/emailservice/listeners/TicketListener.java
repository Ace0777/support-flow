package br.com.ace.emailservice.listeners;


import lombok.extern.slf4j.Slf4j;
import models.dtos.TicketCreatedMessage;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TicketListener {

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "supportflow", type = "topic"),
            value = @Queue(value = "queue.tickets"),
            key = "rk.tickets.create"
    ))
    public void listener(final TicketCreatedMessage message){
        log.info("Ticket created successfully.: {}", message);
    }
}
