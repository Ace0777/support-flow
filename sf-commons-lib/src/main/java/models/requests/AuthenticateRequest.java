package models.requests;

import java.io.Serial;
import java.io.Serializable;

public record AuthenticateRequest(
        String email,
        String password

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
