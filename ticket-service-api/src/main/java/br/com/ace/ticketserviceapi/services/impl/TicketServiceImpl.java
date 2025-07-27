package br.com.ace.ticketserviceapi.services.impl;

import br.com.ace.ticketserviceapi.entities.Ticket;
import br.com.ace.ticketserviceapi.mapper.TicketMapper;
import br.com.ace.ticketserviceapi.repositories.TicketRepository;
import br.com.ace.ticketserviceapi.services.TicketService;
import lombok.RequiredArgsConstructor;
import models.excpetions.ResourceNotFoundException;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static models.enums.TicketStatusEnum.OPEN;
import static models.enums.TicketStatusEnum.RESOLVED;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;
    private final TicketMapper mapper;


    @Override
    public void save(CreateTicketRequest request) {
        repository.save(mapper.fromRequest(request));
    }

    @Override
    public TicketResponse update(UpdateTicketRequest request, Long id) {
        Ticket entity = findById(id);
        entity = mapper.fromRequest(entity, request);

        switch (entity.getStatus()) {
            case RESOLVED -> entity.setClosedAt(now());
            case ASSIGNED -> entity.setClosedAt(null);
        }

        return mapper.fromEntity(repository.save(entity));
    }



      public Ticket findById(final long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Object not found: " + id + ", Type: " + Ticket.class.getName()
                ));
    }
}
