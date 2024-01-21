# Create table Person
CREATE TABLE Person
(
id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
name          VARCHAR(100)                       NOT NULL,
lastname      VARCHAR(130)                       NOT NULL,
year_of_birth int check ( year_of_birth > 1900 ) NOT NULL,
phone_number  VARCHAR(30)                        NOT NULL UNIQUE,
email         VARCHAR(100)                       NOT NULL UNIQUE,
password      VARCHAR                            NOT NULL
);


#### Helpers command

* DROP TABLE Person;

* select *
from Person;

* select *
from foodreview;

* DROP TABLE FoodReview;

# Create table FoodReview
CREATE TABLE FoodReview
(
id         int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
person_id  int NOT NULL REFERENCES Person (id)  ON DELETE CASCADE ,
dateCreate TIMESTAMP,
grade      int NOT NULL CHECK ( grade >= 0 ) CHECK ( grade < 6 ),
comment    VARCHAR DEFAULT 'this default comment'
);

# Insert data from table Users
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Kazimir', 'Malevitch', 1935, '89000000001', 'malevitch@mail.ru', 'kazimir');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Vladimir', 'Mayakovskiy', 1930, '8901000002', 'mayakovskiy@mail.ru', 'vladimir');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Iosif', 'Stalin', 1953, '8901000000', 'stal@mail.ru', 'iosif');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Anatoliy', 'Vasserman', 1952, '8901000003', 'vasser@mail.ru', 'anatoliy');

# Insert data from table FoodReviews
FoodReviews relation for Users in ManyToOne relationships ** 


INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-09-09 23:19:57.017953', 4, 4, 'Good service, good prices');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-05-09 13:19:57.017954', 4, 4, 'tasty food');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-08-09 20:19:57.017955', 4, 4, 'Delicious dishes');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 4, 1, 'Ass');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 3, 1, 'Ass and fail');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 2, 5, 'Good');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 1, 5, 'Good food');


# ==========================

CREATE TABLE Person
(
person_id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
person_name          VARCHAR(100)                              NOT NULL,
person_lastname      VARCHAR(130)                              NOT NULL,
person_year_of_birth int check ( person_year_of_birth > 1900 ) NOT NULL,
person_phone_number  VARCHAR(30)                               NOT NULL UNIQUE,
person_email         VARCHAR(100)                              NOT NULL UNIQUE,
person_username      VARCHAR                                   NOT NULL ,
person_password      VARCHAR                                   NOT NULL,
person_created_at    timestamp,
person_updated_at    timestamp,
person_updated_who   VARCHAR(100)                              NOT NULL
);

DROP TABLE Person cascade;
-- DROP TABLE users;

select *
from Person;

TRUNCATE TABLE person;
-- DELETE FROM person;

-- select *
-- from foodreview;

-- DROP TABLE FoodReview;


-- CREATE TABLE FoodReview
-- (
--     id         int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
--     person_id  int NOT NULL REFERENCES Person (id) ON DELETE CASCADE,
--     dateCreate TIMESTAMP,
--     grade      int NOT NULL CHECK ( grade >= 0 ) CHECK ( grade < 6 ),
--     comment    VARCHAR DEFAULT 'this default comment'
-- );


INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username,person_password, person_updated_who)
VALUES ('Vladimir', 'Mayakovskiy', 1930, '8901000002', 'mayakovskiy@mail.ru', 'vladimir', 'vladimir', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username,person_password, person_updated_who)
VALUES ('Iosif', 'Stalin', 1953, '8901000000', 'stal@mail.ru', 'iosif','iosif', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username,person_password, person_updated_who)
VALUES ('Anatoliy', 'Vasserman', 1952, '8901000003', 'vasser@mail.ru', 'anatoliy','anatoliy','John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username,person_password, person_updated_who)
VALUES ('Kazimir', 'Malevitch', 1935, '89000000001', 'malevitch@mail.ru', 'kazimir','kazimir', 'John Conor');

--
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-09-09 23:19:57.017953', 4, 4, 'Good service, good prices');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-05-09 13:19:57.017954', 4, 4, 'tasty food');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-08-09 20:19:57.017955', 4, 4, 'Delicious dishes');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 4, 1, 'Ass');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 3, 1, 'Ass');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 2, 5, 'Good');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 1, 5, 'Good food');

# =======================
sale laptop


