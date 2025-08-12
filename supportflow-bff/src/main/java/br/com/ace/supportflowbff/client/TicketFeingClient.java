package br.com.ace.supportflowbff.client;

import jakarta.validation.Valid;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "localhost:8765/ticket-service-api", //todo: Change to the actual service name in production
        path = "/api/tickets"
)
public interface TicketFeingClient {

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateTicketRequest request);

    @PutMapping("/{id}")
    ResponseEntity<TicketResponse> update(@PathVariable(name = "id") final Long id,
                                          @Valid @RequestBody UpdateTicketRequest request);

    @GetMapping("/{id}")
    ResponseEntity <TicketResponse> findById(
            @PathVariable(name = "id") final long id);

    @DeleteMapping("/{id}")
    ResponseEntity <Void> deleteById(
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
