package br.com.ace.ticketserviceapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.TicketStatusEnum;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import static models.enums.TicketStatusEnum.OPEN;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_ticket")
public class Ticket implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String requesterId;

    @Column(nullable = false, length = 45)
    private String customerId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status = OPEN;

    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
}
