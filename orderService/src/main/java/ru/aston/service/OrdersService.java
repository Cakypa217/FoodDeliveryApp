package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.OrdersDto;
import ru.aston.entity.Orders;
import ru.aston.mapper.OrdersMapper;
import ru.aston.repository.OrdersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public List<OrdersDto> getAll() {
        return ordersRepository.findAll().stream()
                .map(ordersMapper::toDto)
                .toList();
    }

    public Optional<OrdersDto> getById(Long id) {
        return ordersRepository.findById(id)
                .map(ordersMapper::toDto);
    }

    public OrdersDto create(OrdersDto dto) {
        Orders saved = ordersRepository.save(ordersMapper.toEntity(dto));
        return ordersMapper.toDto(saved);
    }

    public OrdersDto update(Long id, OrdersDto dto) {
        Orders existing = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Orders updated = ordersMapper.toEntity(dto);
        updated.setId(existing.getId());
        return ordersMapper.toDto(ordersRepository.save(updated));
    }

    public void delete(Long id) {
        ordersRepository.deleteById(id);
    }
}
