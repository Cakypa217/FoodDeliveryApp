package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.model.dto.IngredientDto;
import ru.aston.model.entity.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDto toDto(Ingredient ingredient);

    Ingredient toEntity(IngredientDto ingredientDto);
}