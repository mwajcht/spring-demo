create table buyers
(
    id          int primary key generated by default as identity,
    business_id uuid UNIQUE,
    first_name  varchar(50),
    last_name   varchar(50),
    street      varchar(50),
    city        varchar(50),
    postal_code varchar(50),
    country     varchar(50)
);

create table orders
(
    id          int primary key generated by default as identity,
    business_id uuid UNIQUE,
    create_time timestamp,
    buyer_id    int references buyers
);

create table products
(
    id          int primary key generated by default as identity,
    business_id uuid UNIQUE,
    name        varchar(50),
    price       decimal(10, 2)
);

 create table orders_products
(
   order_id int references orders,
   product_id int references products
);
