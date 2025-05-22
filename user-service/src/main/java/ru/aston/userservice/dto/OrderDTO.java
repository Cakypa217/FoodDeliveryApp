package ru.aston.userservice.dto;

import ru.aston.dto.OrderItemDto;
import ru.aston.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        Long userId,
        Long restaurantId,
        List<OrderItemDto> items,
        BigDecimal totalPrice,
        OrderStatus status,
        LocalDateTime createdAt
) {}
