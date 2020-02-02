create table providers (
	  id int unsigned unique not null auto_increment primary key,
	  name varchar(40),
    delivery_cost int not null
);

create table storage (
	  id int unsigned unique not null auto_increment primary key,
    receipt_date timestamp,
    quantity double,
    price_for_kilo int not null,
    providers_id int unsigned not null references providers (id)
);

create table ingredients_storage (
	  ingredients_id int unsigned not null references ingredients (id),
    storage_id int unsigned not null references storage (id),
    primary key (ingredients_id, storage_id)
);