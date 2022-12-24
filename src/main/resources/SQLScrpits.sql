CREATE TABLE IF NOT EXISTS PRODUCTS (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(450) NOT NULL,
  `complexity` varchar(450) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(450) NOT NULL
)

INSERT INTO PRODUCTS VALUES
(1,'Sheriff of Nottingham 2nd Edition','1.90',180.00,'board8.jpg'),
(2,'The Magnificent','3.20',190.00,'board11.jpg'),
(3,'Dead of Winter','3.00',190.00,'board3.jpg'),
(4,'Dune Imperium','3.10',250.00,'board4.png'),
(5,'Eldritch Horror','3.30',310.00,'board5.jpg'),
(6,'Root','3.80',160.00,'board9.jpg'),
(7,'Betrayal at House on the Hill','2.40', 230.00,'board7.jpg'),
(8,'Catan','2.40',150.00,'board1.jpg'),
(9,'Ticket To Ride','1.90',90.00,'board12.jpg'),
(10,'Stratego','1.80',170.00,'board10.jpg'),
(11,'Exploding Kittens Recipes for Disaster','1.00',50.00,'Exploding-kittens-recipes-for-disaster.jpg'),
(12,'Codenames','1.30',70.00,'board2.jpg')



CREATE TABLE IF NOT EXISTS USERS (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
)



CREATE TABLE IF NOT EXISTS ORDERS(
  `o_id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NOT NULL,
  `u_id` int NOT NULL,
  `o_quantity` int NOT NULL,
  `o_date` varchar(450) NOT NULL
)
