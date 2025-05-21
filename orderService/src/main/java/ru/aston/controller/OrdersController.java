package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.OrdersDto;
import ru.aston.dto.mapper.OrdersMapper;
import ru.aston.service.serviceImp.OrdersServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersServiceImpl ordersServiceImpl;
    private final OrdersMapper ordersMapper;

    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        return ResponseEntity.ok(ordersServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Long id) {
        return ordersServiceImpl.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto dto) {
        OrdersDto saved = ordersServiceImpl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDto> updateOrder(@PathVariable Long id, @RequestBody OrdersDto dto) {
        OrdersDto updated = ordersServiceImpl.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
