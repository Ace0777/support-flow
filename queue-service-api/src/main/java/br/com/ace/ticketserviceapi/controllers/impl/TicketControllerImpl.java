package br.com.ace.ticketserviceapi.controllers.impl;


import br.com.ace.ticketserviceapi.controllers.TicketController;
import br.com.ace.ticketserviceapi.services.TicketService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateTicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class TicketControllerImpl implements TicketController {

    private final TicketService service;

    @Override
    public ResponseEntity<Void> save(CreateTicketRequest request) {
        service.save(request);
        return ResponseEntity.status(CREATED).build();
    }
}
