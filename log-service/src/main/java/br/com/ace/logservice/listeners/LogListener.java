package br.com.ace.logservice.listeners;

import br.com.ace.logservice.services.impl.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.dtos.LogEvent;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogListener {

    private final LogService service;


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "supportflowlogs", type = "direct"),
            value = @Queue(value = "queue.logs"),
            key = "rk.logs"
    ), containerFactory = "rabbitListenerContainerFactory"

    )
    public void listener(LogEvent message ){
        log.info("Log received: {}", message);
        service.save(message);
    }

}
