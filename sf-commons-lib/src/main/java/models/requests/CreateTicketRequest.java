package models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTicketRequest(

        @Size(min = 24, max = 36, message = "The customerId must be between 24 and 36 characters")
        @NotBlank(message = "Requester ID cannot be empty")
        String requesterId,

        @Size(min = 24, max = 36, message = "The customerId must be between 24 and 36 characters")
        @NotBlank(message = "The customerId cannot be null or blank")
        String customerId,

        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String title,

        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description,

        @NotBlank(message = "Status cannot be empty")
        String status


) {
}
