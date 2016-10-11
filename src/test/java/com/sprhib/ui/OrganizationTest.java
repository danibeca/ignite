package com.sprhib.ui;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sprhib.init.BaseTestConfig;
import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;
import com.sprhib.ui.page.organization.AddOrganizationPage;
import com.sprhib.ui.page.organization.EditOrganizationPage;
import com.sprhib.ui.page.organization.ListOrganizationPage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/OrganizationBeforeTestRun.sql"), 
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/OrganizationAfterTestRun.sql") })
public class OrganizationTest {

	private WebDriver driver;
	private HomePage homePage;

	@Before
	public void initialize() {
		driver = PageObject.getDriver();
		homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.open();
	}

	@After
	public void closeBrowser() {
		PageObject.closeDriver(this.driver);
	}

	@Test
	public void whenUserAddNewOrganization() {
		AddOrganizationPage addPage = homePage.clickAddOrganization();
		String organizationName = "@New Organization Test" + Math.round(Math.random() * 100);
		addPage.setOrganizationName(organizationName);
		homePage = addPage.clickAddOrganization();
		Assert.assertTrue(homePage.getResult().contains("Organization was successfully added."));
		ListOrganizationPage listPage = homePage.clickListOrganizations();
		Assert.assertTrue(listPage.getOrganizationNames().contains(organizationName));
	}

	@Test
	public void whenUserListOrganization() {
		ListOrganizationPage listPage = homePage.clickListOrganizations();
		Assert.assertTrue(listPage.getOrganizationNames().contains("@New Organization Test 1000"));
	}

	@Test
	public void whenUserEditOrganization() {
		ListOrganizationPage listPage = homePage.clickListOrganizations();
		EditOrganizationPage editPage = listPage.editOrganization();
		Assert.assertTrue(editPage.getOrganizationName().contains("@New Organization Test 1000"));

		String editedName = "@New Organization Test 1000 Edited";
		editPage.changeOrganizationName(editedName);
		homePage = editPage.clickEditOrganization();
		Assert.assertTrue(homePage.getResult().contains("Organization was successfully edited."));

		listPage = homePage.clickListOrganizations();
		Assert.assertTrue(listPage.getOrganizationNames().contains(editedName));
	}

	@Test
	public void whenUserDeleteOrganization() {
		ListOrganizationPage listPage = homePage.clickListOrganizations();
		homePage = listPage.clickDeleteOrganization();
		Assert.assertTrue(homePage.getResult().contains("Organization was successfully deleted."));

		listPage = homePage.clickListOrganizations();
		Assert.assertFalse(listPage.getOrganizationNames().contains("@New Organization Test 1000"));

	}
}
