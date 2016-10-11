package com.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sprhib.model.Organization;
import com.sprhib.service.OrganizationService;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

	private static final String MESSAGE_CODE_ADD_ORGANIZATION_OK = "mss.add.org.ok";
	private static final String MESSAGE_CODE_EDIT_ORGANIZATION_OK = "mss.edit.org.ok";
	private static final String MESSAGE_CODE_DELETE_ORGANIZATION_OK = "mss.delete.org.ok";
	private static final String MESSAGE_CODE_DELETE_ORGANIZATION_FAIL = "mss.delete.org.fail.constraint";

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addOrganizationPage() {
		ModelAndView modelAndView = new ModelAndView("add-organization-form");
		modelAndView.addObject("organization", new Organization());
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addingOrganization(@ModelAttribute Organization organization) {
		ModelAndView modelAndView = new ModelAndView("home");
		organizationService.addOrganization(organization);
		modelAndView.addObject("messageCode", MESSAGE_CODE_ADD_ORGANIZATION_OK);
		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listOfOrganizations() {
		ModelAndView modelAndView = new ModelAndView("list-of-organizations");
		List<Organization> organizations = organizationService.getOrganizations();
		modelAndView.addObject("organizations", organizations);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editOrganizationPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-organization-form");
		Organization organization = organizationService.getOrganization(id);
		modelAndView.addObject("organization", organization);
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView edditingOrganization(@ModelAttribute Organization organization, @PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		organizationService.updateOrganization(organization);
		modelAndView.addObject("messageCode", MESSAGE_CODE_EDIT_ORGANIZATION_OK);
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrganization(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		boolean status = organizationService.deleteOrganization(id);
		if (status) {
			modelAndView.addObject("messageCode", MESSAGE_CODE_DELETE_ORGANIZATION_OK);
		} else {
			modelAndView.addObject("messageCode", MESSAGE_CODE_DELETE_ORGANIZATION_FAIL);
		}

		return modelAndView;
	}
}
