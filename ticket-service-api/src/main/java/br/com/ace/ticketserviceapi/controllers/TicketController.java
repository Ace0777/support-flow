package br.com.ace.ticketserviceapi.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import models.requests.UpdateTicketRequest;
import models.requests.CreateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/tickets")
public interface TicketController {

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateTicketRequest request);

    @PutMapping("/{id}")
    ResponseEntity<TicketResponse> update(@PathVariable(name = "id") final Long id,
    @Parameter(description = "Update ticket request", required = true) @Valid @RequestBody UpdateTicketRequest request);



}
