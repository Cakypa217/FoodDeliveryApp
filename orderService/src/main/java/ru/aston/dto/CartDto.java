package ru.aston.dto;

import java.util.List;

public record CartDto (
        Long id,
        Long userId,
        List<CartItemDto> items,
        Double totalPrice
){}
