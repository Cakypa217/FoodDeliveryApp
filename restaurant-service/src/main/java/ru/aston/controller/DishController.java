package ru.aston.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.aston.model.dto.DishDto;
import ru.aston.model.dto.ShortDishDto;
import ru.aston.model.dto.UpdateDishDto;
import ru.aston.service.dish.DishService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<ShortDishDto> getAllDishes(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "ingredient", required = false) String ingredient,
            @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(name = "size", defaultValue = "10") @Positive Integer size) {
        return dishService.getAllDishes(category, ingredient, from, size);
    }

    @GetMapping("/{id}")
    public ShortDishDto getDishById(@PathVariable("id") Long id) {
        return dishService.getDishById(id);
    }

    @GetMapping("/search")
    public List<DishDto> searchDishes(@RequestParam(name = "query") String query) {
        return dishService.searchDishes(query);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto addDish(@RequestBody DishDto dishDto) {
        return dishService.addDish(dishDto);
    }

    @PatchMapping("/update/{id}")
    public DishDto updateDish(@PathVariable("id") Long id, @RequestBody UpdateDishDto updateDishDto) {
        return dishService.updateDish(id, updateDishDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable("id") Long id) {
        dishService.deleteDish(id);
    }
}