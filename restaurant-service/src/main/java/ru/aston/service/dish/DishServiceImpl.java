package ru.aston.service.dish;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.mapper.DishMapper;
import ru.aston.model.dto.DishDto;
import ru.aston.model.dto.ShortDishDto;
import ru.aston.model.dto.UpdateDishDto;
import ru.aston.model.entity.Category;
import ru.aston.model.entity.Dish;
import ru.aston.model.entity.Ingredient;
import ru.aston.repository.CategoryRepository;
import ru.aston.repository.DishRepository;
import ru.aston.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final DishMapper dishMapper;

    @Override
    public List<ShortDishDto> getAllDishes(String category, String ingredient, Integer from, Integer size) {
        log.info("Получение всех блюд c критериями: category {}, ingredient {}, from {}, size {}",
                category, ingredient, from, size);

        Pageable pageable = PageRequest.of(from / size, size);
        List<Dish> dishes = dishRepository.searchByCriteria(category, ingredient, pageable);
        List<ShortDishDto> shortDishDto = dishes.stream()
                .map(dishMapper::toShortDto)
                .toList();

        log.info("Получены все блюда в количестве: {}", shortDishDto.size());
        return shortDishDto;
    }

    @Override
    public List<DishDto> searchDishes(String query) {
        log.info("Поиск блюд по запросу: {}", query);

        List<DishDto> dishes = dishRepository.findBySearch(query).stream()
                .map(dishMapper::toDto)
                .toList();

        log.info("Найдено блюд: {}", dishes.size());
        return dishes;
    }

    @Override
    public ShortDishDto getDishById(Long id) {
        log.info("Получение блюда с id: {}", id);

        Dish dish = dishById(id);
        ShortDishDto shortDishDto = dishMapper.toShortDto(dish);

        log.info("Получено блюдо: {}", shortDishDto.getName());
        return shortDishDto;
    }

    @Override
    @Transactional
    public DishDto addDish(DishDto dishDto) {
        log.info("Добавление блюда: {}", dishDto.getName());

        Dish dish = dishRepository.save(dishMapper.toEntity(dishDto));
        DishDto DishDto = dishMapper.toDto(dish);

        log.info("Добавлено блюдо: {}", DishDto.getName());
        return DishDto;
    }

    @Override
    @Transactional
    public DishDto updateDish(Long id, UpdateDishDto updateDishDto) {
        log.info("Обновление блюда с id: {}", id);

        Dish dish = dishById(id);

        Optional.ofNullable(updateDishDto.getName()).ifPresent(dish::setName);
        Optional.ofNullable(updateDishDto.getDescription()).ifPresent(dish::setDescription);
        Optional.ofNullable(updateDishDto.getPrice()).ifPresent(dish::setPrice);

        if (updateDishDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateDishDto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Категория не найдена: " + updateDishDto.getCategoryId()));
            dish.setCategory(category);
        }

        if (updateDishDto.getIngredientIds() != null) {
            List<Ingredient> ingredients = ingredientRepository.findAllById(updateDishDto.getIngredientIds());
            if (ingredients.size() != updateDishDto.getIngredientIds().size()) {
                throw new IllegalArgumentException("Некоторые ингредиенты не найдены");
            }
            dish.setIngredients(ingredients);
        }

        Dish updated = dishRepository.save(dish);
        DishDto updatedDishDto = dishMapper.toDto(updated);

        log.info("Блюдо с id {} обновлено", id);
        return updatedDishDto;
    }

    @Override
    @Transactional
    public void deleteDish(Long id) {
        log.info("Удаление блюда с id: {}", id);

        dishRepository.deleteById(id);

        log.info("Блюдо с id {} удалено", id);
    }

    @Override
    public Dish dishById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Блюдо с id " + id + " не найдено"));
    }
}