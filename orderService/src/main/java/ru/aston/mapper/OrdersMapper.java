package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.dto.OrdersDto;
import ru.aston.entity.Orders;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrdersMapper {
    OrdersDto toDto(Orders entity);
    Orders toEntity(OrdersDto dto);
}
