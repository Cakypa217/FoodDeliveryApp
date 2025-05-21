package ru.aston.service;

import ru.aston.dto.OrdersDto;

import java.util.List;
import java.util.Optional;

public interface OrdersService {

    List<OrdersDto> getAll();

    Optional<OrdersDto> getById(Long id);

    OrdersDto create(OrdersDto dto);

    OrdersDto update(Long id, OrdersDto dto);

    void delete(Long id);
}
