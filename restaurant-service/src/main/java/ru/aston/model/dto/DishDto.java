package ru.aston.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.aston.model.entity.Category;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishDto {

    private Long id;

    @NotNull
    private String name;

    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer weight;

    private String imageUrl;

    @NotNull
    private Category category;

    @NotNull
    private List<IngredientDto> ingredients;
}