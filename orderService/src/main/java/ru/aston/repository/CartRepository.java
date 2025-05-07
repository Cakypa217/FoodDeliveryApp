package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
