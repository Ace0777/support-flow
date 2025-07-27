package br.com.ace.ticketserviceapi.services;

import models.requests.CreateTicketRequest;

public interface TicketService {

    void save(CreateTicketRequest request);

}
