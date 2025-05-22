package ru.aston.service.serviceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.OrdersDto;
import ru.aston.dto.mapper.OrderItemMapper;
import ru.aston.entity.Cart;
import ru.aston.entity.OrderItem;
import ru.aston.entity.Orders;
import ru.aston.dto.mapper.OrdersMapper;
import ru.aston.repository.CartRepository;
import ru.aston.repository.OrdersRepository;
import ru.aston.service.OrdersService;
import ru.aston.entity.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final CartRepository cartRepository;
    private final OrderItemMapper ordersItemMapper;

    public List<OrdersDto> getAll() {
        List<Orders> list = ordersRepository.findAll();
        list.forEach(System.out::println);
        return ordersRepository.findAll().stream()
                .map(ordersMapper::toDto)
                .toList();
    }

    public Optional<OrdersDto> getById(Long id) {
        return ordersRepository.findById(id)
                .map(ordersMapper::toDto);
    }

    public Optional<OrdersDto> getByUserId(Long id){
        return ordersRepository.findByUserId(id)
                .map(ordersMapper::toDto);
    }

    public OrdersDto create(OrdersDto dto) {
        // 1. Найти корзину пользователя
        Cart cart = cartRepository.findByUserId(dto.userId())
                .orElseThrow(() -> new RuntimeException("Cart not found for userId: " + dto.userId()));

        // 2. Преобразовать CartItem в OrderItem
        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(cartItem.getItemName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).toList();

        // 3. Создать новый заказ
        Orders order = new Orders();
        order.setUserId(dto.userId());
        order.setRestaurantId(dto.restaurantId());
        order.setStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDateTime.now());
        order.setItems(orderItems);

        // Установить обратную связь на заказ
        orderItems.forEach(item -> item.setOrders(order));

        // 4. Подсчитать totalPrice
        BigDecimal total = orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(total);

        // 5. Сохранить заказ
        Orders saved = ordersRepository.save(order);

        // 6. Очистить корзину
        cart.getItems().clear();
        cartRepository.delete(cart);

        return ordersMapper.toDto(saved);
    }

    public OrdersDto update(Long id, OrdersDto dto) {
        Orders existing = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Orders updated = ordersMapper.toEntity(dto);
        updated.setId(existing.getId());
        return ordersMapper.toDto(ordersRepository.save(updated));
    }

    public void delete(Long id) {
        ordersRepository.deleteById(id);
    }
}
