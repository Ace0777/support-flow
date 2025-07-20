package models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import models.enums.ProfileEnum;

import java.util.Set;


public record CreateUserRequest(

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name,

    @NotBlank(message = "Email cannot be empty")
    @Size(min = 7, max = 50, message = "Email must be between 7 and 50 characters")
    @Email(message = "Email should be valid")
    String email,

    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    @NotBlank(message = "Password cannot be empty")
    String password,


    Set<ProfileEnum> profiles
) { }
