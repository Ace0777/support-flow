package models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum QueueStatusEnum {


    ACTIVE("active"),
    PAUSED("paused"),
    CANCELED("canceled"),
    COMPLETED("completed");

    @Getter
    private final String description;

    public static QueueStatusEnum toEnum(final String description) {
        return Arrays.stream(QueueStatusEnum.values())
                .filter(queueStatusEnum -> queueStatusEnum.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Queue status not found: " + description));
    }

}
