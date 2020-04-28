CREATE DATABASE library CHARACTER SET utf8;

USE library;

CREATE TABLE warehouse
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE good
(
    id           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    warehouse_id INT UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);