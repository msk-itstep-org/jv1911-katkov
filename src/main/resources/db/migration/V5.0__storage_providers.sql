CREATE TABLE providers (
	  id int unsigned unique not null auto_increment primary key,
	  name varchar(40),
    delivery_cost double
);

CREATE TABLE storage (
	  id int unsigned unique not null auto_increment primary key,
    receipt_date timestamp,
    quantity double,
    price_for_kilo double,
    providers_id int unsigned not null references providers (id)
);

CREATE TABLE ingredients_storage (
	  ingredients_id int unsigned not null references ingredients (id),
    storage_id int unsigned not null references storage (id),
    primary key (ingredients_id, storage_id)
);