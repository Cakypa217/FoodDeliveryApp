package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.dto.CartItemDto;
import ru.aston.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
    CartItem toEntity(CartItemDto cartItemDto);
}
