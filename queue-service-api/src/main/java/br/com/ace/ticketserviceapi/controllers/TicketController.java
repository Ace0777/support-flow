package br.com.ace.ticketserviceapi.controllers;



import models.requests.CreateTicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/tickets")
public interface TicketController {

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateTicketRequest request);
}
