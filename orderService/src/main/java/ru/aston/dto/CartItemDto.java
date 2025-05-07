package ru.aston.dto;

public record CartItemDto (
     Long id,
     Long productId,
     Integer quantity,
     Double price,
     Double totalPrice
){}
