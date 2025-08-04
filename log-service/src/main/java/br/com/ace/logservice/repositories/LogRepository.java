package br.com.ace.logservice.repositories;

import br.com.ace.logservice.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
