create table orders (
	  id int unsigned unique not null auto_increment primary key,
	  user_id int unsigned not null references users(id),
	  active bit default true,
	  order_date timestamp not null
);

create table orders_dishes (
     id int unsigned unique not null auto_increment primary key,
     orders_id int unsigned not null references orders (id),
     dishes_id int unsigned not null references dishes (id),
     quantity int not null
);
