DROP DATABASE IF EXISTS library_management_system;
CREATE DATABASE library_management_system;
USE library_management_system;

CREATE TABLE Books (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    category_id VARCHAR(10) NOT NULL,
    copies_qoh int(10) NOT NULL
    FOREIGN KEY(category_id) REFERENCES Categories(id)
);

CREATE TABLE Categories (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
)
