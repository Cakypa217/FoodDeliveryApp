package ru.aston.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortDishDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    private String imageUrl;
}