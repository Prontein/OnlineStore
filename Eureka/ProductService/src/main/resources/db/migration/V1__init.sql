create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int
);

insert into products (title, price) values ('Bread', 25), ('Milk', 80), ('Cheese', 450), ('Oil', 560), ('Potato', 95);
insert into products (title, price) values ('Onion', 34), ('Garlic', 40), ('Pepper', 85), ('Carrot', 120), ('Cucumber', 95);
insert into products (title, price) values ('Meat', 600), ('Spices', 225), ('Water', 91), ('Apple', 65), ('Orange', 75);
insert into products (title, price) values ('Juice', 165), ('Lemon', 68), ('Corn', 195), ('Tomato', 130), ('Cabbage', 170);
