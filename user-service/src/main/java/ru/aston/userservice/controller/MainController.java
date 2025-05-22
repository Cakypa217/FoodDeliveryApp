package ru.aston.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.OrdersDto;
import ru.aston.userservice.dto.LoginDTO;
import ru.aston.userservice.dto.UserDTO;
import ru.aston.userservice.dto.UserInfoDTO;
import ru.aston.userservice.entity.Role;
import ru.aston.userservice.entity.User;
import ru.aston.userservice.mapper.UserMapper;
import ru.aston.userservice.repository.UserRepository;
import ru.aston.userservice.service.EncryptPass;
import ru.aston.userservice.service.JwtUtil;
import ru.aston.userservice.service.OrderClient;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final OrderClient orderClient;

    @GetMapping("/{id}")
    public UserInfoDTO getUser(@PathVariable Long id) {
        return UserMapper.toUserInfoDto(userRepository.getReferenceById(id));
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "User already exists";
        }

        User user = UserMapper.toUser(userDTO);
        user.setPassword(EncryptPass.encrypt(user.getPassword()));

        // Назначим роль по умолчанию
        Role defaultRole = new Role();
        defaultRole.setId(2L); // допустим ID роли ROLE_USER в БД = 2
        user.setRoles(Set.of(defaultRole));

        userRepository.save(user);

        return JwtUtil.generateToken(user.getEmail());
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail());
        System.out.println(userRepository.findByEmail(loginDTO.getEmail()));
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElse(null);

        if (user != null) {
            System.out.println("111111");
//            if (user.getPassword().equals(EncryptPass.encrypt(loginDTO.getPassword()))) {
//                return JwtUtil.generateToken(user.getEmail());
//            }
            if (user.getPassword().equals(loginDTO.getPassword())) {
                return JwtUtil.generateToken(user.getRoles());
            }
        }
        return "Invalid username or password";
    }

    @GetMapping("/{id}/orders")
    public List<OrdersDto> getOrders(@PathVariable Long id) {
        return orderClient.getOrders(id);
    }



}
