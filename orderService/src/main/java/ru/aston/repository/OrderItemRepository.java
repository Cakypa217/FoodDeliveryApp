package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}