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
