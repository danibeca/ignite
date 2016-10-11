package com.sprhib.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Team;
import com.sprhib.util.DataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@Transactional
public class TeamDAOImplTest {
	@Autowired
	private TeamDAO teamDAO;

	@Autowired
	private OrganizationDAO organizationDAO;

	private Team team;

	@Before
	public void initialize() {
		team = DataCreator.createTeam(teamDAO, "Team TEST", 200);
		team.setOrganization(DataCreator.createOrganization(organizationDAO, "Organization for team"));
	}

	@Test
	public void testAddTeam() {
		assertNotNull(team.getId());
		assertNotNull(team.getName());
		assertNotNull(team.getOrganization().getId());
	}

	@Test
	public void testEditTeam() {
		String editedName = "Team EDITED";
		Integer editedRating = 10;
		Team teamEdited = new Team();
		teamEdited.setId(team.getId());
		teamEdited.setName(editedName);
		teamEdited.setRating(editedRating);

		teamDAO.updateTeam(teamEdited);

		assertEquals(editedName, team.getName());
		assertEquals(editedRating, team.getRating());
		assertNull(team.getOrganization());
	}

	@Test
	public void testDeleteTeam() {
		teamDAO.deleteTeam(team.getId());
		assertNull(teamDAO.getTeam(team.getId()));
	}

	@Test
	public void testGetTeam() {
		assertNotNull(teamDAO.getTeam(team.getId()));
	}

	@Test
	public void testGetTeams() {
		DataCreator.createTeam(teamDAO, "Team 2", 252);
		assertTrue(teamDAO.getTeams().size() > 1);
	}

}
