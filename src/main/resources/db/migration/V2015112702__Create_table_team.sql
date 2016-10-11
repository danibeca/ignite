-- -----------------------------------------------------
-- Table teams
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS teams (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  rating INT NULL,
  organization_id INT NULL,
  PRIMARY KEY (id),
  INDEX fk_team_organization1_idx (organization_id ASC),
  CONSTRAINT fk_team_organization1
    FOREIGN KEY (organization_id)
    REFERENCES organizations (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
