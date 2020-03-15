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
    storage_id int unsigned not null references storage (id),
    receipt_date timestamp,
    quantity double,
    price_for_kilo int not null
#     primary key (ingredients_id, storage_id)
);

# insert into providers
#   (id, name, delivery_cost)
# values
#   (1, 'Магазин_1', 300),
#   (2, 'Магазин_2', 500);

# insert into ingredients_storage
#   (id, receipt_date, quantity, price_for_kilo, providers_id)
# values
#   (1, '20/12/14', 1, 50, 1),
#   (2, '20/12/14', 1, 50, 1),
#   (3, '20/12/14', 100, 50, 1),
#   (4, '20/12/14', 100, 50, 1),
#   (5, '20/12/14', 100, 50, 1),
#   (6, '20/12/14', 100, 50, 1),
#   (7, '20/12/14', 100, 50, 1),
#   (8, '20/12/14', 100, 50, 1),
#   (9, '20/12/14', 100, 50, 1),
#   (10, '20/12/14', 100, 50, 1),
#   (11, '20/12/14', 100, 50, 1),
#   (12, '20/12/14', 100, 50, 1),
#   (13, '20/12/14', 100, 50, 1),
#   (14, '20/12/14', 100, 50, 1),
#   (15, '20/12/14', 100, 50, 1),
#   (16, '20/12/14', 100, 50, 1),
#   (17, '20/12/14', 100, 50, 1),
#   (18, '20/12/14', 100, 50, 1),
#   (19, '20/12/14', 100, 50, 1),
#   (20, '20/12/14', 100, 50, 1),
#   (21, '20/12/14', 100, 50, 1),
#   (22, '20/12/14', 100, 50, 1),
#   (23, '20/12/14', 100, 50, 2),
#   (24, '20/12/14', 100, 50, 2),
#   (25, '20/12/14', 100, 50, 2),
#   (26, '20/12/14', 100, 50, 1),
#   (27, '20/12/14', 100, 50, 1),
#   (28, '20/12/14', 100, 50, 1),
#   (29, '20/12/14', 100, 50, 2),
#   (30, '20/12/14', 100, 50, 2),
#   (31, '20/12/14', 100, 50, 2),
#   (32, '20/12/14', 100, 50, 2),
#   (33, '20/12/14', 100, 50, 2),
#   (34, '20/12/14', 100, 50, 2),
#   (35, '20/12/14', 100, 50, 2),
#   (36, '20/12/14', 100, 50, 2),
#   (37, '20/12/14', 100, 50, 2),
#   (38, '20/12/14', 100, 50, 2),
#   (39, '20/12/14', 100, 50, 2),
#   (40, '20/12/14', 100, 50, 2),
#   (41, '20/12/14', 500, 50, 2),
#   (42, '20/12/14', 24, 50, 2),
#   (43, '20/12/14', 12, 50, 2),
#   (44, '20/12/14', 10, 50, 2);
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