package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.OrderItemDto;
import ru.aston.entity.OrderItem;
import ru.aston.mapper.OrderItemMapper;
import ru.aston.service.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAll() {
        List<OrderItemDto> dtos = orderItemService.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getById(@PathVariable Long id) {
        OrderItemDto dto = orderItemService.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> create(@RequestBody OrderItemDto dto) {
        // Передаем DTO в сервис для создания и получаем результат уже в формате DTO
        OrderItemDto createdItem = orderItemService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> update(@PathVariable Long id, @RequestBody OrderItemDto dto) {
        // Передаем DTO в сервис для обновления и получаем результат уже в формате DTO
        OrderItemDto updatedItem = orderItemService.update(id, dto);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
