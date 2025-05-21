package ru.aston.service.serviceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aston.dto.CartItemDto;
import ru.aston.entity.CartItem;
import ru.aston.dto.mapper.CartItemMapper;
import ru.aston.repository.CartItemRepository;
import ru.aston.service.CartItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public List<CartItemDto> getAll() {
        log.info("Fetching all cart items");
        List<CartItemDto> result = cartItemRepository.findAll().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());
        if (log.isDebugEnabled()) {
            String joinedItems = result.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"));
            log.debug("Found {} items:\n{}", result.size(), joinedItems);
        }
        return result;
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
