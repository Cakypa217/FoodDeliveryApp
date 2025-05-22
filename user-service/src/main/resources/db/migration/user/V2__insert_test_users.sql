INSERT INTO "user" (id, name, email, phone, address, password) VALUES
(1, 'Иван Иванов', 'ivan@example.com', '+79990001111', 'Москва, ул. Пушкина, д. 1', 'pass123'),
(2, 'Ольга Смирнова', 'olga@example.com', '+79990002222', 'Санкт-Петербург, Невский пр., д. 2', 'secret456'),
(3, 'Дмитрий Кузнецов', 'dmitry@example.com', '+79990003333', 'Екатеринбург, ул. Ленина, д. 3', 'admin789');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');