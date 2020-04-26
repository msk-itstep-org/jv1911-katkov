create table providers (
	  id int unsigned unique not null auto_increment primary key,
	  name varchar(40),
    delivery_cost int not null
);

# create table storage (
# 	  id int unsigned unique not null auto_increment primary key,
#     receipt_date timestamp,
#     quantity double,
#     price_for_kilo int not null,
#     providers_id int unsigned not null references providers (id)
# );

create table ingredients_storage (
    id int unsigned unique not null auto_increment primary key,
	  ingredients_id int unsigned not null references ingredients (id),
#     storage_id int unsigned not null references storage (id),
    receipt_date date,
    quantity double,
    price_for_kilo int not null
#     primary key (ingredients_id, storage_id)
);

# insert into providers
#   (id, name, delivery_cost)
# values
#   (1, 'Магазин_1', 300),
#   (2, 'Магазин_2', 500);

insert into ingredients_storage
  (id, ingredients_id, receipt_date, quantity, price_for_kilo)
values
  (1, 1, '20/12/14', 1000, 50),
  (2, 2, '20/12/14', 1000, 50),
  (3, 3, '20/12/14', 1000, 50),
  (4, 4, '20/12/14', 1000, 50),
  (5, 5, '20/12/14', 1000, 50),
  (6, 6, '20/12/14', 1000, 50),
  (7, 7, '20/12/14', 1000, 50),
  (8, 8, '20/12/14', 1000, 50),
  (9, 9, '20/12/14', 1000, 50),
  (10, 10, '20/12/14', 1000, 50),
  (11, 11, '20/12/14', 1000, 50),
  (12, 12, '20/12/14', 1000, 50),
  (13, 13, '20/12/14', 100, 50),
  (14, 14, '20/12/14', 100, 50),
  (15, 15, '20/12/14', 100, 50),
  (16, 16, '20/12/14', 100, 50),
  (17, 17, '20/12/14', 100, 50),
  (18, 18, '20/12/14', 100, 50),
  (19, 19, '20/12/14', 100, 50),
  (20, 20, '20/12/14', 100, 50),
  (21, 21, '20/12/14', 100, 50),
  (22, 22, '20/12/14', 100, 50),
  (23, 23, '20/12/14', 100, 50),
  (24, 24, '20/12/14', 100, 50),
  (25, 25, '20/12/14', 100, 50),
  (26, 26, '20/12/14', 100, 50),
  (27, 27, '20/12/14', 100, 50),
  (28, 28, '20/12/14', 100, 50),
  (29, 29, '20/12/14', 100, 50),
  (30, 30, '20/12/14', 100, 50),
  (31, 31, '20/12/14', 100, 50),
  (32, 32, '20/12/14', 100, 50),
  (33, 33, '20/12/14', 100, 50),
  (34, 34, '20/12/14', 100, 50),
  (35, 35, '20/12/14', 100, 50),
  (36, 36, '20/12/14', 100, 50),
  (37, 37, '20/12/14', 100, 50),
  (38, 38, '20/12/14', 100, 50),
  (39, 39, '20/12/14', 100, 50),
  (40, 40, '20/12/14', 100, 50),
  (41, 41, '20/12/14', 500, 50),
  (42, 42, '20/12/14', 24, 50),
  (43, 43, '20/12/14', 12, 50),
  (44, 44, '20/12/14', 10, 50);
#
# insert into ingredients_storage
#   (ingredients_id, storage_id)
# values
#   (1, 1),
#   (2, 2),
#   (3, 3),
#   (4, 4),
#   (5, 5),
#   (6, 6),
#   (7, 7),
#   (8, 8),
#   (9, 9),
#   (10, 10),
#   (11, 11),
#   (12, 12),
#   (13, 13),
#   (14, 14),
#   (15, 15),
#   (16, 16),
#   (17, 17),
#   (18, 18),
#   (19, 19),
#   (20, 20),
#   (21, 21),
#   (22, 22),
#   (23, 23),
#   (24, 24),
#   (25, 25),
#   (26, 26),
#   (27, 27),
#   (28, 28),
#   (29, 29),
#   (30, 30),
#   (31, 31),
#   (32, 32),
#   (33, 33),
#   (34, 34),
#   (35, 35),
#   (36, 36),
#   (37, 37),
#   (38, 38),
#   (39, 39),
#   (40, 40),
#   (41, 41),
#   (42, 42),
#   (43, 43),
#   (44, 44);