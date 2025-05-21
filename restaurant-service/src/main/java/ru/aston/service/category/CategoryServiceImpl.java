package ru.aston.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.mapper.CategoryMapper;
import ru.aston.model.dto.CategoryDto;
import ru.aston.model.entity.Category;
import ru.aston.repository.CategoryRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        log.info("Получение всех категорий");

        List<CategoryDto> categoryDto = categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();

        log.info("Получены все категории в количестве: {}", categoryDto.size());
        return categoryDto;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        log.info("Добавление категории: {}", categoryDto.getName());

        Category category = categoryRepository.save(categoryMapper.toEntity(categoryDto));
        CategoryDto newCategoryDto = categoryMapper.toDto(category);

        log.info("Категория добавлена: {}", newCategoryDto.getName());
        return newCategoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}