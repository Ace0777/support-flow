package models.responses;

public record QueueResponse(
        String id,
        String requesterId,
        String title,
        String description,
        String status,
        String createdAt,
        String closedAt
) {
}
