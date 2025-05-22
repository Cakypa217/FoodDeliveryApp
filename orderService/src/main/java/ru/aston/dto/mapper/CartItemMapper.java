package ru.aston.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.dto.CartItemDto;
import ru.aston.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "cartId", expression = "java(cartItem.getCart().getId())")
    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);

    CartItem toEntity(CartItemDto cartItemDto);
}
