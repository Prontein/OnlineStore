create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price) values ('Bread', 25), ('Milk', 80), ('Cheese', 450), ('Oil', 560), ('Potato', 95);
insert into products (title, price) values ('Onion', 34), ('Garlic', 40), ('Pepper', 85), ('Carrot', 120), ('Cucumber', 95);
insert into products (title, price) values ('Meat', 600), ('Spices', 225), ('Water', 91), ('Apple', 65), ('Orange', 75);
insert into products (title, price) values ('Juice', 165), ('Lemon', 68), ('Corn', 195), ('Tomato', 130), ('Cabbage', 170);

create table comments
(
    id                  bigserial primary key,
    username            varchar(30) not null,
    consumer            varchar(30) not null,
    content             varchar(255),
    product_id          bigint REFERENCES products (id),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table users
(
    id         bigserial primary key,
    first_name varchar(80) not null,
    last_name  varchar(80) not null,
    username   varchar(30) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id     bigint not null references users (id),
    role_id     bigint not null references roles (id),
    primary key (user_id, role_id)
);

CREATE TABLE orders
(
    id              bigserial primary key,
    order_name      VARCHAR(255),
    total_price     INT,
    username        VARCHAR(255) references users(username),
    address         VARCHAR(255),
    phone           VARCHAR(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

CREATE TABLE order_items
(
    id                  bigserial primary key,
    quantity            INT,
    price_per_product   INT,
    price               INT,
    order_id            bigint REFERENCES orders (id),
    product_id          bigint REFERENCES products (id),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, first_name, last_name, password, email)
values ('user', 'Bob', 'Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', 'John', 'Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);