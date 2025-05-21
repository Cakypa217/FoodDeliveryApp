package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.model.dto.CategoryDto;
import ru.aston.model.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}