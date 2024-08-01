-- Drop the database if it exists and create a new one
DROP DATABASE IF EXISTS library_management_system;
CREATE DATABASE library_management_system;
USE library_management_system;

-- Create categories table  
CREATE TABLE categories (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Create users table  
CREATE TABLE users (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'member'
);


-- Create books table  
CREATE TABLE books (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    category_id VARCHAR(10) NOT NULL,
    copies_qoh INT(10) NOT NULL,
    image_path VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Create borrowings table  
CREATE TABLE borrowings (
    id VARCHAR(10) NOT NULL PRIMARY KEY,
    user_id VARCHAR(10) NOT NULL,
    book_id VARCHAR(10) NOT NULL,
    borrowDate DATE NOT NULL,
    returnDate DATE NOT NULL,
    status ENUM('Borrowed', 'Returned', 'Overdue', 'Renewed', 'Lost') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);


-- Insert Data into Categories Table
INSERT INTO categories (id, name) VALUES
('c1', 'Fiction'),
('c2', 'Non-Fiction'),
('c3', 'Science'),
('c4', 'History'),
('c5', 'Biography'),
('c6', 'Fantasy'),
('c7', 'Mystery'),
('c8', 'Horror'),
('c9', 'Romance'),
('c10', 'Thriller');

-- Insert Data into Books Table
INSERT INTO books(id, title, author, category_id, copies_qoh, image_path) VALUES
    ('b1', 'Madol Doova', 'Martin Wickramasinghe', 'c1', 10, 'https://telegra.ph/file/aba04c7cdb07f0ccd81a4.jpg'),
    ('b2', 'Gamperaliya', 'Martin Wickramasinghe', 'c1', 8, 'https://telegra.ph/file/8c186f962c377cae5249d.jpg'),
    ('b3', 'The Village in the Jungle', 'Leonard Woolf', 'c1', 7, 'https://i.ibb.co/4WDTwV2/91-BHj-J-9-Jf-L-AC-UF1000-1000-QL80.jpg'),
    ('b4', 'The Road from Elephant Pass', 'Nihal De Silva', 'c1', 5, 'https://telegra.ph/file/3f9041f06781c17002672.jpg'),
    ('b5', 'Water for Elephants', 'Sara Gruen', 'c9', 3, 'https://telegra.ph/file/f1935b5d3194409f200b1.jpg'),
    ('b6', 'Great Expectations', 'Charles Dickens', 'c4', 4, 'https://telegra.ph/file/65408afe69fe7508eef93.jpg'),
    ('b7', 'To Kill a Mockingbird', 'Harper Lee', 'c7', 6, 'https://telegra.ph/file/93110fba322a6e918ab28.jpg'),
    ('b8', 'The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 'c7', 9, 'https://telegra.ph/file/da75450da6836278abc2e.jpg'),
    ('b9', '1984', 'George Orwell', 'c2', 10, 'https://telegra.ph/file/2d6bbc20ac8795a31ebc3.jpg'),
    ('b10', 'The Hobbit', 'J.R.R. Tolkien', 'c6', 8, 'https://telegra.ph/file/834dd87598099a387c132.jpg'),
    ('b11', 'Harry Potter and the Sorcerers Stone', 'J.K. Rowling', 'c6', 12, 'https://telegra.ph/file/5bea0cc7db1727bfc27b3.jpg'),
    ('b12', 'The Alchemist', 'Paulo Coelho', 'c9', 15, 'https://telegra.ph/file/6fcb8fb2349d8b2d793ea.jpg'),
    ('b13', 'Pride and Prejudice', 'Jane Austen', 'c9', 5, 'https://telegra.ph/file/c3d9de035c73c1f752411.jpg'),
    ('b14', 'The Da Vinci Code', 'Dan Brown', 'c7', 7, 'https://telegra.ph/file/6c05b11ba327a93a47d74.jpg'),
    ('b15', 'The Great Gatsby', 'F. Scott Fitzgerald', 'c1', 4, 'https://telegra.ph/file/ac428704fb2cb79432b86.jpg'),
    ('b16', 'Moby Dick', 'Herman Melville', 'c1', 3, 'https://telegra.ph/file/6e6e8ca64973f8ca8a3d7.jpg'),
    ('b17', 'War and Peace', 'Leo Tolstoy', 'c4', 6, 'https://telegra.ph/file/64f34811fea81791923d1.jpg'),
    ('b18', 'Ulysses', 'James Joyce', 'c4', 2, 'https://telegra.ph/file/aa93bbf271e77e7420b9d.jpg'),
    ('b19', 'Crime and Punishment', 'Fyodor Dostoevsky', 'c7', 8, 'https://telegra.ph/file/9f6d06c702200c5fc5c54.jpg'),
    ('b20', 'The Catcher in the Rye', 'J.D. Salinger', 'c1', 10, 'https://telegra.ph/file/aa99c186adf7338c6ab0c.jpg'),
    ('b21', 'Brave New World', 'Aldous Huxley', 'c2', 5, 'https://telegra.ph/file/183321bcd9de992c55568.jpg'),
    ('b22', 'The Odyssey', 'Homer', 'c4', 6, 'https://telegra.ph/file/3d83d2b07aabd7fb3f5de.jpg'),
    ('b23', 'Lord of the Flies', 'William Golding', 'c1', 7, 'https://telegra.ph/file/6a8cb409b1cb50289e94a.jpg'),
    ('b24', 'Animal Farm', 'George Orwell', 'c2', 9, 'https://telegra.ph/file/ecfb9ac32998e51972468.jpg'),
    ('b25', 'The Book Thief', 'Markus Zusak', 'c1', 3, 'https://telegra.ph/file/b1c81db8dc6444abf1d58.jpg'),
    ('b26', 'Jane Eyre', 'Charlotte Bronte', 'c9', 4, 'https://telegra.ph/file/792747069d83e061c60be.jpg'),
    ('b27', 'Frankenstein', 'Mary Shelley', 'c8', 5, 'https://telegra.ph/file/1517cacd2e6b719790eb2.jpg'),
    ('b28', 'Dracula', 'Bram Stoker', 'c8', 6, 'https://telegra.ph/file/5969d3c4be889d442dd80.jpg'),
    ('b29', 'The Shining', 'Stephen King', 'c8', 8, 'https://telegra.ph/file/ed2f8d0b883e94758c1de.jpg'),
    ('b30', 'Wuthering Heights', 'Emily Bronte', 'c9', 7, 'https://telegra.ph/file/00c17578e83ac68d1df9a.jpg'),
    ('b31', 'The Chronicles of Narnia', 'C.S. Lewis', 'c6', 10, 'https://telegra.ph/file/7080582c63b30f61ba328.jpg'),
    ('b32', 'Gone with the Wind', 'Margaret Mitchell', 'c9', 6, 'https://telegra.ph/file/59acd9ee9c5dd71c4c895.jpg'),
    ('b33', 'Dune', 'Frank Herbert', 'c6', 4, 'https://telegra.ph/file/3785c41f9958a8aad20eb.jpg'),
    ('b34', 'The Iliad', 'Homer', 'c4', 3, 'https://telegra.ph/file/0a7289abbf37ea8a20cae.jpg'),
    ('b35', 'The Picture of Dorian Gray', 'Oscar Wilde', 'c7', 2, 'https://telegra.ph/file/a1a1a4b2293b4ab028e96.jpg'),
    ('b36', 'Les Miserables', 'Victor Hugo', 'c4', 5, 'https://telegra.ph/file/3887a7075a68acbd9f745.jpg'),
    ('b37', 'A Tale of Two Cities', 'Charles Dickens', 'c4', 6, 'https://telegra.ph/file/eafb9bdaae6aecddd7b64.jpg'),
    ('b38', 'Don Quixote', 'Miguel de Cervantes', 'c4', 7, 'https://telegra.ph/file/8eb74ce3e77473557de47.jpg'),
    ('b39', 'Anna Karenina', 'Leo Tolstoy', 'c9', 4, 'https://telegra.ph/file/12ae7bc61507d06bbf228.jpg'),
    ('b40', 'The Brothers Karamazov', 'Fyodor Dostoevsky', 'c7', 5, 'https://telegra.ph/file/a41ac239758ca20eb7882.jpg'),
    ('b41', 'The Hunchback of Notre-Dame', 'Victor Hugo', 'c4', 6, 'https://telegra.ph/file/06f8180d2332417ae0416.jpg'),
    ('b42', 'A Clockwork Orange', 'Anthony Burgess', 'c2', 7, 'https://telegra.ph/file/05e60f19a16fde4242a2e.jpg'),
    ('b43', 'The Old Man and the Sea', 'Ernest Hemingway', 'c1', 8, 'https://telegra.ph/file/ee8cc0668c8339d6fa6ef.jpg'),
    ('b44', 'Of Mice and Men', 'John Steinbeck', 'c1', 9, 'https://telegra.ph/file/0fee96febaab81ce0930a.jpg'),
    ('b45', 'Catch-22', 'Joseph Heller', 'c2', 10, 'https://telegra.ph/file/e772409e243f3204fc520.jpg'),
    ('b46', 'Heart of Darkness', 'Joseph Conrad', 'c4', 4, 'https://telegra.ph/file/99126d3a54bcf1e976600.jpg'),
    ('b47', 'The Sun Also Rises', 'Ernest Hemingway', 'c1', 3, 'https://telegra.ph/file/b7ba873c9cb1d30853f1e.jpg'),
    ('b48', 'The Metamorphosis', 'Franz Kafka', 'c7', 5, 'https://telegra.ph/file/6a8f1dd5d86bee4cc7693.jpg'),
    ('b49', 'Slaughterhouse-Five', 'Kurt Vonnegut', 'c2', 6, 'https://telegra.ph/file/dc2e71399d5f0ec41415a.jpg'),
    ('b50', 'Fahrenheit 451', 'Ray Bradbury', 'c2', 7, 'https://telegra.ph/file/066148f7b21d9c319e503.jpg');

-- Insert Data into Users Table
INSERT INTO users (id, name, email, password, role) VALUES
('u1', 'admin', 'admin@library.com', '1234', 'admin'), 
('u2', 'KamalJayasinghe', 'kamal.jayasinghe@email.com', 'password2', 'member'),
('u3', 'SunilWijesinghe', 'sunil.wijesinghe@email.com', 'password3', 'member'),
('u4', 'NimalPerera', 'nimal.perera@email.com', 'password4', 'member'),
('u5', 'SamanthiSilva', 'samanthi.silva@email.com', 'password5', 'member'),
('u6', 'TharinduJayawardene', 'tharindu.jayawardene@email.com', 'password6', 'member'),
('u7', 'PriyanthiDeSilva', 'priyanthi.desilva@email.com', 'password7', 'member'),
('u8', 'MaheshKumara', 'mahesh.kumara@email.com', 'password8', 'member'),
('u9', 'RuwanFernando', 'ruwan.fernando@email.com', 'password9', 'member'),
('u10', 'ChandanaRathnayake', 'chandana.rathnayake@email.com', 'password10', 'member'),
('u11', 'AnjaliKumar', 'anjali.kumar@email.com', 'password11', 'member'),
('u12', 'KusumDeSilva', 'kusum.desilva@email.com', 'password12', 'member'),
('u13', 'HiranWijeratne', 'hiran.wijeratne@email.com', 'password13', 'member'),
('u14', 'MalaPerera', 'mala.perera@email.com', 'password14', 'member'),
('u15', 'NuwanJayawardena', 'nuwan.jayawardena@email.com', 'password15', 'member'),
('u16', 'SanjeewaSenanayake', 'sanjeewa.senanayake@email.com', 'password16', 'member'),
('u17', 'RashmiSilva', 'rashmi.silva@email.com', 'password17', 'member'),
('u18', 'DineshKumar', 'dinesh.kumar@email.com', 'password18', 'member'),
('u19', 'SanduniPerera', 'sanduni.perera@email.com', 'password19', 'member'),
('u20', 'ShanthiDeSilva', 'shanthi.desilva@email.com', 'password20', 'member'),
('u21', 'ThiliniWijesinghe', 'thilini.wijesinghe@email.com', 'password21', 'member'),
('u22', 'SujeewaRathnayake', 'sujeewa.rathnayake@email.com', 'password22', 'member'),
('u23', 'ArunaJayawardena', 'aruna.jayawardena@email.com', 'password23', 'member'),
('u24', 'RuwanWijeratne', 'ruwan.wijeratne@email.com', 'password24', 'member'),
('u25', 'ChandanaDeSilva', 'chandana.desilva@email.com', 'password25', 'member'),
('u26', 'DilshanKumara', 'dilshan.kumara@email.com', 'password26', 'member'),
('u27', 'RoshanPerera', 'roshan.perera@email.com', 'password27', 'member'),
('u28', 'NishanthiDeSilva', 'nishanthi.desilva@email.com', 'password28', 'member'),
('u29', 'MaheshPerera', 'mahesh.perera@email.com', 'password29', 'member'),
('u30', 'LakshmiRathnayake', 'lakshmi.rathnayake@email.com', 'password30', 'member'),
('u31', 'KasunWijesinghe', 'kasun.wijesinghe@email.com', 'password31', 'member'),
('u32', 'IsharaJayawardena', 'ishara.jayawardena@email.com', 'password32', 'member'),
('u33', 'NadeeshaDeSilva', 'nadeesha.desilva@email.com', 'password33', 'member'),
('u34', 'ThusharaKumar', 'thushara.kumar@email.com', 'password34', 'member'),
('u35', 'SulochanaPerera', 'sulochana.perera@email.com', 'password35', 'member'),
('u36', 'AshanJayawardena', 'ashan.jayawardena@email.com', 'password36', 'member'),
('u37', 'NiroshaDeSilva', 'nirosha.desilva@email.com', 'password37', 'member'),
('u38', 'DilanKumar', 'dilan.kumar@email.com', 'password38', 'member'),
('u39', 'AnushkaPerera', 'anushka.perera@email.com', 'password39', 'member'),
('u40', 'PriyankaRathnayake', 'priyanka.rathnayake@email.com', 'password40', 'member'),
('u41', 'HarshaWijesinghe', 'harsha.wijesinghe@email.com', 'password41', 'member'),
('u42', 'KusumJayawardena', 'kusum.jayawardena@email.com', 'password42', 'member'),
('u43', 'AravindaDeSilva', 'aravinda.desilva@email.com', 'password43', 'member'),
('u44', 'NuwanKumara', 'nuwan.kumara@email.com', 'password44', 'member'), 
('u46', 'DilshanWijeratne', 'dilshan.wijeratne@email.com', 'password46', 'member'),
('u47', 'LasanthaDeSilva', 'lasantha.desilva@email.com', 'password47', 'member'),
('u48', 'ShanikaRathnayake', 'shanika.rathnayake@email.com', 'password48', 'member'),
('u49', 'SachiniJayawardena', 'sachini.jayawardena@email.com', 'password49', 'member'),
('u50', 'AmilaWijesinghe', 'amila.wijesinghe@email.com', 'password50', 'member');
-- Insert Data into Borrowings Table  
INSERT INTO borrowings (id, user_id, book_id, borrowDate, returnDate, status) VALUES
('br1', 'u1', 'b5', '2024-04-01', '2024-05-01', 'Returned'),
('br2', 'u1', 'b12', '2024-04-01', '2024-05-01', 'Returned'),
('br3', 'u2', 'b3', '2024-04-08', '2024-05-08', 'Returned'),
('br4', 'u3', 'b8', '2024-04-10', '2024-05-10', 'Returned'),
('br5', 'u3', 'b19', '2024-04-10', '2024-05-10', 'Returned'),
('br6', 'u4', 'b1', '2024-04-15', '2024-05-29', 'Returned'),
('br7', 'u5', 'b7', '2024-04-20', '2024-06-04', 'Overdue'),
('br8', 'u5', 'b4', '2024-04-20', '2024-06-04', 'Overdue'),
('br9', 'u6', 'b11', '2024-04-25', '2024-06-09', 'Returned'),
('br10', 'u7', 'b10', '2024-05-01', '2024-06-15', 'Renewed'),
('br11', 'u8', 'b13', '2024-07-05', '2024-08-05', 'Borrowed'),
('br12', 'u9', 'b19', '2024-05-10', '2024-06-24', 'Lost'),
('br13', 'u10', 'b11', '2024-07-10', '2024-08-10', 'Borrowed'),
('br14', 'u11', 'b6', '2024-05-20', '2024-06-03', 'Returned'),
('br15', 'u12', 'b9', '2024-07-15', '2024-08-29', 'Borrowed'),
('br16', 'u13', 'b18', '2024-05-30', '2024-07-14', 'Overdue'),
('br17', 'u13', 'b22', '2024-05-30', '2024-07-14', 'Overdue'),
('br18', 'u14', 'b11', '2024-06-01', '2024-07-16', 'Returned'),
('br19', 'u15', 'b2', '2024-07-25', '2024-08-25', 'Borrowed'),
('br20', 'u15', 'b6', '2024-07-25', '2024-08-25', 'Borrowed'),
('br21', 'u16', 'b21', '2024-06-10', '2024-07-25', 'Returned'),
('br22', 'u17', 'b10', '2024-06-15', '2024-07-30', 'Renewed'),
('br23', 'u18', 'b23', '2024-07-20', '2024-08-20', 'Borrowed'),
('br24', 'u19', 'b2', '2024-06-25', '2024-07-09', 'Lost'),
('br25', 'u20', 'b11', '2024-07-30', '2024-08-30', 'Borrowed'),
('br26', 'u21', 'b8', '2024-07-01', '2024-07-16', 'Returned'),
('br27', 'u22', 'b6', '2024-07-05', '2024-07-20', 'Borrowed'),
('br28', 'u22', 'b4', '2024-07-05', '2024-07-20', 'Borrowed'),
('br29', 'u23', 'b19', '2024-07-10', '2024-07-25', 'Returned'),
('br30', 'u24', 'b3', '2024-07-15', '2024-08-15', 'Borrowed'),
('br31', 'u25', 'b27', '2024-07-20', '2024-08-20', 'Borrowed'),
('br32', 'u26', 'b30', '2024-07-25', '2024-08-25', 'Borrowed'),
('br33', 'u27', 'b6', '2024-07-30', '2024-08-30', 'Borrowed');
