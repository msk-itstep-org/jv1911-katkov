CREATE TABLE menu (
	  id int unsigned unique not null auto_increment primary key,
    name varchar(50) not null,
    parent_id int unsigned references menu (id),
    image varchar(255) not null,
    path varchar(255) null
);

INSERT INTO restaurant.menu (id, name, image) VALUES ('1', 'main_menu','/images/upload/mainMenu.jpg');
INSERT INTO restaurant.menu (id, name, image) VALUES ('2', 'bar_menu', '/images/upload/barMenu.jpg');
INSERT INTO restaurant.menu (id, name, image) VALUES ('3', 'grill_menu', '/images/upload/grillMenu.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image, path) VALUES ('4', 'salads', '1', '/images/upload/salads.jpg', '/salads');
INSERT INTO restaurant.menu (id, name, parent_id, image, path) VALUES ('5', 'cold_snacks', '1', '/images/upload/coldSnacks.jpg', '/coldSnacks');
INSERT INTO restaurant.menu (id, name, parent_id, image, path) VALUES ('6', 'hot_snacks', '1', '/images/upload/hotSnacks.jpg', '/hotSnacks');
INSERT INTO restaurant.menu (id, name, parent_id, image, path) VALUES ('7', 'soups', '1', '/images/upload/soups.jpg', '/soups');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('8', 'sausages', '1', '/images/upload/sausages.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('9', 'hot_meals', '1', '/images/upload/hotMeals.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('10', 'side_dishes', '1', '/images/upload/sideDishes.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('11', 'sauses', '1', '/images/upload/sauses.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('12', 'deserts', '1', '/images/upload/deserts.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('13', 'hot_drinks', '1', '/images/upload/hotDrinks.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('14', 'tea', '13', '/images/upload/tea.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('15', 'coffee', '13', '/images/upload/coffee.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('16', 'cold_drinks', '1', '/images/upload/coldDrinks.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('17', 'beer', '2', '/images/upload/beer.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('18', 'vodka', '2', '/images/upload/vodka.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('19', 'rum', '2', '/images/upload/rum.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('20', 'gin', '2', '/images/upload/gin.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('21', 'cognac', '2', '/images/upload/cognac.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('22', 'tequila', '2', '/images/upload/tequila.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('23', 'barbecue', '3', '/images/upload/barbecue.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('24', 'kebabs', '3', '/images/upload/kebabs.jpg');
INSERT INTO restaurant.menu (id, name, parent_id, image) VALUES ('25', 'steaks', '3', '/images/upload/steaks.jpg');

-- добавил path