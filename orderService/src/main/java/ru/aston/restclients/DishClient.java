package ru.aston.restclients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.aston.model.dto.ShortDishDto;

@Component
@RequiredArgsConstructor
public class DishClient {

    private final RestTemplate restTemplate;

    @Value("${dish-service.url}")
    private String dishServiceUrl;

    public ShortDishDto getDishById(Long id) {
        return restTemplate.getForObject(dishServiceUrl + "/api/v1/dishes/" + id, ShortDishDto.class);
    }
}
