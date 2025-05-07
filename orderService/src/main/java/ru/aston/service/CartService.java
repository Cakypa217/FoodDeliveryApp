package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.CartDto;
import ru.aston.entity.Cart;
import ru.aston.mapper.CartMapper;
import ru.aston.repository.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public List<CartDto> getAll() {
        return cartRepository.findAll().stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartDto getById(Long id) {
        return cartRepository.findById(id)
                .map(cartMapper::toDto)
                .orElse(null);
    }

    public CartDto create(CartDto dto) {
        Cart cart = cartMapper.toEntity(dto);
        Cart saved = cartRepository.save(cart);
        return cartMapper.toDto(saved);
    }

    public CartDto update(Long id, CartDto dto) {
        Cart cart = cartMapper.toEntity(dto);
        cart.setId(id);
        Cart updated = cartRepository.save(cart);
        return cartMapper.toDto(updated);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
