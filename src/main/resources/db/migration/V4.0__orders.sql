create table orders (
	  id int unsigned unique not null auto_increment primary key,
	  waiter_name varchar(100) null
);

create table orders_dishes (
     id int unsigned unique not null auto_increment primary key,
     orders_id int not null references orders (id),
     dishes_id int not null references dishes (id),
     quantity int null
);
