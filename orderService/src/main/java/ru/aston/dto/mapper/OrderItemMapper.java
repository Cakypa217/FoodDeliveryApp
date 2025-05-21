package ru.aston.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.dto.OrderItemDto;
import ru.aston.entity.OrderItem;
import ru.aston.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "ordersId", source = "orders.id")
    OrderItemDto toDto(OrderItem entity);

    @Mapping(target = "orders", expression = "java(mapOrders(dto.ordersId()))")
    OrderItem toEntity(OrderItemDto dto);

    default Orders mapOrders(Long id) {
        if (id == null) return null;
        Orders o = new Orders();
        o.setId(id);
        return o;
    }
}
