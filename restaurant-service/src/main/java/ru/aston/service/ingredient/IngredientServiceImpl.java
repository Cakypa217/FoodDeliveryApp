package ru.aston.service.ingredient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.mapper.IngredientMapper;
import ru.aston.model.dto.IngredientDto;
import ru.aston.model.entity.Ingredient;
import ru.aston.repository.IngredientRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<IngredientDto> getAllIngredients() {
        log.info("Получение всех ингредиентов");

        List<IngredientDto> ingredient = ingredientRepository.findAll().stream()
                .map(ingredientMapper::toDto)
                .toList();

        log.info("Получены все ингредиенты в количестве: {}", ingredient.size());
        return ingredient;
    }

    @Override
    public IngredientDto addIngredient(IngredientDto ingredientDto) {
        log.info("Добавление ингредиента: {}", ingredientDto.getName());

        Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
        Ingredient ingredientSave = ingredientRepository.save(ingredient);
        IngredientDto newIngredientDto = ingredientMapper.toDto(ingredientSave);

        log.info("Ингредиент добавлен: {}", newIngredientDto.getName());
        return newIngredientDto;
    }

    @Override
    public void deleteIngredient(Long id) {
        log.info("Удаление ингредиента с id: {}", id);

        ingredientRepository.deleteById(id);

        log.info("Ингредиент с id: {} удален", id);
    }
}