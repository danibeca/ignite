package com.sprhib.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Organization;
import com.sprhib.model.Team;
import com.sprhib.service.OrganizationService;
import com.sprhib.service.TeamService;
import com.sprhib.util.DataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
public class TeamControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private TeamService teamService;
	
	@Mock
	private OrganizationService organizationService;
	
	@InjectMocks
	private TeamController teamController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();		
	}

	@Test
	public void testAddTeam() throws Exception {
		mockMvc.perform(get("/team/add"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("add-team-form"));
	}
	
	@Test
	public void testAddingTeam() throws Exception {
		mockMvc.perform(post("/team/add")) 
			   .andExpect(status().isOk())
               .andExpect(model().attribute("messageCode", "mss.add.team.ok"))
               .andExpect(view().name("home"));
	}
	
	@Test
	public void testListTeams() throws Exception {
		Organization org1 = DataCreator.createOrganization(1, "Organization Test 1");
		Organization org2 = DataCreator.createOrganization(2, "Organization Test 2");
		
		Team team1 = DataCreator.createTeam(1, "Team Test 1", 200);
		Team team2 = DataCreator.createTeam(2, "Team Test 2", 300);
		team1.setOrganization(org1);
		team2.setOrganization(org2);
		List<Team> teams = new ArrayList<Team>();
		teams.add(team1);
		teams.add(team2);
		
		when(teamService.getTeams()).thenReturn(teams);
		
		mockMvc.perform(get("/team/list"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("list-of-teams"))
			   .andExpect(model().attribute("teams", hasSize(teams.size())))
			   .andExpect(model().attribute("teams", hasItem(
                   allOf(
                       hasProperty("id", is(team1.getId())),
                       hasProperty("name", is(team1.getName())),
                       hasProperty("rating", is(team1.getRating())),
                       hasProperty("organization", hasProperty("id", is(org1.getId())))
                   )
               )))
               .andExpect(model().attribute("teams", hasItem(
                   allOf(
            		   hasProperty("id", is(team2.getId())),
                       hasProperty("name", is(team2.getName())),
                       hasProperty("rating", is(team2.getRating())),
                       hasProperty("organization", hasProperty("id", is(org2.getId())))
                   )
               )));
	}
	
	@Test
	public void testEditTeam() throws Exception {
		Organization org1 = DataCreator.createOrganization(1, "Organization Test 1");
		Organization org2 = DataCreator.createOrganization(2, "Organization Test 2");
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(org1);
		organizations.add(org2);
		
		Team team = DataCreator.createTeam(1, "Team Test 1", 200);
		team.setOrganization(org1);
		
		when(teamService.getTeam(team.getId())).thenReturn(team);
		when(organizationService.getOrganizations()).thenReturn(organizations);
		
		mockMvc.perform(get("/team/edit/{id}", team.getId()))
			   .andExpect(status().isOk())
			   .andExpect(view().name("edit-team-form"))
			   .andExpect(model().attribute("team", hasProperty("id", is(team.getId()))))
			   .andExpect(model().attribute("team", hasProperty("name", is(team.getName()))))
			   .andExpect(model().attribute("team", hasProperty("organization", hasProperty("id", is(org1.getId())))))
			   .andExpect(model().attribute("organizations", hasSize(organizations.size())));
	}
	
	@Test
	public void testEdittingTeam() throws Exception {
		mockMvc.perform(post("/team/edit/{id}", 1))
			   .andExpect(status().isOk())
               .andExpect(model().attribute("messageCode", "mss.edit.team.ok"))
               .andExpect(view().name("home"));
	}

	@Test
	public void testDeleteTeamOk() throws Exception {
		mockMvc.perform(get("/team/delete/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(model().attribute("messageCode", "mss.delete.team.ok"))
				.andExpect(view().name("home"));				
	}	
	
}
