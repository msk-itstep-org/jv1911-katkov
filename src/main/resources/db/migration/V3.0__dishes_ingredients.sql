create table dishes (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100) not null unique,
    photo varchar(100) null,
    cost int not null,
    description varchar(255) null,
    menu_id int not null
);

create table ingredients (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100)
);

create table dishes_ingredients (
    id int unsigned unique not null auto_increment primary key,
    dishes_id int unsigned not null references dishes (id),
    ingredients_id int unsigned not null references ingredients (id),
    weight int not null
);


