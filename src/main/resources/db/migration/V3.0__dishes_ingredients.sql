CREATE TABLE dishes (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100) not null unique,
    photo varchar(100) null,
    cost int not null,
    description varchar(255) null,
    menu_id int not null
);

CREATE TABLE ingredients (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100)
);

CREATE TABLE weight_ingredients (
    weight_id int not null references dishes (id),
    ingredient_id int not null references ingredients (id),
    primary key (ingredient_id, weight_id)
);


