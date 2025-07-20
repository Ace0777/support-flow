package br.com.ace.userserviceapi.mapper;


import br.com.ace.userserviceapi.entity.User;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
    componentModel = "spring",
        nullValuePropertyMappingStrategy =  IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    UserResponse fromEntity(final User entity);

    User fromRequest(CreateUserRequest createUserRequest);

}
