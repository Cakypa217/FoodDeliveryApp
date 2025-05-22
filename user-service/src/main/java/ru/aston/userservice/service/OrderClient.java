package ru.aston.userservice.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.aston.dto.OrdersDto;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderClient {

    private final RestTemplate restTemplate;

    public OrderClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<OrdersDto> getOrders(Long userId) {
        String url = "http://localhost:8088/api/v1/orders/user/" + userId;
        ResponseEntity<OrdersDto> response = restTemplate.getForEntity(url, OrdersDto.class);
        return Arrays.asList(response.getBody());
    }

}
