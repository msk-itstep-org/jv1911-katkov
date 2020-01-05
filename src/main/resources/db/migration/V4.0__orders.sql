CREATE TABLE orders (
	  id int unsigned unique not null auto_increment primary key,
    orderDate date,
    dishes_id int unsigned null references dishes (id)
);