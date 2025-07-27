package models.enums;


import java.util.Arrays;

public enum ProfileEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_OPERATOR("ROLE_OPERATOR"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_CLIENT("ROLE_CLIENT");
    ;

    private final String description;

    ProfileEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ProfileEnum fromString(final String description) {
            return Arrays.stream(ProfileEnum .values())
                    .filter(p -> p.getDescription().equalsIgnoreCase(description))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Profile not found: " + description));

    }
}

