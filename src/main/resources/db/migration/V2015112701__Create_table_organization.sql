-- -----------------------------------------------------
-- Table organizations
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS organizations (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;
