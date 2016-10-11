DELETE FROM team_has_member WHERE member_id IN (SELECT id FROM members WHERE name LIKE '@New Member Test%');
DELETE FROM members WHERE name LIKE '@New Member Test%';
DELETE FROM teams WHERE name LIKE '@New Team Test%';
DELETE FROM organizations WHERE name LIKE '@New Organization Test%';