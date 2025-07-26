package models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateQueueRequest(

        @NotBlank(message = "Requester ID cannot be empty")
        String requesterId,
        @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
        String title,
        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description,
        @NotBlank(message = "Status cannot be empty")
        String status


) {
}
