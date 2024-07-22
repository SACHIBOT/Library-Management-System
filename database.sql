DROP DATABASE IF EXISTS library_management_system;
CREATE DATABASE library_management_system;
USE library_management_system;

CREATE TABLE books (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    category_id VARCHAR(10) NOT NULL,
    copies_qoh int(10) NOT NULL,
    FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE categories (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
)

CREATE TABLE users (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL 
);

CREATE TABLE borrowings (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    book_id VARCHAR(50) NOT NULL,
    borrowDate DATE NOT NULL,
    returnDate DATE NOT NULL,
    status ENUM('Borrowed', 'Returned', 'Overdue', 'Renewed', 'Lost') NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(book_id) REFERENCES books(id)
);