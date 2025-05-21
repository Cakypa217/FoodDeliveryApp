package ru.aston.service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.OrderItemDto;
import ru.aston.entity.OrderItem;
import ru.aston.dto.mapper.OrderItemMapper;
import ru.aston.repository.OrderItemRepository;
import ru.aston.service.OrderItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemDto> getAll() {
        List<OrderItem> list = orderItemRepository.findAll();
        list.forEach(System.out::println);
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
