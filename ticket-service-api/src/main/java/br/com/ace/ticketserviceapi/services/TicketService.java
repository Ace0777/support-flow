package br.com.ace.ticketserviceapi.services;

import br.com.ace.ticketserviceapi.entities.Ticket;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService {

    Ticket findById(final long id);

    void save(CreateTicketRequest request);

    TicketResponse update(UpdateTicketRequest request, Long id);

    void deleteById(final Long id);

    List<Ticket> findAll();


    Page<Ticket> findAllPaginated(Integer page, String direction, Integer linesPerPage, String orderBy);

}
