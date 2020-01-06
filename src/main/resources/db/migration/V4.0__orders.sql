CREATE TABLE orders (
	  id int unsigned unique not null auto_increment primary key,
    orderDate timestamp
);

CREATE TABLE orders_dishes (
    orders_id int not null references orders (id),
    dishes_id int not null references dishes (id),
    primary key (orders_id, dishes_id)
);