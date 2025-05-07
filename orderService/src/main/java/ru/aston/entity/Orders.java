package ru.aston.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long restaurantId;

    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING )
    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private BigDecimal totalPrice;
}