package com.sprhib.util;

import com.sprhib.dao.MemberDAO;
import com.sprhib.dao.OrganizationDAO;
import com.sprhib.dao.TeamDAO;
import com.sprhib.model.Member;
import com.sprhib.model.Organization;
import com.sprhib.model.Team;

public class DataCreator {

	public static Organization createOrganization(OrganizationDAO organizationDAO, String name) {
		Organization organization = new Organization();
		organization.setName(name);
		organizationDAO.addOrganization(organization);
		return organization;
	}

	public static Team createTeam(TeamDAO teamDAO, String name, int rating) {
		Team team = new Team();
		team.setName(name);
		team.setRating(rating);
		teamDAO.addTeam(team);
		return team;
	}

	public static Member createMember(MemberDAO memberDAO, String name) {
		Member member = new Member();
		member.setName(name);
		memberDAO.addMember(member);
		return member;
	}

	public static Organization createOrganization(Integer id, String name) {
		Organization organization = new Organization();
		organization.setId(id);
		organization.setName(name);
		return organization;
	}

	public static Team createTeam(Integer id, String name, Integer rating) {
		Team team = new Team();
		team.setId(id);
		team.setName(name);
		team.setRating(rating);
		return team;
	}

	public static Member createMember(Integer id, String name) {
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		return member;

	}
}
