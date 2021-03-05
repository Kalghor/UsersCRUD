USE workshop2;
CREATE TABLE users (
                       id INT(11) AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(60) NOT NULL,
                       PRIMARY KEY(id)
);