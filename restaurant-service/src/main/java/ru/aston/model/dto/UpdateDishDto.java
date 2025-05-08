package ru.aston.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDishDto {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer weight;

    private String imageUrl;

    private Long categoryId;

    private List<Long> ingredientIds;
}