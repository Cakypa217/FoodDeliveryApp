package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
