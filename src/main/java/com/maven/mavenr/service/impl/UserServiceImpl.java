package com.maven.mavenr.service.impl;

import com.maven.mavenr.dto.*;
import com.maven.mavenr.mapper.UserMapper;
import com.maven.mavenr.model.User;
import com.maven.mavenr.repository.UserRepository;
import com.maven.mavenr.service.UserService;
import com.maven.mavenr.specification.UserSpecification; // ✅ Import this
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSpecification userSpecification; // ✅ Inject

    @Override
    public Mono<UserDTO> createUser(UserCreateDTO createDTO) {
        User user = UserMapper.toEntity(createDTO);
        user.generateId();
        return userRepository.save(user)
                .map(UserMapper::toDTO);
    }

    @Override
    public Mono<UserDTO> updateUser(String id, UserUpdateDTO updateDTO) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found with id: " + id)))
                .flatMap(existingUser -> {
                    UserMapper.updateEntity(existingUser, updateDTO);
                    return userRepository.save(existingUser);
                })
                .map(UserMapper::toDTO);
    }

    @Override
    public Flux<UserDTO> searchUsers(UserSearchCriteria searchCriteria) {
        return userSpecification.applyFilter(userRepository.findAll(), searchCriteria)
                .map(UserMapper::toDTO);
    }

    @Override
    public Flux<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .map(UserMapper::toDTO);
    }

    @Override
    public Mono<UserDTO> getUserById(String id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<Long> countUsers(UserSearchCriteria searchCriteria) {
        return userSpecification.applyFilter(userRepository.findAll(), searchCriteria)
                .count();
    }

}
