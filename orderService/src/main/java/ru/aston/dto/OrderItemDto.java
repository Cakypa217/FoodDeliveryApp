package ru.aston.dto;

import java.math.BigDecimal;

public record OrderItemDto (
    Long id,
    Long ordersId,
    String productName,
    Integer quantity,
    BigDecimal price
) {}
