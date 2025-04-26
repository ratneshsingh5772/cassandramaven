package com.maven.mavenr.service;

import com.maven.mavenr.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDTO> createUser(UserCreateDTO createDTO);

    Mono<UserDTO> updateUser(String id, UserUpdateDTO updateDTO);

    Flux<UserDTO> searchUsers(UserSearchCriteria searchCriteria);

    Flux<UserDTO> getAllUsers();

    Mono<UserDTO> getUserById(String id);

    Mono<Void> deleteUser(String id);
    Mono<Long> countUsers(UserSearchCriteria searchCriteria);
}
