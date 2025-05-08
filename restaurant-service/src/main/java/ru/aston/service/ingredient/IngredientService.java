package ru.aston.service.ingredient;

import ru.aston.model.dto.IngredientDto;

import java.util.List;

public interface IngredientService {

    List<IngredientDto> getAllIngredients();

    IngredientDto addIngredient(IngredientDto ingredientDto);

    void deleteIngredient(Long id);
}