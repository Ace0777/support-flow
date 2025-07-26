package br.com.ace.queueserviceapi.services;

import models.requests.CreateQueueRequest;

public interface QueueService {

    void save(CreateQueueRequest request);

}
