CREATE TABLE "users" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(50),
    address VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE "roles" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);