-- Create table Person
CREATE TABLE Person
(
id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
name          VARCHAR(100)                       NOT NULL,
lastname      VARCHAR(130)                       NOT NULL,
year_of_birth int check ( year_of_birth > 1900 ) NOT NULL,
phone_number  VARCHAR(30)                        NOT NULL UNIQUE,
email         VARCHAR(100)                       NOT NULL UNIQUE,
password      VARCHAR                            NOT NULL
);


-- Helpers command

DROP TABLE Person;

select *
from Person;

select *
from foodreview;

DROP TABLE FoodReview;

-- Create table FoodReview
CREATE TABLE FoodReview
(
id         int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
person_id  int NOT NULL REFERENCES Person (id) ON DELETE CASCADE,
dateCreate TIMESTAMP,
grade      int NOT NULL CHECK ( grade >= 0 ) CHECK ( grade < 6 ),
comment    VARCHAR DEFAULT 'this default comment'
);

--  Insert data from table Users
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Kazimir', 'Malevitch', 1935, '89000000001', 'malevitch@mail.ru', 'kazimir');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Vladimir', 'Mayakovskiy', 1930, '8901000002', 'mayakovskiy@mail.ru', 'vladimir');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Iosif', 'Stalin', 1953, '8901000000', 'stal@mail.ru', 'iosif');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Anatoliy', 'Vasserman', 1952, '8901000003', 'vasser@mail.ru', 'anatoliy');

--  Insert data from table FoodReviews
--     FoodReviews relation for Users in ManyToOne relationships **


INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-09-09 23:19:57.017953', 4, 4, 'Good service, good prices');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-05-09 13:19:57.017954', 4, 4, 'tasty food');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-08-09 20:19:57.017955', 4, 4, 'Delicious dishes');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 4, 1, 'Ass');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 3, 1, 'Ass and fail');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 2, 5, 'Good');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 1, 5, 'Good food');


-- # ==========================

CREATE TABLE Person
(
person_id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
person_name          VARCHAR(100)                              NOT NULL,
person_lastname      VARCHAR(130)                              NOT NULL,
person_year_of_birth int check ( person_year_of_birth > 1900 ) NOT NULL,
person_phone_number  VARCHAR(30)                               NOT NULL UNIQUE,
person_email         VARCHAR(100)                              NOT NULL UNIQUE,
person_username      VARCHAR                                   NOT NULL,
person_password      VARCHAR                                   NOT NULL,
person_role          int                                       NOT NULL REFERENCES Role (role_id) ON DELETE CASCADE ON UPDATE NO ACTION,
person_created_at    timestamp,
person_updated_at    timestamp,
person_updated_who   VARCHAR(100)                              NOT NULL
);

DROP TABLE Person;
-- DROP TABLE users;

select *
from Person;

TRUNCATE TABLE person;
-- DELETE FROM person;

CREATE TABLE Role
(
role_id    int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
role_name  VARCHAR(130) NOT NULL,
role_value VARCHAR(20)  NOT NULL
);

SELECT *
FROM role;

DROP TABLE Role;
-- select *
-- from foodreview;

CREATE TABLE Discount
(
discount_id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
discount_name VARCHAR(130) NOT NULL ,
discount_sale VARCHAR(30) NOT NULL
);

-- DROP TABLE FoodReview;


-- CREATE TABLE FoodReview
-- (
--     id         int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
--     person_id  int NOT NULL REFERENCES Person (id) ON DELETE CASCADE,
--     dateCreate TIMESTAMP,
--     grade      int NOT NULL CHECK ( grade >= 0 ) CHECK ( grade < 6 ),
--     comment    VARCHAR DEFAULT 'this default comment'
-- );


INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Vladimir', 'Mayakovskiy', 1930, '8901000002', 'mayakovskiy@mail.ru', 'vladimir', 'vladimir', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Iosif', 'Stalin', 1953, '8901000000', 'stal@mail.ru', 'iosif', 'iosif', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Anatoliy', 'Vasserman', 1952, '8901000003', 'vasser@mail.ru', 'anatoliy', 'anatoliy', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Kazimir', 'Malevitch', 1935, '89000000001', 'malevitch@mail.ru', 'kazimir', 'kazimir', 'John Conor');

