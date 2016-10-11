package com.sprhib.ui.page.team;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class ListTeamPage extends PageObject {

	@FindBy(how = How.CLASS_NAME, using = "teamName")
	private List<WebElement> teamNames;

	@FindBy(how = How.CLASS_NAME, using = "teamRating")
	private List<WebElement> teamRatings;

	@FindBy(how = How.CLASS_NAME, using = "teamOrganization")
	private List<WebElement> teamOrganizations;

	@FindBy(css = "a[href*='edit/1000.html']")
	private WebElement editButton;

	@FindBy(css = "a[href*='delete/1000.html']")
	private WebElement deleteButton;

	public ListTeamPage(WebDriver driver) {
		super(driver);
	}

	public EditTeamPage editTeam() {
		editButton.click();
		return PageFactory.initElements(driver, EditTeamPage.class);
	}

	public HomePage clickDeleteTeam() {
		deleteButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

	public List<String> getTeamNames() {
		return getTextsFromListElements(teamNames);
	}

	public List<String> getTeamRatings() {
		return getTextsFromListElements(teamRatings);
	}

	public List<String> getTeamOrganizationNames() {
		return getTextsFromListElements(teamOrganizations);
	}

}
