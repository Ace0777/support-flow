package br.com.ace.ticketserviceapi.services;

import br.com.ace.ticketserviceapi.entities.Ticket;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;

public interface TicketService {

    Ticket findById(final long id);

    void save(CreateTicketRequest request);

    TicketResponse update(UpdateTicketRequest request, Long id);

    void deleteById(final Long id);


}
