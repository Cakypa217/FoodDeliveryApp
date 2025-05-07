package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.dto.OrderItemDto;
import ru.aston.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem entity);
    OrderItem toEntity(OrderItemDto dto);
}
