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
import com.sprhib.ui.page.team.AddTeamPage;
import com.sprhib.ui.page.team.EditTeamPage;
import com.sprhib.ui.page.team.ListTeamPage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/TeamBeforeTestRun.sql"), 
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/TeamAfterTestRun.sql") })
public class TeamTest {

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
	public void whenUserAddNewTeam() {
		AddTeamPage addPage = homePage.clickAddTeam();
		String name = "@New Team Test" + Math.round(Math.random() * 100);
		String rating = "50";
		addPage.setTeamName(name);
		addPage.setTeamRating(rating);
		homePage = addPage.clickAddTeam();
		Assert.assertTrue(homePage.getResult().contains("Team was successfully added."));
		ListTeamPage listPage = homePage.clickListTeams();
		Assert.assertTrue(listPage.getTeamNames().contains(name));
		Assert.assertTrue(listPage.getTeamRatings().contains(rating));
		Assert.assertTrue(listPage.getTeamOrganizationNames().contains("@New Organization Test 1000"));
	}

	@Test
	public void whenUserListTeam() {
		ListTeamPage listPage = homePage.clickListTeams();
		Assert.assertTrue(listPage.getTeamNames().contains("@New Team Test 1000"));
	}

	@Test
	public void whenUserEditTeam() {
		ListTeamPage listPage = homePage.clickListTeams();
		EditTeamPage editPage = listPage.editTeam();
		Assert.assertTrue(editPage.getTeamCurrentName().contains("@New Team Test 1000"));
		Assert.assertTrue(editPage.getTeamCurrentOrganization().contains("@New Organization Test 1000"));
		Assert.assertTrue(editPage.getTeamCurrentRating().contains("22"));

		String editedName = "@New Team Test 1000 Edited";
		String editedOrganization = "@New Organization Test 1001";
		String editedRating = "500";
		editPage.changeTeamName(editedName);
		editPage.changeTeamRating(editedRating);
		editPage.changeTeamOrganization(editedOrganization);
		homePage = editPage.clickEditTeam();
		Assert.assertTrue(homePage.getResult().contains("Team was successfully edited."));

		listPage = homePage.clickListTeams();
		Assert.assertTrue(listPage.getTeamNames().contains(editedName));
		Assert.assertTrue(listPage.getTeamOrganizationNames().contains(editedOrganization));
		Assert.assertTrue(listPage.getTeamRatings().contains(editedRating));
	}

	@Test
	public void whenUserDeleteTeam() {
		ListTeamPage listPage = homePage.clickListTeams();
		homePage = listPage.clickDeleteTeam();
		Assert.assertTrue(homePage.getResult().contains("Team was successfully deleted."));

		listPage = homePage.clickListTeams();
		Assert.assertFalse(listPage.getTeamNames().contains("@New Team Test 1000"));

	}
}
