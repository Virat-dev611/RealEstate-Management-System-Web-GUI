CREATE DATABASE IF NOT EXISTS realestate_db;
USE realestate_db;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password_hash VARCHAR(255),
    role VARCHAR(20)
);

CREATE TABLE properties (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200),
    type VARCHAR(50),
    price DECIMAL(12,2),
    size VARCHAR(50),
    location VARCHAR(200),
    status VARCHAR(40),
    owner_id INT
);

CREATE TABLE bookings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    user_id INT,
    message TEXT,
    status VARCHAR(40)
);
