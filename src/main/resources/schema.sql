
CREATE TABLE coin(
   `id` INT PRIMARY KEY AUTO_INCREMENT,
   `code` VARCHAR(3) NULL,
   `symbol` VARCHAR(45) NULL,
   `rate` VARCHAR(20) NULL,
   `description` VARCHAR(45) NULL,
   `rate_float` FLOAT NULL
);
