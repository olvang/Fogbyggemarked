USE FogByggemarked;
set foreign_key_checks=0;
TRUNCATE TABLE bill;
TRUNCATE TABLE category;
TRUNCATE TABLE material_to_category;
TRUNCATE TABLE materials;
TRUNCATE TABLE orders;
TRUNCATE TABLE sheds;
TRUNCATE TABLE variant;
set foreign_key_checks=1;
SET SQL_MODE =  'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('540', '25', '200', '25x200 mm. trykimp. Brædt',20);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('360', '25', '125', '25x125mm. trykimp. Brædt',30);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('600', '45', '195', '45x195 mm. spærtræ ubh.',40);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('420', '38', '73', '38x73 mm. Lægte ubh.',50);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('270', '45', '95', '45x95 mm. Reglar ub.',60);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('300', '97', '97', '97x97 mm. trykimp. Stolpe',21);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('210', '19', '100', '19x100 mm. trykimp. Brædt',31);
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `name`,`price`) VALUES ('600', '109', 'Plastmo Ecolite blåtonet(600 x 109)',61);
INSERT INTO `FogByggemarked`.`materials` (`length`, `amount`, `name`,`price`) VALUES ('0', '200', 'plastmo bundskruer 200 stk.',81);
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`) VALUES ('hulbånd 1x20 mm. 10 mtr.',91);
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`) VALUES ('universal 190 mm højre',12);
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`) VALUES ('universal 190 mm venstre',22);
INSERT INTO `FogByggemarked`.`materials` (`amount`, `name`,`price`) VALUES ('200', '4,5 x 60 mm. skruer 200 stk.',32);
INSERT INTO `FogByggemarked`.`materials` (`amount`, `name`,`price`) VALUES ('250', '4,0 x 50 mm. beslagskruer 250 stk.',42);
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `name`,`price`) VALUES ('120', '10', 'bræddebolt 10 x 120 mm.',52);
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`) VALUES ('40', '11', '40', 'firkantskiver 40x40x11mm',62);
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `amount`, `name`,`price`) VALUES ('70', '5', '400', '4.5 x 70 mm. Skruer 400 stk.',72);
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `name`,`price`) VALUES ('50', '5', '4,5 x 50 mm. Skruer 300 stk.',82);
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`) VALUES ('stalddørsgreb 50x75',92);
INSERT INTO `FogByggemarked`.`materials` (`length`, `name`,`price`) VALUES ('390', 't hængsel 390 mm',13);
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`) VALUES ('vinkelbeslag 35',23);
INSERT INTO `FogByggemarked`.`orders` VALUES (1,300,300,300,0);
INSERT INTO `FogByggemarked`.`orders` VALUES (2,400,400,400,0);
INSERT INTO `FogByggemarked`.`orders` VALUES (3,500,500,500,0);
INSERT INTO `FogByggemarked`.`orders` VALUES (4,600,600,600,0);
INSERT INTO `FogByggemarked`.`orders` VALUES (5,300,300,300,10);
INSERT INTO `FogByggemarked`.`orders` VALUES (6,400,400,400,15);
INSERT INTO `FogByggemarked`.`orders` VALUES (7,500,500,500,20);
INSERT INTO `FogByggemarked`.`orders` VALUES (8,600,600,600,25);
INSERT INTO `FogByggemarked`.`orders` VALUES (9,200,200,200,0);
INSERT INTO `FogByggemarked`.`sheds` VALUES (1,5,50,50);
INSERT INTO `FogByggemarked`.`sheds` VALUES (2,6,60,60);
INSERT INTO `FogByggemarked`.`sheds` VALUES (3,7,70,70);
INSERT INTO `FogByggemarked`.`sheds` VALUES (4,8,80,80);
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('understernbrædder til for & bag ende');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('understernbrædder til siderne');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('oversternbrædder til forenden');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('oversternbrædder til siderne');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til z på bagside af dør');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('løsholter til skur gavle');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('løsholter til skur sider');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Remme i sider, sadles ned i stolpe');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Remme i sider, sadles ned i stolper (skur del, deles)');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Spær, monteres på rem');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Stolper nedgraves 90 cm. i jord');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til beklædning af skur 1 på 2');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('vandbrædt på stern i sider');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('vandbrædt på stern i forende');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('tagplader monteres på spær');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Skruer til tagplader');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til vindkryds på spær');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af spær på rem - højre');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af spær på rem - venstre');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af stern&vandbrædt');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af universalbeslag + hulbånd');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af rem på stolper - bolte');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af rem på stolper - skiver');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til montering af yderste beklædning');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til montering af inderste beklædning');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til lås på dør i skur');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til skurdør');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af løsholter i skur');
INSERT INTO `FogByggemarked`.`variant` (variant_name,category_id) VALUES ('Plastmo Ecolite Blaaligt', 15);
INSERT INTO `FogByggemarked`.`variant`(variant_name,category_id) VALUES ('Plastmo Ecolite Groonligt', 15);
INSERT INTO `FogByggemarked`.`variant` (variant_name,category_id)VALUES ('Plastmo Ecolite Gulligt', 15);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (1,1);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (1,2);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (2,3);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (2,4);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (3,5);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (4,6);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (4,7);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (5,8);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (5,9);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (5,10);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (6,11);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (7,12);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (7,13);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (7,14);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (8,15);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (9,16);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (10,17);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (11,18);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (12,19);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (13,20);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (14,21);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (15,22);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (16,23);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (17,24);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (21,25);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (18,26);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (19,27);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (20,28);