package com.sprhib.ui.page.team;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class AddTeamPage extends PageObject {

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(name = "rating")
	private WebElement ratingField;

	@FindBy(name = "organization")
	private WebElement organizationField;

	@FindBy(name = "btnAdd")
	private WebElement addButton;

	public AddTeamPage(WebDriver driver) {
		super(driver);
	}

	public void setTeamName(String name) {
		nameField.sendKeys(name);
	}

	public void setTeamRating(String rating) {
		ratingField.sendKeys(rating);
	}

	public void setTeamOrganization(String value) {
		new Select(organizationField).selectByVisibleText(value);
		organizationField.sendKeys(Keys.CONTROL);
	}

	public HomePage clickAddTeam() {
		addButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
}
