insert into buyers (business_id, first_name, last_name, street, city, postal_code, country)
values ('3c08ba35-2e08-4fff-a893-868cdb66deef', 'Maciej', 'Wajcht', 'Baraniaka 6', 'Poznań', '61-366', 'PL');

insert into products (business_id, name, price)
values ('d09b91b2-db8e-4161-85bd-00aae5a33244', 'Laptop', 7999.99);
insert into products (business_id, name, price)
values ('14b9094b-4192-4ffc-86f8-d405233f7811', 'Display', 2000.00);
insert into products (business_id, name, price)
values ('e2efaea6-1d57-42ac-ad7b-e25d81c05fc7', 'Phone', 2500.49);

insert into orders (business_id, create_time, buyer_id)
values ('04e5c529-2e1c-43b5-97b4-c509d4dfa5ed', current_timestamp, 1);

insert into orders_products (order_id, product_id)
values (1, 1);
insert into orders_products (order_id, product_id)
values (1, 2);
