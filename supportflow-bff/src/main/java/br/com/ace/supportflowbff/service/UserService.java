package br.com.ace.supportflowbff.service;

import br.com.ace.supportflowbff.client.UserFeingClient;
import lombok.RequiredArgsConstructor;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFeingClient feignClient;
/*    private final BCryptPasswordEncoder encoder;*/

    public UserResponse findById(final String id) {
        return feignClient.findById(id).getBody(); // findbyid returns a ResponseEntity
    }

    public void save(CreateUserRequest request) {
        feignClient.save(request);
    }


    public List<UserResponse> findAll() {
        return feignClient.findAll().getBody();
    }

    public UserResponse update(final String id, final UpdateUserRequest request) {
        return feignClient.update(id, request).getBody();
    }

}
