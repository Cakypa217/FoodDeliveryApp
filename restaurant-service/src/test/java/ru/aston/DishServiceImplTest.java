package ru.aston;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
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
import ru.aston.service.dish.DishServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DishServiceImplTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private DishMapper dishMapper;

    @InjectMocks
    private DishServiceImpl dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDishes_shouldReturnListOfShortDtos() {
        String category = "Pizza";
        String ingredient = "Cheese";
        Integer from = 0;
        Integer size = 10;

        Dish dish1 = new Dish();
        dish1.setId(1L);
        dish1.setName("Margherita");

        Dish dish2 = new Dish();
        dish2.setId(2L);
        dish2.setName("Pepperoni");

        List<Dish> dishes = Arrays.asList(dish1, dish2);

        ShortDishDto shortDto1 = new ShortDishDto();
        shortDto1.setId(1L);
        shortDto1.setName("Margherita");

        ShortDishDto shortDto2 = new ShortDishDto();
        shortDto2.setId(2L);
        shortDto2.setName("Pepperoni");

        when(dishRepository.searchByCriteria(category, ingredient, PageRequest.of(0, 10)))
                .thenReturn(dishes);
        when(dishMapper.toShortDto(dish1)).thenReturn(shortDto1);
        when(dishMapper.toShortDto(dish2)).thenReturn(shortDto2);

        List<ShortDishDto> result = dishService.getAllDishes(category, ingredient, from, size);

        assertThat(result).containsExactly(shortDto1, shortDto2);
    }

    @Test
    void searchDishes_shouldReturnListOfDtos() {
        String query = "Pizza";

        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("Pizza Margherita");

        DishDto dishDto = new DishDto();
        dishDto.setId(1L);
        dishDto.setName("Pizza Margherita");

        when(dishRepository.findBySearch(query)).thenReturn(List.of(dish));
        when(dishMapper.toDto(dish)).thenReturn(dishDto);

        List<DishDto> result = dishService.searchDishes(query);

        assertThat(result).containsExactly(dishDto);
    }

    @Test
    void getDishById_shouldReturnShortDto() {
        Long id = 1L;
        Dish dish = new Dish();
        dish.setId(id);
        dish.setName("Pizza");

        ShortDishDto shortDishDto = new ShortDishDto();
        shortDishDto.setId(id);
        shortDishDto.setName("Pizza");

        when(dishRepository.findById(id)).thenReturn(Optional.of(dish));
        when(dishMapper.toShortDto(dish)).thenReturn(shortDishDto);

        ShortDishDto result = dishService.getDishById(id);

        assertThat(result).isEqualTo(shortDishDto);
    }

    @Test
    void addDish_shouldSaveAndReturnDto() {
        DishDto dishDto = new DishDto();
        dishDto.setName("New Pizza");

        Dish dish = new Dish();
        dish.setName("New Pizza");

        Dish savedDish = new Dish();
        savedDish.setId(1L);
        savedDish.setName("New Pizza");

        when(dishMapper.toEntity(dishDto)).thenReturn(dish);
        when(dishRepository.save(dish)).thenReturn(savedDish);
        when(dishMapper.toDto(savedDish)).thenReturn(dishDto);

        DishDto result = dishService.addDish(dishDto);

        assertThat(result).isEqualTo(dishDto);
        verify(dishRepository).save(dish);
    }

    @Test
    void updateDish_shouldUpdateAndReturnDto() {
        Long id = 1L;
        UpdateDishDto updateDto = new UpdateDishDto();
        updateDto.setName("Updated Pizza");
        updateDto.setCategoryId(1L);
        updateDto.setIngredientIds(List.of(1L, 2L));

        Dish existingDish = new Dish();
        existingDish.setId(id);
        existingDish.setName("Old Pizza");

        Category category = new Category();
        category.setId(1L);

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient(1L, "Cheese", null),
                new Ingredient(2L, "Tomato", null)
        );

        when(dishRepository.findById(id)).thenReturn(Optional.of(existingDish));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(ingredientRepository.findAllById(any())).thenReturn(ingredients);
        when(dishRepository.save(any())).thenReturn(existingDish);
        when(dishMapper.toDto(any())).thenReturn(new DishDto());

        DishDto result = dishService.updateDish(id, updateDto);

        assertThat(result).isNotNull();
        verify(dishRepository).save(any());
    }

    @Test
    void deleteDish_shouldCallRepositoryDelete() {
        Long id = 1L;
        dishService.deleteDish(id);
        verify(dishRepository).deleteById(id);
    }
}