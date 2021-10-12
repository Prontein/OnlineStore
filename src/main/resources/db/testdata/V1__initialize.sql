
create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price) values ('Test_product_1', 25), ('Test_product_2', 80), ('Test_product_3', 450);
insert into products (title, price) values ('Test_product_4', 34), ('Test_product_5', 40), ('Test_product_6', 85);


create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into users (username, password, email)
values  ('Test_user_1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'email1'),
        ('Test_user_2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'email2'),
        ('Test_user_3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'email3');

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

CREATE TABLE users_roles
(
    user_id     bigint not null references users (id),
    role_id     bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 1),
       (3, 2);

CREATE TABLE orders
(
    id              bigserial primary key,
    order_name      VARCHAR(255),
    total_price     INT,
    user_id         bigint REFERENCES users (id),
    address         VARCHAR(255),
    phone           VARCHAR(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into orders (order_name, total_price, user_id, address, phone)

values  ('Test_order_1', 105, 1L, 'address_test1', 'phone_test1'),
        ('Test_order_2', 530, 2L, 'address_test2', 'phone_test2'),
        ('Test_order_3', 484, 3L, 'address_test3', 'phone_test3');

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

insert into order_items (quantity, price_per_product, price, order_id, product_id)
values  (1, 25, 25, 1L, 1L),
        (1, 80, 80, 1L, 2L),
        (1, 80, 80, 2L, 2L),
        (1, 450, 450, 2L, 3L),
        (1, 450, 450, 3L, 3L),
        (1, 34, 34, 3L, 4L);


