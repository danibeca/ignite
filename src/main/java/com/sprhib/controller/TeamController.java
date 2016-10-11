package com.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Organization;
import com.sprhib.model.Team;
import com.sprhib.service.OrganizationService;
import com.sprhib.service.TeamService;
import com.sprhib.util.editor.OrganizationEditor;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

	private static final String MESSAGE_CODE_ADD_TEAM_OK = "mss.add.team.ok";
	private static final String MESSAGE_CODE_EDIT_TEAM_OK = "mss.edit.team.ok";
	private static final String MESSAGE_CODE_DELETE_TEAM_OK = "mss.delete.team.ok";
	
	@Autowired
	private TeamService teamService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private OrganizationEditor organizationEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Organization.class, organizationEditor);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addTeamPage() {
		ModelAndView modelAndView = new ModelAndView("add-team-form");
		modelAndView.addObject("team", new Team());
		List<Organization> organizations = organizationService.getOrganizations();
		modelAndView.addObject("organizations", organizations);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addingTeam(@ModelAttribute Team team) {
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.addTeam(team);
		modelAndView.addObject("messageCode", MESSAGE_CODE_ADD_TEAM_OK);
		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listOfTeams() {
		ModelAndView modelAndView = new ModelAndView("list-of-teams");
		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editTeamPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-team-form");
		Team team = teamService.getTeam(id);
		modelAndView.addObject("team", team);
		List<Organization> organizations = organizationService.getOrganizations();
		modelAndView.addObject("organizations", organizations);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.updateTeam(team);
		modelAndView.addObject("messageCode", MESSAGE_CODE_EDIT_TEAM_OK);
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		teamService.deleteTeam(id);
		modelAndView.addObject("messageCode", MESSAGE_CODE_DELETE_TEAM_OK);
		return modelAndView;
	}
}
