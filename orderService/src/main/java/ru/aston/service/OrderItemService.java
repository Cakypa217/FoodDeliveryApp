package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.OrderItemDto;
import ru.aston.entity.OrderItem;
import ru.aston.mapper.OrderItemMapper;
import ru.aston.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemDto> getAll() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderItemDto getById(Long id) {
        return orderItemRepository.findById(id)
                .map(orderItemMapper::toDto)
                .orElse(null);
    }

    public OrderItemDto create(OrderItemDto dto) {

        OrderItem orderItem = orderItemMapper.toEntity(dto);
        OrderItem savedItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(savedItem);
    }

    public OrderItemDto update(Long id, OrderItemDto dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        orderItem.setId(id);
        OrderItem updatedItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(updatedItem);
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