--
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-09-09 23:19:57.017953', 4, 4, 'Good service, good prices');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-05-09 13:19:57.017954', 4, 4, 'tasty food');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-08-09 20:19:57.017955', 4, 4, 'Delicious dishes');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 4, 1, 'Ass');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 3, 1, 'Ass');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 2, 5, 'Good');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 1, 5, 'Good food');

-- # =======================
-- sale laptop
================================================================
-- Create table Person
CREATE TABLE Person
(
id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
name          VARCHAR(100)                       NOT NULL,
lastname      VARCHAR(130)                       NOT NULL,
year_of_birth int check ( year_of_birth > 1900 ) NOT NULL,
phone_number  VARCHAR(30)                        NOT NULL UNIQUE,
email         VARCHAR(100)                       NOT NULL UNIQUE,
password      VARCHAR                            NOT NULL
);


-- Helpers command

DROP TABLE Person;

select *
from Person;

select *
from foodreview;

DROP TABLE FoodReview;

-- Create table FoodReview
CREATE TABLE FoodReview
(
id         int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
person_id  int NOT NULL REFERENCES Person (id) ON DELETE CASCADE,
dateCreate TIMESTAMP,
grade      int NOT NULL CHECK ( grade >= 0 ) CHECK ( grade < 6 ),
comment    VARCHAR DEFAULT 'this default comment'
);

--  Insert data from table Users
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Kazimir', 'Malevitch', 1935, '89000000001', 'malevitch@mail.ru', 'kazimir');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Vladimir', 'Mayakovskiy', 1930, '8901000002', 'mayakovskiy@mail.ru', 'vladimir');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Iosif', 'Stalin', 1953, '8901000000', 'stal@mail.ru', 'iosif');
INSERT INTO Person(name, lastname, year_of_birth, phone_number, email, password)
VALUES ('Anatoliy', 'Vasserman', 1952, '8901000003', 'vasser@mail.ru', 'anatoliy');

--  Insert data from table FoodReviews
--     FoodReviews relation for Users in ManyToOne relationships **


INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-09-09 23:19:57.017953', 4, 4, 'Good service, good prices');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-05-09 13:19:57.017954', 4, 4, 'tasty food');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-08-09 20:19:57.017955', 4, 4, 'Delicious dishes');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 4, 1, 'Ass');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 3, 1, 'Ass and fail');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 2, 5, 'Good');
INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
VALUES ('2023-07-09 03:19:57.017956', 1, 5, 'Good food');


-- # ==========================

CREATE TABLE Person
(
person_id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
person_name          VARCHAR(100)                              NOT NULL,
person_lastname      VARCHAR(130)                              NOT NULL,
person_year_of_birth int check ( person_year_of_birth > 1900 ) NOT NULL,
person_phone_number  VARCHAR(30)                               NOT NULL UNIQUE,
person_email         VARCHAR(100)                              NOT NULL UNIQUE,
person_username      VARCHAR                                   NOT NULL,
person_password      VARCHAR                                   NOT NULL,
person_role          int                                       NOT NULL REFERENCES Role (role_id) ON DELETE CASCADE ON UPDATE NO ACTION,
person_discount      int                                       NOT NULL REFERENCES Discount (discount_id) ON DELETE CASCADE ON UPDATE NO ACTION,
person_created_at    timestamp,
person_updated_at    timestamp,
person_updated_who   VARCHAR(100)                              NOT NULL
);

-- SELECT * FROM Person WHERE ;

DROP TABLE Person CASCADE;
-- DROP TABLE users;

select *
from Person;

TRUNCATE TABLE person;
-- DELETE FROM person;

CREATE TABLE Role
(
role_id    int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
role_name  VARCHAR(130) NOT NULL,
role_value VARCHAR(20)  NOT NULL
);

SELECT *
FROM role;


DROP TABLE Role;
-- select *
-- from foodreview;

CREATE TABLE Discount
(
discount_id   int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
discount_name VARCHAR(130) NOT NULL,
discount_sale VARCHAR(30)  NOT NULL
);

SELECT *
FROM discount;


CREATE TABLE Restaurant_reviews
(
restaurant_reviews_id        int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
restaurant_reviews_owner     int       NOT NULL REFERENCES Person (person_id) ON DELETE CASCADE ON UPDATE SET NULL,
restaurant_reviews_gradle    int       NOT NULL,
restaurant_reviews_comment   VARCHAR,
restaurant_reviews_create_at TIMESTAMP NOT NULL,
restaurant_reviews_update_at TIMESTAMP NOT NULL
);

