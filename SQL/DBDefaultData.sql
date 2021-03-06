USE FogByggemarked;
set foreign_key_checks=0;
TRUNCATE TABLE bill;
TRUNCATE TABLE category;
TRUNCATE TABLE material_to_category;
TRUNCATE TABLE materials;
TRUNCATE TABLE variant;
set foreign_key_checks=1;
SET SQL_MODE =  'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('3600', '25', '200', 'trykimp. Brædt',20, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('5400', '25', '200', 'trykimp. Brædt',25, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('3600', '25', '125', 'trykimp. Brædt',20, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('5400', '25', '125', 'trykimp. Brædt',25, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('4200', '38', '73', 'Lægte ubh.',50, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('2700', '45', '95', 'Reglar ub.',60, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('2400', '45', '95', 'Reglar ub.',60, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('6000', '45', '195', 'spærtræ ubh.',40, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('4800', '45', '195', 'spærtræ ubh.',35, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('3000', '97', '97', 'trykimp. Stolpe',21, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('2100', '19', '100', 'trykimp. Brædt',15, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('5400', '19', '100', 'trykimp. Brædt',20, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('3600', '19', '100', 'trykimp. Brædt',18, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `name`,`price`,`unit`) VALUES ('6000', '109', 'Plastmo Ecolite blåtonet',61, 'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `name`,`price`,`unit`) VALUES ('3600', '109', 'Plastmo Ecolite blåtonet',61, 'Stk');

INSERT INTO `FogByggemarked`.`materials` (`length`, `amount`, `name`,`price`,`unit`) VALUES ('0', '200', 'plastmo bundskruer 200 stk.',81,'Pakke');
INSERT INTO `FogByggemarked`.`materials` (`length`, `name`,`price`,`unit`) VALUES ('10000','hulbånd 1x20 mm. 10 mtr.',91,'Rulle');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('universal 190 mm højre',12,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('universal 190 mm venstre',22,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`amount`, `name`,`price`,`unit`) VALUES ('200', '4,5 x 60 mm. skruer 200 stk.',32,'Pakke');
INSERT INTO `FogByggemarked`.`materials` (`amount`, `name`,`price`,`unit`) VALUES ('250', '4,0 x 50 mm. beslagskruer 250 stk.',42,'Pakke');
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `name`,`price`,`unit`) VALUES ('1200', '10', 'bræddebolt.',52,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('400', '11', '40', 'firkantskiver',62,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `amount`, `name`,`price`,`unit`) VALUES ('700', '5', '400', 'Skruer 400 stk.',72,'Pakke');
INSERT INTO `FogByggemarked`.`materials` (`length`, `width`, `amount`,`name`,`price`,`unit`) VALUES ('500', '5', '300', 'Skruer 300 stk.',82,'Pakke');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('stalddørsgreb 50x75',92,'Sæt');
INSERT INTO `FogByggemarked`.`materials` (`length`, `name`,`price`,`unit`) VALUES ('3900', 't hængsel',13,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`, `width`,`unit`) VALUES ('vinkelbeslag',23,'35','Stk');

INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('4800', '25', '150', '25x150 mm. trykimp. Bræt',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('6000', '25', '150', '25x150 mm. trykimp. Bræt',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('2400', '19', '100', '19x100 mm. trykimp. Bræt',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('5400', '25', '50', '25x50 mm. trykimp. Bræt',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('5400', '38', '73', '38x73 mm. taglægte T1',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`length`, `height`, `width`, `name`,`price`,`unit`) VALUES ('4200', '38', '73', '38x73 mm. taglægte T1',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('B & C Dobbelt -s sort',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('B & C Rygsten sort',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('B & C Toplægte holder',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`unit`) VALUES ('B & C rygstensbeslag',18,'Stk');
INSERT INTO `FogByggemarked`.`materials` (`name`,`price`,`amount`,`unit`) VALUES ('B & C tagstens bindere & nakkekroge', 20,500,'Pakke');
INSERT INTO `FogByggemarked`.`materials` (`amount`,`name`,`price`,`unit`) VALUES ('100', '5,0 x 100 mm. skruer 100 stk.', 30,'Pakke');

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
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til lås på dør i sku r');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til skurdør');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af løsholter i skur');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Vindskeder på rejsning');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('beklædning af gavle 1 på 2');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til montering oven på tagfodslægte');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('toplægte til montering af rygsten lægges i toplægte holder');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('monteres på taglægter 6 rækker af 24 sten på hver side af taget');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('monteres på toplægte med medfølgende beslag se tagstens vejledning');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('monteres på toppen af spæret (til toplægte)');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af rygsten');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til montering af tagsten, alle ydersten + hver anden fastgøres');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af Stern, vindskeder, vindkryds & vand bræt');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('Til montering af universalbeslag + toplægte');
INSERT INTO `FogByggemarked`.`category` (decription) VALUES ('til taglægter');

INSERT INTO `FogByggemarked`.`variant` (variant_name,category_id) VALUES ('Plastmo Ecolite Blaaligt', 15);
INSERT INTO `FogByggemarked`.`variant`(variant_name,category_id) VALUES ('Plastmo Ecolite Groonligt', 15);
INSERT INTO `FogByggemarked`.`variant` (variant_name,category_id)VALUES ('Plastmo Ecolite Gulligt', 15);

INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (1,1);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (2,1);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (1,2);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (2,2);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (3,3);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (4,3);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (3,4);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (4,4);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (5,5);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (6,6);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (7,6);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (6,7);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (7,7);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (8,8);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (9,8);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (8,9);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (9,9);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (8,10);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (9,10);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (10,11);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (11,12);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (12,12);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (13,12);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (11,13);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (12,13);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (13,13);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (11,14);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (12,14);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (13,14);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (14,15);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (15,15);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (16,16);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (17,17);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (18,18);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (19,19);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (20,20);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (21,21);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (22,22);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (23,23);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (24,24);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (25,25);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (26,26);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (27,27);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (28,28);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (1,29);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (2,29);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (29,29);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (30,29);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (11,30);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (12,30);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (13,30);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (31,30);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (32,31);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (33,32);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (34,32);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (33,33);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (34,33);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (35,34);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (36,35);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (37,36);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (38,37);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (39,38);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (20,39);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (21,40);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (40,41);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (29,1);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (30,1);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (29,2);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (30,2);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (31,12);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (31,13);
INSERT INTO `FogByggemarked`.`material_to_category` (`material_id`,`category_id`) VAlUES (31,14);