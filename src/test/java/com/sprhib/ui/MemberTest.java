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
import com.sprhib.ui.page.member.AddMemberPage;
import com.sprhib.ui.page.member.EditMemberPage;
import com.sprhib.ui.page.member.ListMemberPage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = BaseTestConfig.class)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/MemberBeforeTestRun.sql"), 
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/MemberAfterTestRun.sql") })
public class MemberTest {

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
	public void whenUserAddNewMember() {
		AddMemberPage addPage = homePage.clickAddMember();
		String name = "@New Member Test" + Math.round(Math.random() * 100);
		String team = "@New Team Test 1001";
		addPage.setMemberName(name);
		addPage.addMemberTeam(team);
		homePage = addPage.clickAddMember();
		Assert.assertTrue(homePage.getResult().contains("Member was successfully added."));
		ListMemberPage listPage = homePage.clickListMembers();
		Assert.assertTrue(listPage.getMemberNames().contains(name));
		Assert.assertTrue(listPage.getMemberTeams().contains(team));

	}

	@Test
	public void whenUserListMembers() {
		ListMemberPage listPage = homePage.clickListMembers();
		Assert.assertTrue(listPage.getMemberNames().contains("@New Member Test 2000"));
	}

	@Test
	public void whenUserEditMember() {
		ListMemberPage listPage = homePage.clickListMembers();
		EditMemberPage editPage = listPage.clickEditMember();
		Assert.assertTrue(editPage.getMemberCurrentName().contains("@New Member Test 2000"));
		Assert.assertTrue(editPage.getMemberCurrentTeams().contains("@New Team Test 1000"));

		String editedName = "@New Member Test 1000 Edited";
		String newTeam = "@New Team Test 1001";
		editPage.changeMemberName(editedName);
		editPage.addMemberTeam(newTeam);
		homePage = editPage.clickEditMember();
		Assert.assertTrue(homePage.getResult().contains("Member was successfully edited."));

		listPage = homePage.clickListMembers();
		Assert.assertTrue(listPage.getMemberNames().contains(editedName));
		Assert.assertTrue(listPage.getMemberTeams().contains("@New Team Test 1000::@New Team Test 1001")
						||listPage.getMemberTeams().contains("@New Team Test 1001::@New Team Test 1000"));
	}

	@Test
	public void whenUserDeleteMember() {
		ListMemberPage listPage = homePage.clickListMembers();
		homePage = listPage.clickDeleteMember();
		Assert.assertTrue(homePage.getResult().contains("Member was successfully deleted."));

		listPage = homePage.clickListMembers();
		Assert.assertFalse(listPage.getMemberNames().contains("@New Member Test 2000"));

	}

}
