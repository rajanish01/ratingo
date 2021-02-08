create database playground;
use playground;

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE rating (
    product_id BIGINT PRIMARY KEY REFERENCES product (id),
    total_rating BIGINT NOT NULL DEFAULT 0,
    rating_count BIGINT NOT NULL DEFAULT 0,
    average_rating DECIMAL(19 , 2 ) NOT NULL DEFAULT 0
);