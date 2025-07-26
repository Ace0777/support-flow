package br.com.ace.queueserviceapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.QueueStatusEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static models.enums.QueueStatusEnum.ACTIVE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_queue")
public class Queue implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String requesterId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QueueStatusEnum status = ACTIVE;

    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
}
