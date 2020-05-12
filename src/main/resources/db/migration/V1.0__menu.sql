create table menu (
	  id INT unsigned unique not null auto_increment primary key,
    name varchar(50) not null,
    parent_id int unsigned references menu (id),
    image varchar(255) not null
);

INSERT INTO menu (id, name, image) VALUES ('1', 'Основное меню','/images/upload/mainMenu.jpg');
INSERT INTO menu (id, name, image) VALUES ('2', 'Барное меню', '/images/upload/barMenu.jpg');
INSERT INTO menu (id, name, image) VALUES ('3', 'Гриль меню', '/images/upload/grillMenu.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('4', 'Салаты', '1', '/images/upload/salads.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('5', 'Холодные закуски', '1', '/images/upload/coldSnacks.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('6', 'Горячие закуски', '1', '/images/upload/hotSnacks.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('7', 'Супы', '1', '/images/upload/soups.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('8', 'Сосиски', '1', '/images/upload/sausages.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('9', 'Горячие блюда', '1', '/images/upload/hotMeals.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('10', 'Гарниры', '1', '/images/upload/sideDishes.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('11', 'Соусы', '1', '/images/upload/sauses.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('12', 'Десерты', '1', '/images/upload/deserts.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('13', 'Горячие напитки', '1', '/images/upload/hotDrinks.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('14', 'Чай', '13', '/images/upload/tea.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('15', 'Кофе', '13', '/images/upload/coffee.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('16', 'Холодные напитки', '1', '/images/upload/coldDrinks.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('17', 'Пиво', '2', '/images/upload/beer.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('18', 'Водка', '2', '/images/upload/vodka.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('19', 'Ром', '2', '/images/upload/rum.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('20', 'Джин', '2', '/images/upload/gin.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('21', 'Коньяк', '2', '/images/upload/cognac.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('22', 'Текила', '2', '/images/upload/tequila.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('23', 'Барбекю', '3', '/images/upload/barbecue.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('24', 'Кебабы', '3', '/images/upload/kebabs.jpg');
INSERT INTO menu (id, name, parent_id, image) VALUES ('25', 'Стейки', '3', '/images/upload/steaks.jpg');

-- добавил path
-- INSERT INTO menu (id, name, image) VALUES ('1', 'main_menu','/images/upload/mainMenu.jpg');
-- INSERT INTO menu (id, name, image) VALUES ('2', 'bar_menu', '/images/upload/barMenu.jpg');
-- INSERT INTO menu (id, name, image) VALUES ('3', 'grill_menu', '/images/upload/grillMenu.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('4', 'salads', '1', '/images/upload/salads.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('5', 'cold_snacks', '1', '/images/upload/coldSnacks.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('6', 'hot_snacks', '1', '/images/upload/hotSnacks.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('7', 'soups', '1', '/images/upload/soups.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('8', 'sausages', '1', '/images/upload/sausages.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('9', 'hot_meals', '1', '/images/upload/hotMeals.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('10', 'side_dishes', '1', '/images/upload/sideDishes.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('11', 'sauses', '1', '/images/upload/sauses.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('12', 'deserts', '1', '/images/upload/deserts.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('13', 'hot_drinks', '1', '/images/upload/hotDrinks.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('14', 'tea', '13', '/images/upload/tea.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('15', 'coffee', '13', '/images/upload/coffee.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('16', 'cold_drinks', '1', '/images/upload/coldDrinks.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('17', 'beer', '2', '/images/upload/beer.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('18', 'vodka', '2', '/images/upload/vodka.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('19', 'rum', '2', '/images/upload/rum.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('20', 'gin', '2', '/images/upload/gin.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('21', 'cognac', '2', '/images/upload/cognac.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('22', 'tequila', '2', '/images/upload/tequila.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('23', 'barbecue', '3', '/images/upload/barbecue.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('24', 'kebabs', '3', '/images/upload/kebabs.jpg');
-- INSERT INTO menu (id, name, parent_id, image) VALUES ('25', 'steaks', '3', '/images/upload/steaks.jpg');