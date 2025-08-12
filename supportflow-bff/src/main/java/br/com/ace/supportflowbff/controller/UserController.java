package br.com.ace.supportflowbff.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import models.requests.CreateUserRequest;
import models.requests.UpdateUserRequest;
import models.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/users")
@Tag(name = "UserController", description = "User management operations")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") final String id);

    @PostMapping
    ResponseEntity<Void> save(@RequestBody final CreateUserRequest createUserRequest);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(@PathVariable final String id, @RequestBody final UpdateUserRequest updateUserRequest);

}
