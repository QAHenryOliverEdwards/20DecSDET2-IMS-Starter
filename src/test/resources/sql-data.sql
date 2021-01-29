INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`name`, `price`) VALUES ('pencil', 0.49);
INSERT INTO `orders` (`fk_c_id`) VALUES (1);
INSERT INTO `order_items` (`fk_o_id`, `fk_i_id`) VALUES (1, 1);

INSERT INTO `customers` (`first_name`, `surname`) VALUES ('henry', 'oliver-edwards');
INSERT INTO `items` (`name`, `price`) VALUES ('pen', 0.99);
INSERT INTO `orders` (`fk_c_id`) VALUES (2);
INSERT INTO `order_items` (`fk_o_id`, `fk_i_id`) VALUES (2, 2);

INSERT INTO `customers` (`first_name`, `surname`) VALUES ('aaron', 'mayne');