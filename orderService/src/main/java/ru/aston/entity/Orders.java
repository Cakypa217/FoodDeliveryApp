package ru.aston.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private OrderStatus status; // enum: NEW(0), CONFIRMED(1), DELIVERED(2), CANCELLED(3)

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private BigDecimal totalPrice;
}