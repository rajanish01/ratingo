create database playground;
use playground;

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    rating_id BIGINT REFERENCES rating (id)
);

CREATE TABLE rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    total_rating BIGINT NOT NULL DEFAULT 0,
    rating_count BIGINT NOT NULL DEFAULT 0,
    average_rating DECIMAL(19 , 6 ) NOT NULL DEFAULT 0
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mob_no VARCHAR(255) NOT NULL
);

CREATE TABLE user_enrollment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES user (id),
    product_id BIGINT NOT NULL REFERENCES product (id),
    start_date DATE,
    end_date DATE,
    active TINYINT(1)
);