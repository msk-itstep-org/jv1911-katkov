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

insert into dishes
(`name`, `photo`, `cost`, `description`, `menu_id`)
values
('Бекон с сыром в духовке', '/images/upload/dishesMain/hotSnakes/Бекон с сыром в духовке.jpg', '250', 'Яго-го', '9'),
('Жульен с курицей в тарталетках', '/images/upload/dishesMain/hotSnakes/Жульен с курицей в тарталетках.jpg', '230', 'Вово', '9'),
('Закуска из баклажан пикантная', '/images/upload/dishesMain/hotSnakes/Закуска из баклажан пикантная.jpg', '200', 'Бакл', '9'),
('Креветки в кляре', '/images/upload/dishesMain/hotSnakes/Креветки в кляре.jpg', '270', 'Вакл', '9'),
('Свиные ребрышки на сковороде', '/images/upload/dishesMain/hotSnakes/Свиные ребрышки на сковороде.jpg', '300', 'Закл', '9'),
('Сырные палочки', '/images/upload/dishesMain/hotSnakes/Сырные палочки.jpg', '150', 'Дакл', '9');


