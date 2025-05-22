package ru.aston.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.entity.Orders;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByUserId(Long id);
}