package models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum TicketStatusEnum {


    OPEN("open"),
    ASSIGNED("assigned"),
    RESOLVED("resolved"),
    CANCELED("canceled");

    @Getter
    private final String description;

    public static TicketStatusEnum toEnum(final String description) {
        return Arrays.stream(TicketStatusEnum.values())
                .filter(ticketStatusEnum -> ticketStatusEnum.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Queue status not found: " + description));
    }

}
