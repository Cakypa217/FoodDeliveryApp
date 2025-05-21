package ru.aston.dto;

import java.math.BigDecimal;

public record CartItemDto (
        Long id,
        Long cartId,
        Long menuItemId,
        String itemName,
        Integer quantity,
        BigDecimal price,
        BigDecimal totalPrice
){}
