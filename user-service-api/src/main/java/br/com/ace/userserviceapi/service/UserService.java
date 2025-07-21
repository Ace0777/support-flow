package br.com.ace.userserviceapi.service;

import br.com.ace.userserviceapi.entity.User;
import br.com.ace.userserviceapi.mapper.UserMapper;
import br.com.ace.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.excpetions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.mapstruct.Mapping;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;
    private final UserRepository repository;

    public UserResponse findById(final String id) {
        return mapper.fromEntity(find(id));
    }

    public void save(CreateUserRequest request) {
        verifyEmailAlreadyExists(request.email(), null);
        repository.save(
                mapper.fromRequest(request)
                        .withPassword(encoder.encode(request.password()))
        );
    }

    private void verifyEmailAlreadyExists(final String email, final String id) {
      repository.findByEmail(email)
              .filter(user -> !user.getId().equals(id))  //se ele indentificar um usuario com o mesmo email, mas com id diferente, lança a exceção
              .ifPresent(user -> {
                  throw new DataIntegrityViolationException("Email already exists: " + email);
              });

    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream().map(mapper::fromEntity)
                .toList();
    }

    public UserResponse update(final String id, final UpdateUserRequest request) {
        User entity = find(id);
        verifyEmailAlreadyExists(request.email(), id);
        return mapper.fromEntity(
                repository.save(
                        mapper.update(request, entity)
                                .withPassword(request.password() != null ? encoder.encode(request.password()) : entity.getPassword())
                )
        );
    }


    private User find(final String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Object not found: " + id + ", Type: " + User.class.getName()
                ));
    }
}
