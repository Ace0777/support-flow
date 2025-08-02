package models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.requests.CreateTicketRequest;
import models.responses.TicketResponse;
import models.responses.UserResponse;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreatedMessage implements Serializable {

    @Serial
    private static final Long serialVersionUID = 1L;

    private TicketResponse ticket;
    private UserResponse customer;
    private UserResponse requester;


}
