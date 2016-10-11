package com.sprhib.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.member.AddMemberPage;
import com.sprhib.ui.page.member.ListMemberPage;
import com.sprhib.ui.page.organization.AddOrganizationPage;
import com.sprhib.ui.page.organization.ListOrganizationPage;
import com.sprhib.ui.page.team.AddTeamPage;
import com.sprhib.ui.page.team.ListTeamPage;

public class HomePage extends PageObject {

	@FindBy(how = How.LINK_TEXT, using = "Organization list")
	private WebElement listOrganizationLink;

	@FindBy(how = How.LINK_TEXT, using = "Add new organization")
	private WebElement addOrganizationLink;

	@FindBy(how = How.LINK_TEXT, using = "Team list")
	private WebElement listTeamLink;

	@FindBy(how = How.LINK_TEXT, using = "Add new team")
	private WebElement addTeamLink;

	@FindBy(how = How.LINK_TEXT, using = "Member list")
	private WebElement listMemberLink;

	@FindBy(how = How.LINK_TEXT, using = "Add new member")
	private WebElement addMemberLink;

	@FindBy(tagName = "h3")
	private WebElement result;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(URL_BASE);
	}

	public AddOrganizationPage clickAddOrganization() {
		addOrganizationLink.click();
		return PageFactory.initElements(driver, AddOrganizationPage.class);
	}

	public ListOrganizationPage clickListOrganizations() {
		listOrganizationLink.click();
		return PageFactory.initElements(driver, ListOrganizationPage.class);
	}

	public AddTeamPage clickAddTeam() {
		addTeamLink.click();
		return PageFactory.initElements(driver, AddTeamPage.class);
	}

	public ListTeamPage clickListTeams() {
		listTeamLink.click();
		return PageFactory.initElements(driver, ListTeamPage.class);
	}

	public AddMemberPage clickAddMember() {
		addMemberLink.click();
		return PageFactory.initElements(driver, AddMemberPage.class);
	}

	public ListMemberPage clickListMembers() {
		listMemberLink.click();
		return PageFactory.initElements(driver, ListMemberPage.class);
	}

	public String getResult() {
		return result.getText();
	}

}
