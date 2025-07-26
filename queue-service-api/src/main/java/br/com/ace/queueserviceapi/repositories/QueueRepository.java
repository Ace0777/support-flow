package br.com.ace.queueserviceapi.repositories;

import br.com.ace.queueserviceapi.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {



}
