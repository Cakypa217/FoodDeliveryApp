package ru.aston.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aston.userservice.dto.LoginDTO;
import ru.aston.userservice.dto.OrderDTO;
import ru.aston.userservice.dto.UserDTO;
import ru.aston.userservice.dto.UserInfoDTO;
import ru.aston.userservice.entity.User;
import ru.aston.userservice.mapper.UserMapper;
import ru.aston.userservice.repository.UserRepository;
import ru.aston.userservice.service.EncryptPass;
import ru.aston.userservice.service.JwtUtil;
import ru.aston.userservice.service.OrderClient;

import java.util.List;

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
    public String registerUser(@RequestBody UserDTO user) {
        if (userRepository.existsById(user.getId())) {
            return "User already exists";
        }
        userRepository.save(UserMapper.toUser(user));
        return JwtUtil.generateToken(user.getEmail());
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail()).orElse(null);

        if (user != null) {
            if (user.getPassword().equals(EncryptPass.encrypt(loginDTO.getPassword()))) {
                return JwtUtil.generateToken(user.getEmail());
            }
        }
        return "Invalid username or password";
    }

    @GetMapping("/{id}/orders")
    public List<OrderDTO> getOrders(@PathVariable Long id) {
        return orderClient.getOrders(id);
    }



}
