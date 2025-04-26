package com.maven.mavenr.mapper;

import com.maven.mavenr.dto.*;
import com.maven.mavenr.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User toEntity(UserCreateDTO createDTO) {
        if (createDTO == null) {
            return null;
        }
        User user = new User();
        user.setName(createDTO.getName());
        user.setEmail(createDTO.getEmail());
        return user;
    }

    public static void updateEntity(User user, UserUpdateDTO updateDTO) {
        if (updateDTO.getName() != null) {
            user.setName(updateDTO.getName());
        }
        if (updateDTO.getEmail() != null) {
            user.setEmail(updateDTO.getEmail());
        }
    }
}
