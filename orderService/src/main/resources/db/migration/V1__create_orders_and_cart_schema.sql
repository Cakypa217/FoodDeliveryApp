-- Таблица заказов
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL
);

-- Таблица позиций заказа
CREATE TABLE order_item (
    id SERIAL PRIMARY KEY,
    orders_id BIGINT NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order_item_orders FOREIGN KEY (orders_id) REFERENCES orders(id) ON DELETE CASCADE
);

-- Таблица корзин
CREATE TABLE cart (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    last_updated TIMESTAMP NOT NULL
);

-- Таблица позиций корзины
CREATE TABLE cart_item (
    id SERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    menu_item_id BIGINT NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_cart_item_cart FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE
);