CREATE TABLE IF NOT EXISTS user_table(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Category (
    categoryId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publicationYear INT NOT NULL,
    isbn VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    categoryId BIGINT,
    FOREIGN KEY (categoryId) REFERENCES Category(categoryid)
);