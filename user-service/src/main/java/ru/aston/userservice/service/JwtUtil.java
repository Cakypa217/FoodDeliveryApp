package ru.aston.userservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String SECRET_KEY = "aston";

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
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
