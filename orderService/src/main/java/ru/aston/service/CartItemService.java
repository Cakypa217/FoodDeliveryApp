package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.CartItemDto;
import ru.aston.entity.CartItem;
import ru.aston.mapper.CartItemMapper;
import ru.aston.repository.CartItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public List<CartItemDto> getAll() {
        return cartItemRepository.findAll().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartItemDto getById(Long id) {
        return cartItemRepository.findById(id)
                .map(cartItemMapper::toDto)
                .orElse(null);
    }

    public CartItemDto create(CartItemDto dto) {
        CartItem entity = cartItemMapper.toEntity(dto);
        CartItem saved = cartItemRepository.save(entity);
        return cartItemMapper.toDto(saved);
    }

    public CartItemDto update(Long id, CartItemDto dto) {
        CartItem entity = cartItemMapper.toEntity(dto);
        entity.setId(id); // Ensure ID is set for update
        CartItem updated = cartItemRepository.save(entity);
        return cartItemMapper.toDto(updated);
    }

    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
