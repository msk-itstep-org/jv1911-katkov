CREATE TABLE users (
    id int unsigned unique not null auto_increment primary key,
    username varchar(100) not null unique,
    password varchar(10) not null unique,
    unique index (username)
);

CREATE TABLE roles (
    id int unsigned unique not null auto_increment primary key,
    role varchar(50),
    unique index (role)
);

CREATE TABLE users_roles (
    user_id int not null references users (id),
    role_id int not null references roles (id),
    primary key (user_id, role_id)
);

INSERT INTO users (id, username, password) VALUES ('1', 'waiter','12345');
INSERT INTO users (id, username, password) VALUES ('2', 'admin','admin');
INSERT INTO users (id, username, password) VALUES ('3', 'chief','54321');
INSERT INTO users (id, username, password) VALUES ('4', 'manager','09876');

INSERT INTO roles (role) VALUES ('ROLE_WAITER');
INSERT INTO roles (role) VALUES ('ROLE_CHIEF');
INSERT INTO roles (role) VALUES ('ROLE_MANAGER');
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
