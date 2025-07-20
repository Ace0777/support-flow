package br.com.ace.userserviceapi.service;

import br.com.ace.userserviceapi.mapper.UserMapper;
import br.com.ace.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.excpetions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.mapstruct.Mapping;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(
                userRepository.findById(id)
                                .orElseThrow(() ->new ResourceNotFoundException(
                                        "Object not found: " + id + ", Type: " + UserResponse.class.getName()
                                ))
        );
    }

    @Mapping(target = "id", ignore = true)
    public void save(CreateUserRequest createUserRequest) {
        verifyEmailAlreadyExists(createUserRequest.email(), null);
        userRepository.save(userMapper.fromRequest(createUserRequest));
    }

    private void verifyEmailAlreadyExists(final String email, final String id) {
      userRepository.findByEmail(email)
              .filter(user -> !user.getId().equals(id))  //se ele indentificar um usuario com o mesmo email, mas com id diferente, lança a exceção
              .ifPresent(user -> {
                  throw new DataIntegrityViolationException("Email already exists: " + email);
              });

    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::fromEntity)
                .toList();
    }
}
