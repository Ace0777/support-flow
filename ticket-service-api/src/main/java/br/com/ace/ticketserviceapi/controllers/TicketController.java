package br.com.ace.ticketserviceapi.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import models.requests.UpdateTicketRequest;
import models.requests.CreateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/tickets")
public interface TicketController {

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateTicketRequest request);

    @PutMapping("/{id}")
    ResponseEntity<TicketResponse> update(@PathVariable(name = "id") final Long id,
    @Parameter(description = "Update ticket request", required = true) @Valid @RequestBody UpdateTicketRequest request);

    @GetMapping("/{id}")
    ResponseEntity <TicketResponse> findById(
            @NotNull(message = "Ticket ID cannot be null")
            @Parameter(description = "Ticket ID", required = true)
            @PathVariable(name = "id") final long id);

    @DeleteMapping("/{id}")
    ResponseEntity <Void> deleteById(
            @NotNull(message = "Ticket ID cannot be null")
            @Parameter(description = "Ticket ID", required = true)
            @PathVariable(name = "id") final long id);

    @GetMapping
    ResponseEntity <List<TicketResponse>> findAll();

    @GetMapping("/page")
    ResponseEntity<Page<TicketResponse>> findAllPaginated(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,

            @RequestParam(value = "direction", defaultValue = "ASC", required = true) final String direction,

            @RequestParam(value = "linesPerPage", defaultValue = "12") final Integer linesPerPage,

            @RequestParam(value = "orderBy", defaultValue = "id") final String orderBy
    );

}
