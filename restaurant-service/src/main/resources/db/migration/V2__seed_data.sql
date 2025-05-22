INSERT INTO category (name) VALUES
    ('Пицца'),
    ('Суши'),
    ('Бургеры'),
    ('Напитки')
ON CONFLICT DO NOTHING;

INSERT INTO ingredient (name) VALUES
    ('Сыр'),
    ('Томат'),
    ('Курица'),
    ('Говядина'),
    ('Огурец'),
    ('Рис'),
    ('Нори'),
    ('Булочка'),
    ('Соус'),
    ('Вода')
ON CONFLICT DO NOTHING;

INSERT INTO dish (name, description, price, weight, image_url, category_id)
VALUES
    ('Маргарита', 'Пицца с томатным соусом, сыром и базиликом', 450.00, 500, 'https://example.com/images/margarita.jpg', 1),
    ('Пепперони', 'Острая пицца с салями и сыром моцарелла', 550.00, 530, 'https://example.com/images/pepperoni.jpg', 1),
    ('Калифорния ролл', 'Ролл с крабом, авокадо и огурцом', 400.00, 200, 'https://example.com/images/california.jpg', 2),
    ('Филадельфия', 'Ролл с лососем, сливочным сыром и огурцом', 480.00, 220, 'https://example.com/images/philadelphia.jpg', 2),
    ('Кока-Кола 0.5л', 'Газированный напиток', 100.00, 500, 'https://example.com/images/coke.jpg', 3),
    ('Апельсиновый сок', 'Свежевыжатый апельсиновый сок', 150.00, 300, 'https://example.com/images/orange-juice.jpg', 3);
