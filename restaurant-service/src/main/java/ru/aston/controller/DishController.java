package ru.aston.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String ingredient,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        return dishService.getAllDishes(category, ingredient, from, size);
    }

    @GetMapping("/{id}")
    public ShortDishDto getDishById(@PathVariable Long id) {
        return dishService.getDishById(id);
    }

    @GetMapping("/search")
    public List<DishDto> searchDishes(@RequestParam String query) {
        return dishService.searchDishes(query);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto addDish(@RequestBody DishDto dishDto) {
        return dishService.addDish(dishDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}")
    public DishDto updateDish(@PathVariable Long id, @RequestBody UpdateDishDto updateDishDto) {
        return dishService.updateDish(id, updateDishDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
    }
}