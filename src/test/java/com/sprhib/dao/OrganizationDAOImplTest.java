package com.sprhib.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.model.Organization;
import com.sprhib.util.DataCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@Transactional
public class OrganizationDAOImplTest {
	@Autowired
	private OrganizationDAO organizationDAO;

	private Organization organization;

	@Before
	public void initialize() {
		organization = DataCreator.createOrganization(organizationDAO, "Organization TEST");
	}

	@Test
	public void testAddOrganization() {
		assertNotNull(organization.getId());
		assertNotNull(organization.getName());
	}

	@Test
	public void testEditOrganization() {
		String editedName = "Organization EDITED";
		Organization organizationEdited = new Organization();
		organizationEdited.setId(organization.getId());
		organizationEdited.setName(editedName);

		organizationDAO.updateOrganization(organizationEdited);

		assertEquals(editedName, organization.getName());
	}

	@Test
	public void testDeleteOrganization() {
		organizationDAO.deleteOrganization(organization.getId());
		assertNull(organizationDAO.getOrganization(organization.getId()));
	}

	@Test
	public void testGetOrganization() {
		assertNotNull(organizationDAO.getOrganization(organization.getId()));
	}

	@Test
	public void testGetOrganizations() {
		DataCreator.createOrganization(organizationDAO, "Organization 2");
		assertTrue(organizationDAO.getOrganizations().size() > 1);
	}
}
