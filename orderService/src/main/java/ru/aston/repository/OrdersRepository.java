package ru.aston.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}