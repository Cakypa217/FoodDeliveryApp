package ru.aston.service;

import ru.aston.dto.CartItemDto;

import java.util.List;

public interface CartItemService {
    List<CartItemDto> getAll();

    CartItemDto getById(Long id);

    CartItemDto create(CartItemDto dto);

    CartItemDto update(Long id, CartItemDto dto);

    void delete(Long id);
}
