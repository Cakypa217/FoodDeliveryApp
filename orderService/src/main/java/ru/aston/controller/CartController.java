package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.CartDto;
import ru.aston.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAll() {
        return ResponseEntity.ok(cartService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getById(@PathVariable Long id) {
        CartDto dto = cartService.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartDto> create(@RequestBody CartDto dto) {
        CartDto created = cartService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> update(@PathVariable Long id, @RequestBody CartDto dto) {
        CartDto updated = cartService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
