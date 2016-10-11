-- -----------------------------------------------------
-- Table team_has_member
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS team_has_member (
  team_id INT NOT NULL,
  member_id INT NOT NULL,
  PRIMARY KEY (team_id, member_id),
  INDEX fk_team_has_member_member1_idx (member_id ASC),
  INDEX fk_team_has_member_team_idx (team_id ASC),
  CONSTRAINT fk_team_has_member_team
    FOREIGN KEY (team_id)
    REFERENCES teams (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_team_has_member_member1
    FOREIGN KEY (member_id)
    REFERENCES members (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;