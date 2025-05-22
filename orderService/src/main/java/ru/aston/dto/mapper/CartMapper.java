package ru.aston.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.dto.CartDto;
import ru.aston.entity.Cart;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    @Mapping(
            target = "totalCartPrice",
            expression = "java(calculateTotalCartPrice(cart))"
    )
    @Mapping(target = "userId", source = "userId")
    CartDto toDto(Cart cart);

    Cart toEntity(CartDto cartDto);

    default BigDecimal calculateTotalCartPrice(Cart cart) {
        if (cart.getItems() == null) return BigDecimal.ZERO;
        return cart.getItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
