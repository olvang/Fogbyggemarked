DROP DATABASE IF EXISTS `FogByggemarked`;
CREATE SCHEMA `FogByggemarked`;
USE FogByggemarked;
SET SQL_MODE =  'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';

CREATE TABLE `orders` (
  `order_id` int PRIMARY KEY AUTO_INCREMENT,
  `carport_width` int not null,
  `carport_depth` int not null,
  `carport_height` int not null,
  `carport_incline` int not null default 0,
  `order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `customer_name` varchar(255) not null,
  `customer_adresse` varchar(255) not null,
  `customer_email` varchar(255) not null,
  `customer_phonenumber` varchar(8) not null,
  `customer_zipcode` varchar(4) not null
  -- `roof_material_id` int not null, 
  -- `retail_price` int not null 
);

CREATE TABLE `bill` ( 
	`bill_id` int PRIMARY KEY AUTO_INCREMENT,
    `order_id` int not null,
	`markup_price` int not null,
    CONSTRAINT bill_fkey
		FOREIGN KEY (`order_id`) 
		REFERENCES orders(`order_id`)
		ON DELETE CASCADE
); 

CREATE TABLE `sheds` (
	`shed_id` int PRIMARY KEY AUTO_INCREMENT,
    `order_id` int not null,
    `shed_width` int not null,
    `shed_depth` int not null, 
    -- `shed_wall_id` int not null,
    -- `shed_floor_id` int not null,
    CONSTRAINT shed_fkey
		FOREIGN KEY (`order_id`) 
		REFERENCES orders(`order_id`)
		ON DELETE CASCADE
);

CREATE TABLE `materials` (
  `materials_id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `length` INT NOT NULL DEFAULT 0,
  `height` INT NULL DEFAULT 0,
  `width` INT NULL DEFAULT 0,
  `amount` INT NOT NULL DEFAULT 1,
  `name` VARCHAR(55) NOT NULL,
  `price` int not null,
  `unit` VARCHAR(55) NOT NULL
);

CREATE TABLE category (
	`category_id` INT primary key not null auto_increment,
    `decription` varchar(250) not null
);

CREATE TABLE material_to_category(
	`material_id` int not null,
    `category_id` int not null, 
    
    CONSTRAINT material_id_fkey
		FOREIGN KEY (`material_id`) 
		REFERENCES materials(`materials_id`),
	CONSTRAINT category_id_fkey
		FOREIGN KEY (`category_id`) 
		REFERENCES category(`category_id`)
);
CREATE TABLE variant(
	`variant_id` int primary key not null auto_increment,
    `variant_name` varchar(250) not null, 
	`category_id` int not null,
    CONSTRAINT variant_category_fkey
		FOREIGN KEY (`category_id`) 
		REFERENCES category(`category_id`)
);
