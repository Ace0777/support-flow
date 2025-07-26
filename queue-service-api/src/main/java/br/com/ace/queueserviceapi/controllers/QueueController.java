package br.com.ace.queueserviceapi.controllers;


import models.requests.CreateQueueRequest;
import models.requests.CreateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/queues")
public interface QueueController {

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateQueueRequest createQueueRequest);
}
