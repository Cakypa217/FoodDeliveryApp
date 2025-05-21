package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.CartDto;
import ru.aston.dto.CartItemDto;
import ru.aston.service.serviceImp.CartServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartServiceImpl;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAll() {
        return ResponseEntity.ok(cartServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getById(@PathVariable Long id) {
        CartDto dto = cartServiceImpl.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartDto> create(@RequestBody CartDto dto) {
        CartDto created = cartServiceImpl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> update(@PathVariable Long id, @RequestBody CartDto dto) {
        CartDto updated = cartServiceImpl.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/item-add")
    public ResponseEntity<CartDto> addItemToCart(@RequestBody CartItemDto itemDto) {
        CartDto updatedCart = cartServiceImpl.addItemToCart(itemDto);
        return ResponseEntity.ok(updatedCart);
    }
}
