package com.sprhib.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
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
import com.sprhib.service.OrganizationService;
import com.sprhib.util.DataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
public class OrganizationControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private OrganizationService organizationService;
	
	@InjectMocks
	private OrganizationController organizationController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(organizationController).build();		
	}

	@Test
	public void testAddOrganization() throws Exception {
		mockMvc.perform(get("/organization/add"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("add-organization-form"));
	}
	
	@Test
	public void testAddingOrganization() throws Exception {
		mockMvc.perform(post("/organization/add")) 
			   .andExpect(status().isOk())
               .andExpect(model().attribute("messageCode", "mss.add.org.ok"))
               .andExpect(view().name("home"));
	}
	
	@Test
	public void testListOrganizations() throws Exception {
		Organization org1 = DataCreator.createOrganization(1, "Organization Test 1");
		Organization org2 = DataCreator.createOrganization(2, "Organization Test 2");		
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(org1);
		organizations.add(org2);
		when(organizationService.getOrganizations()).thenReturn(organizations);
		
		mockMvc.perform(get("/organization/list"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("list-of-organizations"))
			   .andExpect(model().attribute("organizations", hasSize(organizations.size())))
			   .andExpect(model().attribute("organizations", hasItem(
                   allOf(
                       hasProperty("id", is(org1.getId())),
                       hasProperty("name", is(org1.getName()))                       
                   )
               )))
               .andExpect(model().attribute("organizations", hasItem(
                   allOf(
            		   hasProperty("id", is(org2.getId())),
                       hasProperty("name", is(org2.getName()))
                   )
               )));
	}
	
	@Test
	public void testEditOrganization() throws Exception {
		Organization org1 = DataCreator.createOrganization(1, "Organization Test 1");
		when(organizationService.getOrganization(org1.getId())).thenReturn(org1);
		
		mockMvc.perform(get("/organization/edit/{id}", org1.getId()))
			   .andExpect(status().isOk())
			   .andExpect(view().name("edit-organization-form"))
			   .andExpect(model().attribute("organization", hasProperty("id", is(org1.getId()))))
			   .andExpect(model().attribute("organization", hasProperty("name", is(org1.getName()))));
	}
	
	@Test
	public void testEdittingOrganization() throws Exception {
		mockMvc.perform(post("/organization/edit/{id}", 1))
			   .andExpect(status().isOk())
               .andExpect(model().attribute("messageCode", "mss.edit.org.ok"))
               .andExpect(view().name("home"));
	}

	@Test
	public void testDeleteOrganizationOk() throws Exception {
		int id = 1;
		when(organizationService.deleteOrganization(id)).thenReturn(true);
		
		mockMvc.perform(get("/organization/delete/{id}", id))
				.andExpect(status().isOk())
				.andExpect(model().attribute("messageCode", "mss.delete.org.ok"))
				.andExpect(view().name("home"));				
	}
	
	@Test
	public void testDeleteOrganizationFail() throws Exception {
		int id = 1;
		when(organizationService.deleteOrganization(id)).thenReturn(false);
		
		mockMvc.perform(get("/organization/delete/{id}", id))
				.andExpect(status().isOk())
				.andExpect(model().attribute("messageCode", "mss.delete.org.fail.constraint"))
				.andExpect(view().name("home"));				
	}
}
