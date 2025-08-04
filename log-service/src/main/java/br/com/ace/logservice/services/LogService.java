package br.com.ace.logservice.services;

import br.com.ace.logservice.entities.Log;
import models.dtos.LogEvent;
import models.requests.CreateTicketRequest;

public interface LogService {

    void save(LogEvent log);

}
