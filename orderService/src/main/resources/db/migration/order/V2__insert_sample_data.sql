-- Вставка заказов
INSERT INTO orders (user_id, restaurant_id, created_at, status, total_price) VALUES
(101, 1, NOW(), 'NEW', 25.50),
(102, 2, NOW(), 'DELIVERED', 40.00),
(103, 1, NOW(), 'CANCELLED', 0.00);

-- Вставка позиций заказа
INSERT INTO order_item (orders_id, item_name, quantity, price) VALUES
(1, 'Margherita Pizza', 1, 12.50),
(1, 'Coca-Cola', 2, 6.50),
(2, 'Cheeseburger', 2, 20.00),
(2, 'Fries', 2, 8.00),
(3, 'Lasagna', 1, 10.00);  -- этот заказ отменён, но для примера оставим

-- Вставка корзин
INSERT INTO cart (user_id, last_updated) VALUES
(101, NOW()),
(104, NOW());

-- Вставка позиций корзины
INSERT INTO cart_item (cart_id, menu_item_id, item_name, quantity, price) VALUES
(1, 301, 'Pasta Carbonara', 1, 14.00),
(1, 302, 'Orange Juice', 1, 5.50),
(2, 303, 'Chicken Wrap', 2, 8.00);