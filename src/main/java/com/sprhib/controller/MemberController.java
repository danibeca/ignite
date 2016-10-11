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

import com.sprhib.model.Member;
import com.sprhib.model.Team;
import com.sprhib.service.MemberService;
import com.sprhib.service.TeamService;
import com.sprhib.util.editor.TeamEditor;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final String MESSAGE_CODE_ADD_MEMBER_OK = "mss.add.member.ok";
	private static final String MESSAGE_CODE_EDIT_MEMBER_OK = "mss.edit.member.ok";
	private static final String MESSAGE_CODE_DELETE_MEMBER_OK = "mss.delete.member.ok";

	@Autowired
	private MemberService memberService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private TeamEditor teamEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Team.class, teamEditor);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addMemberPage() {
		ModelAndView modelAndView = new ModelAndView("add-member-form");
		modelAndView.addObject("member", new Member());
		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addingMember(@ModelAttribute Member member) {
		ModelAndView modelAndView = new ModelAndView("home");
		memberService.addMember(member);
		modelAndView.addObject("messageCode", MESSAGE_CODE_ADD_MEMBER_OK);
		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listOfMembers() {
		ModelAndView modelAndView = new ModelAndView("list-of-members");
		List<Member> members = memberService.getMembersWithTeams();
		modelAndView.addObject("members", members);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editMemberPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-member-form");
		Member member = memberService.getMemberWithTeams(id);
		modelAndView.addObject("member", member);
		List<Team> teams = teamService.getTeams();
		modelAndView.addObject("teams", teams);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingMember(@ModelAttribute Member member, @PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		memberService.updateMember(member);
		modelAndView.addObject("messageCode", MESSAGE_CODE_EDIT_MEMBER_OK);
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteMember(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		memberService.deleteMember(id);
		modelAndView.addObject("messageCode", MESSAGE_CODE_DELETE_MEMBER_OK);
		return modelAndView;
	}
}
