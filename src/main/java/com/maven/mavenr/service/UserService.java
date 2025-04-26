package com.maven.mavenr.service;

import com.maven.mavenr.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDTO> createUser(UserDTO userDTO);

    Flux<UserDTO> getAllUsers();

    Mono<UserDTO> getUserById(String id);

    Mono<Void> deleteUser(String id);
}
