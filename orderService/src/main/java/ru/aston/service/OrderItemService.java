package ru.aston.service;

import ru.aston.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAll();

    OrderItemDto getById(Long id);

    OrderItemDto create(OrderItemDto dto);

    OrderItemDto update(Long id, OrderItemDto dto);

    void delete(Long id);
}
