package br.com.ace.ticketserviceapi.services.impl;

import br.com.ace.ticketserviceapi.mapper.TicketMapper;
import br.com.ace.ticketserviceapi.repositories.TicketRepository;
import br.com.ace.ticketserviceapi.services.TicketService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateTicketRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;
    private final TicketMapper mapper;


    @Override
    public void save(CreateTicketRequest request) {
        repository.save(mapper.fromRequest(request));
    }
}
