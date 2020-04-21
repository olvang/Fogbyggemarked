DROP DATABASE IF EXISTS `FogByggemareked`;
CREATE SCHEMA `FogByggemareked`;
USE FogByggemareked;
SET SQL_MODE =  'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';

CREATE TABLE `orders` (
  `order_id` int PRIMARY KEY AUTO_INCREMENT,
  -- `customer_id` int NOT NULL,
  `carport_width` int not null,
  `carport_depth` int not null,
  `carport_height` int not null,
  `carport_incline` int not null default 0 -- ,
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
  `materials_id` INT NOT NULL AUTO_INCREMENT,
  `length` INT NOT NULL,
  `height` INT NULL,
  `width` INT NULL,
  `amount` INT NOT NULL DEFAULT 1,
  `name` VARCHAR(55) NOT NULL,
  -- `category_id` INT NOT NULL,
  PRIMARY KEY (`materials_id`),
  UNIQUE INDEX `materials_id_UNIQUE` (`materials_id` ASC) VISIBLE);
