DROP DATABASE IF EXISTS coffeemachine;
CREATE DATABASE coffeemachine;
    
CREATE TABLE coffeemachine.role 
(
id INT NOT NULL UNIQUE PRIMARY KEY,
title VARCHAR(20) NOT NULL UNIQUE
 );
 
 INSERT INTO coffeemachine.role
VALUES
(0,'admin'),
(1,'user');

CREATE TABLE coffeemachine.users
(
	id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(30) UNIQUE,
    password VARCHAR(30) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
	last_name  VARCHAR(30) NOT NULL,
	email      VARCHAR(30) NOT NULL UNIQUE,
    bill INT NOT NULL DEFAULT 0,
	role_id INT,
    FOREIGN KEY (role_id) REFERENCES coffeemachine.role(id)
);
INSERT INTO coffeemachine.users (login,password,first_name,last_name,email,bill,role_id)
VALUES
("ELLA","1234","lexa","drozd","mini_wariors1@mail.ru",150,1),
("ALLA","1661","zzz","sleep","mini_wariors@mail.ru",40,1),
("Nadezda","parole","nadya","sinkevich","mi@mail.ru",70,1);

CREATE TABLE coffeemachine.ingredients
(
	id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(30) UNIQUE,
    price INT DEFAULT 0 NOT NULL,
    count INT DEFAULT 0 NOT NULL
);


CREATE TABLE coffeemachine.beverages
(
	id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(30) UNIQUE,
    count INT DEFAULT 0 NOT NULL,
    price INT DEFAULT 0 NOT NULL
);

CREATE TABLE coffeemachine.orders
(
	id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
	id_user INT NOT NULL,
    id_beverage INT NOT NULL,
    date VARCHAR(50),
    FOREIGN KEY (id_user) REFERENCES coffeemachine.users(id),
    FOREIGN KEY (id_beverage) REFERENCES coffeemachine.beverages(id)
);

CREATE TABLE coffeemachine.additives
(
	id_order INT NOT NULL,
    id_ingredient INT NOT NULL,
    FOREIGN KEY (id_order) REFERENCES coffeemachine.orders(id),
    FOREIGN KEY (id_ingredient) REFERENCES coffeemachine.ingredients(id)
)
    
