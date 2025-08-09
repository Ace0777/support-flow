package br.com.ace.supportflowbff.service;

import br.com.ace.supportflowbff.client.TicketFeingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final TicketFeingClient ticketFeingClient;

    public void save(CreateTicketRequest request) {
        ticketFeingClient.save(request);
    }

    public TicketResponse update(UpdateTicketRequest request, Long id) {
       return ticketFeingClient.update(id, request).getBody();
    }

    public void deleteById(final Long id) {
        ticketFeingClient.deleteById(id);
    }

    public List<TicketResponse> findAll() {
        return ticketFeingClient.findAll().getBody();
    }

    public Page<TicketResponse> findAllPaginated(Integer page, String direction, Integer linesPerPage, String orderBy) {
        return ticketFeingClient.findAllPaginated(page, direction, linesPerPage, orderBy).getBody();
    }

    public TicketResponse findById(final long id){
        return ticketFeingClient.findById(id).getBody();
    }

}
