package br.com.ace.ticketserviceapi.services.impl;

import br.com.ace.ticketserviceapi.clients.UserServiceFeignClient;
import br.com.ace.ticketserviceapi.entities.Ticket;
import br.com.ace.ticketserviceapi.mapper.TicketMapper;
import br.com.ace.ticketserviceapi.repositories.TicketRepository;
import br.com.ace.ticketserviceapi.services.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.dtos.TicketCreatedMessage;
import models.excpetions.ResourceNotFoundException;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import models.responses.UserResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void save(CreateTicketRequest request) {
        final var customer = validateUserId(request.customerId());
        final var requester = validateUserId(request.requesterId());
        final var entity = repository.save(mapper.fromRequest(request));

        log.info("Ticket created: {}", entity);

        rabbitTemplate.convertAndSend(
                "supportflow",
                "rk.tickets.create",
                new TicketCreatedMessage(mapper.fromEntity(entity), customer, requester)
                );
    }

    @Override
    public TicketResponse update(UpdateTicketRequest request, Long id) {

        validateUsers(request);

        Ticket entity = findById(id);
        entity = mapper.fromRequest(entity, request);

        switch (entity.getStatus()) {
            case RESOLVED -> entity.setClosedAt(now());
            case ASSIGNED, OPEN -> entity.setClosedAt(null);
        }
        return mapper.fromEntity(repository.save(entity));
    }

    private void validateUsers(UpdateTicketRequest request) {
        if(request.requesterId() != null) validateUserId(request.requesterId());
        if(request.customerId() != null) validateUserId(request.customerId());
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
