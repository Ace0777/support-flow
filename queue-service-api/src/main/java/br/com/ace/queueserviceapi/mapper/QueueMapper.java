package br.com.ace.queueserviceapi.mapper;

import br.com.ace.queueserviceapi.entities.Queue;
import models.enums.QueueStatusEnum;
import models.requests.CreateQueueRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
    componentModel = "spring",
        nullValuePropertyMappingStrategy =  IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface QueueMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus") //target de status é o que é passado no request e faço isso usando o mapStatus
    Queue fromRequest(CreateQueueRequest request);

    @Named("mapStatus")
    default QueueStatusEnum mapStatus( final String status){
        return QueueStatusEnum.toEnum(status);
    }
}
