package com.maven.mavenr.service.impl;

import com.maven.mavenr.dto.UserDTO;
import com.maven.mavenr.mapper.UserMapper;
import com.maven.mavenr.model.User;
import com.maven.mavenr.repository.UserRepository;
import com.maven.mavenr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDTO> createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user.generateId();
        return userRepository.save(user)
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
}
