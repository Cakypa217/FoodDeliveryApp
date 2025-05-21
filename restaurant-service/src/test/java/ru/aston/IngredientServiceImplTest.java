package ru.aston;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aston.mapper.IngredientMapper;
import ru.aston.model.dto.IngredientDto;
import ru.aston.model.entity.Ingredient;
import ru.aston.repository.IngredientRepository;
import ru.aston.service.ingredient.IngredientServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientMapper ingredientMapper;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addIngredient_shouldSaveAndReturnDto() {
        IngredientDto dto = new IngredientDto(null, "Tomato");
        Ingredient entity = new Ingredient();
        entity.setName("Tomato");

        Ingredient savedEntity = new Ingredient();
        savedEntity.setId(1L);
        savedEntity.setName("Tomato");

        IngredientDto savedDto = new IngredientDto(1L, "Tomato");

        when(ingredientMapper.toEntity(dto)).thenReturn(entity);
        when(ingredientRepository.save(entity)).thenReturn(savedEntity);
        when(ingredientMapper.toDto(savedEntity)).thenReturn(savedDto);

        IngredientDto result = ingredientService.addIngredient(dto);

        assertThat(result).isEqualTo(savedDto);
        verify(ingredientRepository).save(entity);
    }

    @Test
    void getAllIngredients_shouldReturnListOfDtos() {
        Ingredient entity1 = new Ingredient();
        entity1.setId(1L);
        entity1.setName("Tomato");

        Ingredient entity2 = new Ingredient();
        entity2.setId(2L);
        entity2.setName("Cheese");

        List<Ingredient> entities = Arrays.asList(entity1, entity2);

        IngredientDto dto1 = new IngredientDto(1L, "Tomato");
        IngredientDto dto2 = new IngredientDto(2L, "Cheese");

        when(ingredientRepository.findAll()).thenReturn(entities);
        when(ingredientMapper.toDto(entity1)).thenReturn(dto1);
        when(ingredientMapper.toDto(entity2)).thenReturn(dto2);

        List<IngredientDto> result = ingredientService.getAllIngredients();

        assertThat(result).containsExactly(dto1, dto2);
        verify(ingredientRepository).findAll();
    }

    @Test
    void deleteIngredient_shouldCallRepositoryDelete() {
        Long id = 1L;
        ingredientService.deleteIngredient(id);
        verify(ingredientRepository).deleteById(id);
    }
}
