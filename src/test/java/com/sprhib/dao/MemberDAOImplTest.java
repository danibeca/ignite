package com.sprhib.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Member;
import com.sprhib.model.Team;
import com.sprhib.util.DataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@Transactional
public class MemberDAOImplTest {

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private TeamDAO teamDAO;

	private Member member;

	@Before
	public void initialize() {
		member = DataCreator.createMember(memberDAO, "John Doe");
		Set<Team> teams = new HashSet<Team>();
		teams.add(DataCreator.createTeam(teamDAO, "Team 1", 20));
		teams.add(DataCreator.createTeam(teamDAO, "Team 2", 40));
		member.setTeams(teams);
	}

	@Test
	public void testAddMember() {
		assertNotNull(member.getId());
		assertNotNull(member.getName());
		assertNotNull(member.getTeams().iterator().next().getId());
	}

	@Test
	public void testEditMember() {
		String editedName = "John Doe II";
		Member memberEdited = new Member();
		memberEdited.setId(member.getId());
		memberEdited.setName(editedName);

		memberDAO.updateMember(memberEdited);

		assertEquals(editedName, member.getName());
		assertNull(member.getTeams());
	}

	@Test
	public void testDeleteTeam() {
		memberDAO.deleteMember(member.getId());
		assertNull(memberDAO.getMember(member.getId()));
	}

	@Test
	public void testGetTeam() {
		assertNotNull(memberDAO.getMember(member.getId()));
	}

	@Test
	public void testGetTeams() {
		DataCreator.createMember(memberDAO, "Joe Doe III");
		assertTrue(memberDAO.getMembers().size() > 1);
	}
}
