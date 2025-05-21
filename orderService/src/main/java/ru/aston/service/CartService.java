package ru.aston.service;

import ru.aston.dto.CartDto;
import ru.aston.dto.CartItemDto;

import java.util.List;

public interface CartService {
    List<CartDto> getAll();

    CartDto getById(Long id);

    CartDto create(CartDto dto);

    CartDto update(Long id, CartDto dto);

    void delete(Long id);

    CartDto addItemToCart(CartItemDto itemDto);
}
