package br.com.ace.userserviceapi.service;

import br.com.ace.userserviceapi.entity.User;
import br.com.ace.userserviceapi.mapper.UserMapper;
import br.com.ace.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(
                userRepository.findById(id).orElse(null)
        );
    }

    @Mapping(target = "id", ignore = true)
    public void save(CreateUserRequest createUserRequest) {
        userRepository.save(userMapper.fromRequest(createUserRequest));
    }
}
