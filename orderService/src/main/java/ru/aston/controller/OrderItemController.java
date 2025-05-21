package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.OrderItemDto;
import ru.aston.service.serviceImp.OrderItemServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemServiceImpl orderItemServiceImpl;

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAll() {
        List<OrderItemDto> dtos = orderItemServiceImpl.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getById(@PathVariable Long id) {
        OrderItemDto dto = orderItemServiceImpl.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> create(@RequestBody OrderItemDto dto) {
        // Передаем DTO в сервис для создания и получаем результат уже в формате DTO
        OrderItemDto createdItem = orderItemServiceImpl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> update(@PathVariable Long id, @RequestBody OrderItemDto dto) {
        // Передаем DTO в сервис для обновления и получаем результат уже в формате DTO
        OrderItemDto updatedItem = orderItemServiceImpl.update(id, dto);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
