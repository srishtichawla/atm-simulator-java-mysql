DROP DATABASE bankmanagementsystem;
CREATE DATABASE bankmanagementsystem;
USE bankmanagementsystem;

CREATE TABLE signup (
    formno VARCHAR(30),
    name VARCHAR(50),
    father_name VARCHAR(50),
    dob VARCHAR(20),
    gender VARCHAR(10),
    email VARCHAR(50),
    marital_status VARCHAR(20),
    address VARCHAR(100),
    city VARCHAR(50),
    province VARCHAR(50),
    postal VARCHAR(20)
);

SHOW TABLES;

SELECT * FROM SIGNUP;

CREATE TABLE signuptwo (
    formno VARCHAR(20),
    religion VARCHAR(50),
    income VARCHAR(50),
    education VARCHAR(50),
    occupation VARCHAR(50),
    id_type VARCHAR(50),
    id_number VARCHAR(50),
    existing_account VARCHAR(10)
);

SHOW TABLES;

SELECT * FROM signuptwo;
SELECT * FROM cards;

DROP TABLE bank; 

CREATE table bank(pin varchar(10), date varchar(50), type varchar(20), amount varchar(20));

select * from bank;

DESCRIBE bank;
