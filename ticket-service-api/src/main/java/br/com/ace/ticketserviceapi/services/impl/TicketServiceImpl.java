package br.com.ace.ticketserviceapi.services.impl;

import br.com.ace.ticketserviceapi.clients.UserServiceFeignClient;
import br.com.ace.ticketserviceapi.entities.Ticket;
import br.com.ace.ticketserviceapi.mapper.TicketMapper;
import br.com.ace.ticketserviceapi.repositories.TicketRepository;
import br.com.ace.ticketserviceapi.services.TicketService;
import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.excpetions.ResourceNotFoundException;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import models.responses.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;


@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;
    private final TicketMapper mapper;
    private final UserServiceFeignClient userServiceFeignClient;




    @Override
    public void save(CreateTicketRequest request) {
        final var customer = validateUserId(request.customerId());
        final var requester = validateUserId(request.requesterId());

        log.info("Customer: {}", customer);
        log.info("Requester: {}", requester);


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

    @Override
    public void deleteById(final Long id) {
        repository.delete(findById(id));
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Ticket> findAllPaginated(Integer page, String direction, Integer linesPerPage, String orderBy) {
        PageRequest pageRequest = PageRequest.of(
                page,
                linesPerPage,
                Sort.Direction.valueOf(direction),
                orderBy
        );
        return repository.findAll(pageRequest);
    }


    public Ticket findById(final long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Object not found: " + id + ", Type: " + Ticket.class.getName()
                ));
    }


    UserResponse validateUserId(final String userId){
        return userServiceFeignClient.findById(userId).getBody();
    }
}
