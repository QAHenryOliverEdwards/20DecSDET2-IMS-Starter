drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS customers(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(255),
surname VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders(
id INT PRIMARY KEY AUTO_INCREMENT,
fk_c_id INT,
FOREIGN KEY (fk_c_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS items(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
price DECIMAL(5, 2)
);

CREATE TABLE IF NOT EXISTS order_items(
fk_o_id INT,
fk_i_id INT,
FOREIGN KEY (fk_o_id) REFERENCES orders(id),
FOREIGN KEY (fk_i_id) REFERENCES items(id)
);