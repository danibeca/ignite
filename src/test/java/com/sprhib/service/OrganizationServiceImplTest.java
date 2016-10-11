package com.sprhib.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sprhib.dao.OrganizationDAO;
import com.sprhib.model.Organization;
import com.sprhib.util.DataCreator;

public class OrganizationServiceImplTest {

	@InjectMocks
	private OrganizationServiceImpl organizationService;

	@Mock
	private OrganizationDAO organizationDAO;

	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetOrganization() {
		Integer id = 1;
		String name = "Organization #1";
		when(organizationDAO.getOrganization(id)).thenReturn(DataCreator.createOrganization(id, name));

		Organization result = organizationService.getOrganization(id);

		assertEquals(id, result.getId());
		assertEquals(name, result.getName());
	}

	@Test
	public void testGetOrganizations() {
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(DataCreator.createOrganization(1, "Organization 1"));
		organizations.add(DataCreator.createOrganization(2, "Organization 2"));
		when(organizationDAO.getOrganizations()).thenReturn(organizations);

		List<Organization> result = organizationService.getOrganizations();
		assertTrue(result.size() == 2);
	}

	@Test
	public void testDeleteFailOrganization() {
		int id = 2;
		when(organizationDAO.hasTeams(id)).thenReturn(true);

		assertFalse(organizationService.deleteOrganization(id));
	}

	@Test
	public void testDeleteOkOrganization() {
		int id = 2;
		when(organizationDAO.hasTeams(id)).thenReturn(false);

		assertTrue(organizationService.deleteOrganization(id));
	}
}
