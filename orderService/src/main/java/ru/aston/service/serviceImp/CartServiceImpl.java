package ru.aston.service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.CartDto;
import ru.aston.dto.CartItemDto;
import ru.aston.entity.Cart;
import ru.aston.dto.mapper.CartMapper;
import ru.aston.entity.CartItem;
import ru.aston.model.dto.ShortDishDto;
import ru.aston.repository.CartRepository;
import ru.aston.restclients.DishClient;
import ru.aston.service.CartService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class CartServiceImpl implements CartService {
//    private final CartRepository cartRepository;
//    private final CartMapper cartMapper;
//
//    public List<CartDto> getAll() {
//        List<Cart> carts = cartRepository.findAll();
//        carts.forEach(System.out::println);
//        return cartRepository.findAll().stream()
//                .map(cartMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    public CartDto getById(Long id) {
//        Optional<Cart> cart = cartRepository.findById(id);
//        System.out.println(cart);
//        return cartRepository.findById(id)
//                .map(cartMapper::toDto)
//                .orElse(null);
//    }
//
//    public CartDto create(CartDto dto) {
//        Cart cart = cartMapper.toEntity(dto);
//        cart.setLastUpdated(LocalDateTime.now());
//        Cart saved = cartRepository.save(cart);
//        return cartMapper.toDto(saved);
//    }
//
//    public CartDto update(Long id, CartDto dto) {
//        Cart cart = cartMapper.toEntity(dto);
//        cart.setId(id);
//        Cart updated = cartRepository.save(cart);
//        return cartMapper.toDto(updated);
//    }
//
//    public void delete(Long id) {
//        cartRepository.deleteById(id);
//    }
//
//    public CartDto addItemToCart(CartItemDto itemDto) {
//        // Получаем корзину по cartId из DTO
//        Cart cart = cartRepository.findById(itemDto.cartId())
//                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + itemDto.cartId()));
//
//        // Ищем, существует ли уже такой товар в корзине
//        Optional<CartItem> existingItemOpt = cart.getItems().stream()
//                .filter(i -> i.getMenuItemId().equals(itemDto.menuItemId()))
//                .findFirst();
//
//        if (existingItemOpt.isPresent()) {
//            // Увеличиваем количество, если товар уже есть
//            CartItem existingItem = existingItemOpt.get();
//            int addedQuantity = itemDto.quantity() != null ? itemDto.quantity() : 1;
//            existingItem.setQuantity(existingItem.getQuantity() + addedQuantity);
//        } else {
//            // Создаем новый CartItem
//            CartItem newItem = new CartItem();
//            newItem.setMenuItemId(itemDto.menuItemId());
//            newItem.setItemName(itemDto.itemName());
//            newItem.setQuantity(itemDto.quantity() != null ? itemDto.quantity() : 1);
//            newItem.setPrice(itemDto.price());
//            newItem.setCart(cart); // связываем с корзиной
//
//            cart.getItems().add(newItem);
//        }
//
//        cart.setLastUpdated(LocalDateTime.now());
//        Cart saved = cartRepository.save(cart);
//        return cartMapper.toDto(saved);
//    }
//}

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final DishClient dishClient; // ✅ Инжектируем клиент

    public List<CartDto> getAll() {
        return cartRepository.findAll().stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartDto getById(Long id) {
        return cartRepository.findById(id)
                .map(cartMapper::toDto)
                .orElse(null);
    }

    public CartDto create(CartDto dto) {
        Cart cart = cartMapper.toEntity(dto);
        cart.setLastUpdated(LocalDateTime.now());
        Cart saved = cartRepository.save(cart);
        return cartMapper.toDto(saved);
    }

    public CartDto update(Long id, CartDto dto) {
        Cart cart = cartMapper.toEntity(dto);
        cart.setId(id);
        Cart updated = cartRepository.save(cart);
        return cartMapper.toDto(updated);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    public CartDto addItemToCart(CartItemDto itemDto) {
        Cart cart = cartRepository.findById(itemDto.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + itemDto.cartId()));

        // ✅ Получаем данные блюда из dish-service
        ShortDishDto dish = dishClient.getDishById(itemDto.menuItemId());

        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(i -> i.getMenuItemId().equals(itemDto.menuItemId()))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            int addedQuantity = itemDto.quantity() != null ? itemDto.quantity() : 1;
            existingItem.setQuantity(existingItem.getQuantity() + addedQuantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setMenuItemId(dish.getId());
            newItem.setItemName(dish.getName());
            newItem.setPrice(dish.getPrice());
            newItem.setQuantity(itemDto.quantity() != null ? itemDto.quantity() : 1);
            newItem.setCart(cart);

            cart.getItems().add(newItem);
        }

        cart.setLastUpdated(LocalDateTime.now());
        Cart saved = cartRepository.save(cart);
        return cartMapper.toDto(saved);
    }
}