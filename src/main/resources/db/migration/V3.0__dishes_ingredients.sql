CREATE TABLE dishes (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100) not null unique,
    photo varchar(100) null,
    cost int not null,
    description varchar(255) null,
    weight int unsigned not null,
    menu_id int not null
);

CREATE TABLE ingredients (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100)
);

CREATE TABLE weight_ingredients (
    weight_id int not null references dishes (weightSet),
    ingredient_id int not null references ingredients (id),
    primary key (ingredient_id, weight_id)
);

# INSERT INTO users (id, username, password) VALUES ('1', 'waiter','12345');
# INSERT INTO users (id, username, password) VALUES ('2', 'admin','admin');
# INSERT INTO users (id, username, password) VALUES ('3', 'chief','54321');
# INSERT INTO users (id, username, password) VALUES ('4', 'manager','09876');
#
# INSERT INTO roles (role) VALUES ('ROLE_WAITER');
# INSERT INTO roles (role) VALUES ('ROLE_CHIEF');
# INSERT INTO roles (role) VALUES ('ROLE_MANAGER');
# INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
