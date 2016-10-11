package com.sprhib.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprhib.dao.TeamDAO;
import com.sprhib.model.Team;
import com.sprhib.util.DataCreator;

public class TeamServiceImplTest {

	@InjectMocks
	private TeamServiceImpl teamService;

	@Mock
	private TeamDAO teamDAO;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetTeam() {
		Integer id = 1;
		String name = "Team #1";
		Integer rating = 22;
		when(teamDAO.getTeam(id)).thenReturn(DataCreator.createTeam(id, name, rating));

		Team result = teamService.getTeam(id);

		assertEquals(id, result.getId());
		assertEquals(name, result.getName());
		assertEquals(rating, result.getRating());
	}

	@Test
	public void testGetTeams() {
		List<Team> teams = new ArrayList<Team>();
		teams.add(DataCreator.createTeam(1, "Team 1", 12));
		teams.add(DataCreator.createTeam(2, "Team 2", 1));

		when(teamDAO.getTeams()).thenReturn(teams);

		List<Team> result = teamService.getTeams();
		assertTrue(result.size() == 2);
	}
}
