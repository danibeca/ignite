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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.sprhib.model.Member;
import com.sprhib.model.Team;
import com.sprhib.service.MemberService;
import com.sprhib.service.TeamService;
import com.sprhib.util.DataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
public class MemberControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private MemberService memberService;
	
	@Mock
	private TeamService teamService;
	
	@InjectMocks
	private MemberController memberController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();		
	}

	@Test
	public void testAddMember() throws Exception {
		mockMvc.perform(get("/member/add"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("add-member-form"));
	}
	
	@Test
	public void testAddingMember() throws Exception {
		mockMvc.perform(post("/member/add")) 
			   .andExpect(status().isOk())
               .andExpect(model().attribute("messageCode", "mss.add.member.ok"))
               .andExpect(view().name("home"));
	}
	
	@Test
	public void testListMembers() throws Exception {
		Team team1 = DataCreator.createTeam(1, "Team Test 1", 200);
		Team team2 = DataCreator.createTeam(2, "Team Test 2", 300);
		Set<Team> teamsMember1 = new HashSet<Team>();
		Set<Team> teamsMember2 = new HashSet<Team>();
		teamsMember1.add(team1);
		teamsMember1.add(team2);
		teamsMember2.add(team1);
		
		Member member1 = DataCreator.createMember(1, "Member Test 1");
		Member member2 = DataCreator.createMember(2, "Member Test 2");
		member1.setTeams(teamsMember1);
		member2.setTeams(teamsMember2);
		List<Member> members = new ArrayList<Member>();
		members.add(member1);
		members.add(member2);
		
		when(memberService.getMembersWithTeams()).thenReturn(members);
		
		mockMvc.perform(get("/member/list"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("list-of-members"))
			   .andExpect(model().attribute("members", hasSize(members.size())))
			   .andExpect(model().attribute("members", hasItem(
                   allOf(
                       hasProperty("id", is(member1.getId())),
                       hasProperty("name", is(member1.getName()))                       
                   )
               )))
               .andExpect(model().attribute("members", hasItem(
                   allOf(
            		   hasProperty("id", is(member2.getId())),
                       hasProperty("name", is(member2.getName()))                       
                   )
               )));
	}
	
	@Test
	public void testEditMember() throws Exception {
		Team team1 = DataCreator.createTeam(1, "Team Test 1", 200);
		Team team2 = DataCreator.createTeam(2, "Team Test 2", 300);
		Set<Team> teamsMember = new HashSet<Team>();
		teamsMember.add(team1);		
		List<Team> teams = new ArrayList<Team>();
		teams.add(team1);
		teams.add(team2);
		
		Member member = DataCreator.createMember(1, "Member Test 1");
		member.setTeams(teamsMember);
		
		when(memberService.getMemberWithTeams(member.getId())).thenReturn(member);
		when(teamService.getTeams()).thenReturn(teams);
		
		mockMvc.perform(get("/member/edit/{id}", member.getId()))
			   .andExpect(status().isOk())
			   .andExpect(view().name("edit-member-form"))
			   .andExpect(model().attribute("member", hasProperty("id", is(member.getId()))))
			   .andExpect(model().attribute("member", hasProperty("name", is(member.getName()))))
			   .andExpect(model().attribute("member", hasProperty("teams", hasSize(teamsMember.size()))))
			   .andExpect(model().attribute("teams", hasSize(teams.size())))		 
			   .andExpect(model().attribute("teams", hasItem(
				   allOf(
	                 hasProperty("id", is(team1.getId())),
	                 hasProperty("name", is(team1.getName()))                       
				   )
		        )))
		        .andExpect(model().attribute("teams", hasItem(
		            allOf(
	        		 hasProperty("id", is(team2.getId())),
		             hasProperty("name", is(team2.getName()))                        
		            )
		         )));
	}
	
	@Test
	public void testEdittingMember() throws Exception {
		mockMvc.perform(post("/member/edit/{id}", 1))
			   .andExpect(status().isOk())
               .andExpect(model().attribute("messageCode", "mss.edit.member.ok"))
               .andExpect(view().name("home"));
	}

	@Test
	public void testDeleteMemberOk() throws Exception {
		mockMvc.perform(get("/member/delete/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(model().attribute("messageCode", "mss.delete.member.ok"))
				.andExpect(view().name("home"));				
	}	
	
}
