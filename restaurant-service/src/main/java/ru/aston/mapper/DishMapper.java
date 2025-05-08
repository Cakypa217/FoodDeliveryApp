package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.model.dto.DishDto;
import ru.aston.model.dto.ShortDishDto;
import ru.aston.model.entity.Dish;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishDto toDto(Dish dish);

    ShortDishDto toShortDto(Dish dish);

    Dish toEntity(DishDto dishDto);

    Dish toEntity(ShortDishDto shortDishDto);
}