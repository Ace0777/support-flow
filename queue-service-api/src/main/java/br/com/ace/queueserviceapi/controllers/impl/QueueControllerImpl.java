package br.com.ace.queueserviceapi.controllers.impl;


import br.com.ace.queueserviceapi.controllers.QueueController;
import br.com.ace.queueserviceapi.services.QueueService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateQueueRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class QueueControllerImpl implements QueueController {

    private final QueueService service;

    @Override
    public ResponseEntity<Void> save(CreateQueueRequest request) {
        service.save(request);
        return ResponseEntity.status(CREATED).build();
    }
}
