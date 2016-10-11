-- -----------------------------------------------------
-- Table members
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS members (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;