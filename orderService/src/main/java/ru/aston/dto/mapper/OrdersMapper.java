package ru.aston.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.dto.OrdersDto;
import ru.aston.entity.Orders;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrdersMapper {
    @Mapping(target = "totalPrice", expression = "java(calculateTotalPrice(entity))")
    OrdersDto toDto(Orders entity);

    Orders toEntity(OrdersDto dto);

    default BigDecimal calculateTotalPrice(Orders orders) {
        if (orders.getItems() == null) return BigDecimal.ZERO;
        return orders.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
