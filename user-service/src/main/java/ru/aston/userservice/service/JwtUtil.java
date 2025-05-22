package ru.aston.userservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ru.aston.userservice.entity.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String SECRET_KEY = "aston";

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
    public static String generateToken(Set<Role> roles) {
        List<String> roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return JWT.create()
                .withClaim("roles", roleNames)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static String getUsernameFromToken(String token) {
        try {
            // Проверяем токен и декодируем его
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token); // Проверяем подпись токена

            // Извлекаем subject (username) из токена
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            // Обработка ошибок, если токен невалидный
            System.err.println("Invalid token: " + exception.getMessage());
            return null;
        }
    }

}
