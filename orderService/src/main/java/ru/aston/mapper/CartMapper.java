package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.dto.CartDto;
import ru.aston.entity.Cart;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
    Cart toEntity(CartDto cartDto);

    List<CartDto> toDtoList(List<Cart> carts);
    List<Cart> toEntityList(List<CartDto> cartDtos);
}
