package ru.aston.dto;

import ru.aston.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrdersDto(
        Long id,
        Long userId,
        Long restaurantId,
        List<OrderItemDto> items,
        Double totalPrice,
        OrderStatus status,
        LocalDateTime createdAt
) {}

