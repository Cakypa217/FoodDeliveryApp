package ru.aston.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@ToString(exclude = "orders")
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Orders orders;

    private String productName;
    private Integer quantity;
    private BigDecimal price;

    @Transient
    public BigDecimal getTotalPrice() {
        return price != null && quantity != null
                ? price.multiply(BigDecimal.valueOf(quantity))
                : BigDecimal.ZERO;
    }
}

