package br.com.ace.logservice.services.impl;

import br.com.ace.logservice.entities.Log;
import br.com.ace.logservice.mapper.LogMapper;
import br.com.ace.logservice.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.dtos.LogEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService implements br.com.ace.logservice.services.LogService {

    private final LogMapper mapper;
    private final LogRepository repository;

    @Override
    public void save(LogEvent logEvent) {
        repository.save(mapper.toEntity(logEvent));
    }
}
