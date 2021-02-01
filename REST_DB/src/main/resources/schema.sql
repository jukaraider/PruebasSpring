DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id 			INTEGER     	NOT NULL AUTO_INCREMENT,
    name 		VARCHAR(50) 	NOT NULL,
    category 	VARCHAR(100) 	DEFAULT NULL,
    PRIMARY KEY (id)
);