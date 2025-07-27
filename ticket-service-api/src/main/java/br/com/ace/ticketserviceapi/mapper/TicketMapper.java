package br.com.ace.ticketserviceapi.mapper;

import br.com.ace.ticketserviceapi.entities.Ticket;
import models.enums.TicketStatusEnum;
import models.requests.CreateTicketRequest;
import models.requests.UpdateTicketRequest;
import models.responses.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
    componentModel = "spring",
        nullValuePropertyMappingStrategy =  IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus") //target de status é o que é passado no request e faço isso usando o mapStatus
    @Mapping(target = "createdAt", expression = "java(mapCreatedAt())")
    Ticket fromRequest(CreateTicketRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "createdAt", ignore = true)
    Ticket fromRequest(@MappingTarget Ticket ticket, UpdateTicketRequest request);

    TicketResponse fromEntity(Ticket entity);

    @Named("mapStatus")
    default TicketStatusEnum mapStatus(final String status){
        return TicketStatusEnum.toEnum(status);
    }

    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt() {
        return LocalDateTime.now();
    }

    List<TicketResponse> fromEntities(List<Ticket> tickets);
}
