package ru.aston.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cart cart;

    private Long menuItemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
}
