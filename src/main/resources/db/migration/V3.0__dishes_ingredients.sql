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
(`id`, `name`, `photo`, `cost`, `description`, `menu_id`)
values
(1, 'Бекон с сыром в духовке', '/images/upload/dishesMain/hotSnakes/Бекон с сыром в духовке.jpg', '250', 'Яго-го', '6'),
(2, 'Жульен с курицей в тарталетках', '/images/upload/dishesMain/hotSnakes/Жульен с курицей в тарталетках.jpg', '230', 'Вово', '6'),
(3, 'Закуска из баклажан пикантная', '/images/upload/dishesMain/hotSnakes/Закуска из баклажан пикантная.jpg', '200', 'Бакл', '6'),
(4, 'Креветки в кляре', '/images/upload/dishesMain/hotSnakes/Креветки в кляре.jpg', '270', 'Вакл', '6'),
(5, 'Свиные ребрышки на сковороде', '/images/upload/dishesMain/hotSnakes/Свиные ребрышки на сковороде.jpg', '300', 'Закл', '6'),
(6, 'Сырные палочки', '/images/upload/dishesMain/hotSnakes/Сырные палочки.jpg', '150', 'Дакл', '6');

insert into dishes
(`id`, `name`, `photo`, `cost`, `description`, `menu_id`)
values
(7, 'Буйабес', '/images/upload/dishesMain/soups/Буйабес.jpg', '320', 'Супец с креветкой', '7'),
(8, 'Свекольник с мясом', '/images/upload/dishesMain/soups/Свекольник с мясом.jpg', '230', 'Мясцо', '7'),
(9, 'Сырный суп пюре с курицей', '/images/upload/dishesMain/soups/Сырный суп пюре с курицей.jpg', '250', 'Куре', '7'),
(10, 'Турецкий суп с булгуром', '/images/upload/dishesMain/soups/Турецкий суп с булгуром.jpg', '300', 'Ваще', '7');

insert into ingredients
(`id`, `name`)
values
(1, 'Рыба красная'),
(2, 'Мидии'),
(3, 'Кальмары'),
(4, 'Креветки'),
(5, 'Лук'),
(6, 'Сельдерей'),
(8, 'Специи сухие'),
(9, 'Масло растительное'),
(10, 'Сыр плавленный'),
(11, 'Рис'),
(12, 'Картофель'),
(13, 'Курица'),
(14, 'Перец черный молотый'),
(15, 'Зелень'),
(16, 'Свинина'),
(17, 'Свекла'),
(18, 'Морковь'),
(19, 'Перец болгарский'),
(20, 'Соус томатный'),
(21, 'Чеснок'),
(22, 'Кислота лимонная'),
(23, 'Лист лавровый'),
(24, 'Булгур'),
(25, 'Чечевица красная'),
(26, 'Помидоры'),
(28, 'Лук зеленый'),
(29, 'Яйца'),
(30, 'Мука пшеничная'),
(31, 'Крахмал кукурузный'),
(32, 'Вино красное'),
(33, 'Баклажаны'),
(34, 'Сыр твердый'),
(35, 'Лаваш тонкий'),
(36, 'Сухари панировочные'),
(37, 'Бекон'),
(38, 'Брынза'),
(39, 'Пармезан'),
(40, 'Филе куриное'),
(41, 'Тартолетки'),
(42, 'Сметана'),
(43, 'Ребрышки свиные'),
(44, 'Майонез');

insert into dishes_ingredients
(`dishes_id`, `ingredients_id`, `weight`)
values
(7, 1, 75),
(7, 2, 38),
(7, 3, 25),
(7, 4, 25),
(7, 5, 10),
(7, 6, 25),
(7, 8, 3),
(7, 9, 3),
(8, 10, 35),
(8, 11, 13),
(8, 12, 35),
(8, 13, 42),
(8, 18, 10),
(8, 5, 10),
(8, 14, 3),
(8, 15, 5),
(9, 16, 63),
(9, 17, 63),
(9, 18, 18),
(9, 5, 18),
(9, 12, 75),
(9, 19, 13),
(9, 20, 3),
(9, 21, 2),
(9, 9, 5),
(9, 23, 1),
(9, 22, 1),
(9, 15, 1),
(10, 24, 30),
(10, 25, 30),
(10, 26, 40),
(10, 5, 28),
(10, 9, 12);

