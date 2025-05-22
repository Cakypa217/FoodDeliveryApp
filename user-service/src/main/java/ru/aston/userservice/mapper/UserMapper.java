package ru.aston.userservice.mapper;

import ru.aston.userservice.dto.UserDTO;
import ru.aston.userservice.dto.UserInfoDTO;
import ru.aston.userservice.entity.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {

        if (user == null) return null;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setPassword(user.getPassword());

        return userDTO;

    }

    public static User toUser(UserDTO userDTO) {

        if (userDTO == null) return null;

        User user = new User();

        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public static UserInfoDTO toUserInfoDto(User user) {

        if (user == null) return null;

        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setName(user.getName());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setPhone(user.getPhone());
        userInfoDTO.setAddress(user.getAddress());

        return userInfoDTO;

    }

}
