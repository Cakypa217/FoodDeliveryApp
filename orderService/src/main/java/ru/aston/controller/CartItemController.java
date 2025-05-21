package ru.aston.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.CartItemDto;
import ru.aston.service.serviceImp.CartItemServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemServiceImpl cartItemServiceImpl;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAll() {
        return ResponseEntity.ok(cartItemServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getById(@PathVariable Long id) {
        CartItemDto dto = cartItemServiceImpl.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartItemDto> create(@RequestBody CartItemDto dto) {
        CartItemDto created = cartItemServiceImpl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> update(@PathVariable Long id, @RequestBody CartItemDto dto) {
        CartItemDto updated = cartItemServiceImpl.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartItemServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
