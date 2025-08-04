package br.com.ace.logservice.mapper;

import br.com.ace.logservice.entities.Log;
import models.dtos.LogEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy =  IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface LogMapper {

    
    Log toEntity(LogEvent logEvent);



}
