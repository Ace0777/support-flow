package models.requests;

import jakarta.validation.constraints.Size;

public record UpdateTicketRequest(
        @Size(min = 24, max = 36, message = "The customerId must be between 24 and 36 characters")
        String requesterId,

        @Size(min = 24, max = 36, message = "The customerId must be between 24 and 36 characters")
        String customerId,

        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String title,

        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description,

        @Size(min = 3, max = 20, message = "Status must be between 3 and 20 characters")
        String status
) {
}
