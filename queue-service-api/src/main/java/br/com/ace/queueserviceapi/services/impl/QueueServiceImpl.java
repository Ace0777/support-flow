package br.com.ace.queueserviceapi.services.impl;

import br.com.ace.queueserviceapi.mapper.QueueMapper;
import br.com.ace.queueserviceapi.repositories.QueueRepository;
import br.com.ace.queueserviceapi.services.QueueService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateQueueRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final QueueRepository repository;
    private final QueueMapper mapper;


    @Override
    public void save(CreateQueueRequest request) {
        repository.save(mapper.fromRequest(request));
    }
}
