package br.com.ace.ticketserviceapi.repositories;

import br.com.ace.ticketserviceapi.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {



}
