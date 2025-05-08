package ru.aston.service.dish;

import ru.aston.model.dto.DishDto;
import ru.aston.model.dto.ShortDishDto;
import ru.aston.model.dto.UpdateDishDto;
import ru.aston.model.entity.Dish;

import java.util.List;

public interface DishService {

    List<ShortDishDto> getAllDishes(String category, String ingredient, Integer from, Integer size);

    ShortDishDto getDishById(Long id);

    List<DishDto> searchDishes(String query);

    DishDto addDish(DishDto dishDto);

    DishDto updateDish(Long id, UpdateDishDto updateDishDto);

    void deleteDish(Long id);

    Dish dishById(Long id);
}