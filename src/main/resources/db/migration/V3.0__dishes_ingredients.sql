create table dishes (
    id int unsigned unique not null auto_increment primary key,
    name varchar(100) not null unique,
    cost int not null,
    description varchar(255) null,
    active bit default true,
    menu_id int not null,
    photo_id int null references uploads (id)
);

create table ingredients (
    id int unsigned unique not null auto_increment primary key,
    active bit default true,
    name varchar(100)
);

create table dishes_ingredients (
    id int unsigned unique not null auto_increment primary key,
    dishes_id int unsigned not null references dishes (id),
    ingredients_id int unsigned not null references ingredients (id),
    weight int not null
);

create table uploads (
     id int unsigned not null auto_increment primary key,
     filename varchar(255) not null,
     original_filename varchar(255) not null,
     content_type varchar(255) not null
);

insert into dishes
(`id`, `name`, `cost`, `description`, `menu_id`, photo_id)
values
(1, 'Бекон с сыром в духовке', '250', 'Пальчики оближешь', '6', 5),
(2, 'Жульен с курицей в тарталетках', '230', 'Самое то!', '6', 6),
(3, 'Закуска из баклажан пикантная', '200', 'Осторожно, осторое', '6', 7),
(4, 'Креветки в кляре', '270', 'Такие только у нас', '6', 8),
(5, 'Свиные ребрышки на сковороде', '300', 'Блаженство', '6', 9),
(6, 'Сырные палочки', '150', 'Идеальная закуска к пиву', '6', 10);

insert into dishes
(`id`, `name`, `cost`, `description`, `menu_id`, photo_id)
values
(7, 'Буйабес', '320', 'Суп с морепродуктами', '7', 1),
(8, 'Свекольник с мясом', '230', 'Настоящий мясной суп', '7', 2),
(9, 'Сырный суп пюре с курицей', '250', 'Лучшее сочетание', '7', 3),
(10, 'Турецкий суп с булгуром', '300', 'Такого Вы еще не пробовали', '7', 4);

insert into uploads
(id, filename, original_filename, content_type)
values
(1, 'Буйабес.jpg', 'Буйабес.jpg', 'image/jpeg'),
(2, 'Свекольник с мясом.jpg', 'Свекольник с мясом.jpg', 'image/jpeg'),
(3, 'Сырный суп пюре с курицей.jpg', 'Сырный суп пюре с курицей.jpg', 'image/jpeg'),
(4, 'Турецкий суп с булгуром.jpg', 'Турецкий суп с булгуром.jpg', 'image/jpeg'),
(5, 'Бекон с сыром в духовке.jpg', 'Бекон с сыром в духовке.jpg', 'image/jpeg'),
(6, 'Жульен с курицей в тарталетках.jpg', 'Жульен с курицей в тарталетках.jpg', 'image/jpeg'),
(7, 'Закуска из баклажан пикантная.jpg', 'Закуска из баклажан пикантная.jpg', 'image/jpeg'),
(8, 'Креветки в кляре.jpg', 'Креветки в кляре.jpg', 'image/jpeg'),
(9, 'Свиные ребрышки на сковороде.jpg', 'Свиные ребрышки на сковороде.jpg', 'image/jpeg'),
(10, 'Сырные палочки.jpg', 'Сырные палочки.jpg', 'image/jpeg');

insert into ingredients
(`id`, `name`)
values
(1, 'Рыба красная'),
(2, 'Мидии'),
(3, 'Кальмары'),
(4, 'Креветки'),
(5, 'Лук'),
(6, 'Сельдерей'),
(7, 'Шпинат'),
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
(27, 'Кокос'),
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

