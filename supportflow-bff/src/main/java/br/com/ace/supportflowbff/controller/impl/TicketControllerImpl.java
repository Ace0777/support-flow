package br.com.ace.supportflowbff.controller.impl;




import br.com.ace.supportflowbff.controller.TicketController;
import br.com.ace.supportflowbff.service.TicketService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public ResponseEntity<TicketResponse> update(final Long id, UpdateTicketRequest request) {

        return ResponseEntity.ok().body(service.update(request, id));
    }

    @Override
    public ResponseEntity<TicketResponse> findById(long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(final long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<TicketResponse>> findAll() {
        return ResponseEntity.ok().body(
                service.findAll()
        );
    }

    @Override
    public ResponseEntity<Page<TicketResponse>> findAllPaginated(Integer page, String direction, Integer linesPerPage, String orderBy) {
        return ResponseEntity.ok().body(
                service.findAllPaginated(page, direction, linesPerPage, orderBy)
        );
    }
}
