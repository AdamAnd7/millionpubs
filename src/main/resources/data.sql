CREATE TABLE user_account(
    id integer PRIMARY KEY AUTO_INCREMENT,
    user_name varchar (255) unique,
    balance decimal
);

INSERT INTO user_account (id,user_name, balance) VALUES (1,'Adam', 1000.00);
INSERT INTO user_account (id,user_name, balance) VALUES (2,'Mariusz', 1200.00);
INSERT INTO user_account (id,user_name, balance) VALUES (3,'Waldemar', 2500.00);