SELECT *
FROM restaurant_reviews;

-- DROP TABLE FoodReview;


-- CREATE TABLE FoodReview
-- (
--     id         int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
--     person_id  int NOT NULL REFERENCES Person (id) ON DELETE CASCADE,
--     dateCreate TIMESTAMP,
--     grade      int NOT NULL CHECK ( grade >= 0 ) CHECK ( grade < 6 ),
--     comment    VARCHAR DEFAULT 'this default comment'
-- );


INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Vladimir', 'Mayakovskiy', 1930, '8901000002', 'mayakovskiy@mail.ru', 'vladimir', 'vladimir', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Iosif', 'Stalin', 1953, '8901000000', 'stal@mail.ru', 'iosif', 'iosif', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Anatoliy', 'Vasserman', 1952, '8901000003', 'vasser@mail.ru', 'anatoliy', 'anatoliy', 'John Conor');
INSERT INTO Person(person_name, person_lastname, person_year_of_birth, person_phone_number, person_email,
person_username, person_password, person_updated_who)
VALUES ('Kazimir', 'Malevitch', 1935, '89000000001', 'malevitch@mail.ru', 'kazimir', 'kazimir', 'John Conor');

--
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-09-09 23:19:57.017953', 4, 4, 'Good service, good prices');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-05-09 13:19:57.017954', 4, 4, 'tasty food');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-08-09 20:19:57.017955', 4, 4, 'Delicious dishes');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 4, 1, 'Ass');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 3, 1, 'Ass');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 2, 5, 'Good');
-- INSERT INTO FoodReview(dateCreate, person_id, grade, comment)
-- VALUES ('2023-07-09 03:19:57.017956', 1, 5, 'Good food');

-- # =======================
-- sale laptop

================================================================
StateFromTable (for ENUM)

CREATE TABLE IF NOT EXISTS State_from_table (
state_from_table_id int GENERATED BY DEFAULT AS IDENTITY ,
state_from_table_name varchar(100) NOT NULL ,
state_from_table_value varchar(40) NOT NULL
);

DROP TABLE IF EXISTS State_from_table;

INSERT INTO State_from_table(state_from_table_name,state_from_table_value) VALUES ('ready','READY_TO_BOARD');

SELECT * FROM State_from_table;



ALTER TABLE State_from_table ADD CONSTRAINT state_from_table_id_constr UNIQUE (state_from_table_id);

CREATE TABLE IF NOT EXISTS Reserve_table (
reverse_table_id int GENERATED BY DEFAULT AS IDENTITY UNIQUE ,
reverse_table_accommodation_number int NOT NULL ,
reserve_table_state int NOT NULL REFERENCES state_from_table(state_from_table_id) ON DELETE CASCADE ON UPDATE NO ACTION,
reverse_table_number_of_seats int NOT NULL

);

SELECT * FROM Reserve_table;

DROP TABLE Reserve_table;


CREATE TABLE if NOT EXISTS Order_table (
order_id int UNIQUE GENERATED BY DEFAULT AS IDENTITY ,
order_owner int NOT NULL REFERENCES Person(person_id) ON DELETE CASCADE,
order_price DECIMAL(10,1) NOT NULL,
order_status varchar(120) NOT NULL

);


CREATE TABLE if NOT EXISTS Dishes (
dishes_id bigint UNIQUE GENERATED BY DEFAULT AS IDENTITY ,
dishes_name varchar(150) not null ,
dishes_price DECIMAL(10,1) NOT NULL ,
dishes_weight DECIMAL(10,1) ,
dishes_calories int check ( dishes_calories >= 0 ),
dishes_proteins int check ( dishes_proteins >= 0 ),
dishes_fats int check ( dishes_fats >= 0 ),
dishes_carbohydrates int check ( dishes_carbohydrates >= 0 ),
dishes_image_url varchar

);


CREATE TABLE if NOT EXISTS Order_element
(
order_element_id            bigint UNIQUE GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
order_element_order_elements int REFERENCES Order_table (order_id) ON DELETE NO ACTION,
order_element_dishes        bigint REFERENCES Dishes (dishes_id) ON DELETE NO ACTION,
order_element_count         int NOT NULL CHECK ( order_element_count >= 0 )
);

[//]: # (DROP TABLE if EXISTS Order_element;)
Select * from Order_element;
