package ru.aston.userservice.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.aston.userservice.dto.OrderDTO;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderClient {

    private final RestTemplate restTemplate;

    public OrderClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<OrderDTO> getOrders(Long userId) {
        String url = "http://order-service/users/" + userId + "/orders";
        ResponseEntity<OrderDTO[]> response = restTemplate.getForEntity(url, OrderDTO[].class);
        return Arrays.asList(response.getBody());
    }

}
