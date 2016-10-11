package com.sprhib.ui.page.team;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class EditTeamPage extends PageObject {

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(name = "rating")
	private WebElement ratingField;

	@FindBy(name = "organization")
	private WebElement organizationField;

	@FindBy(name = "btnEdit")
	private WebElement editButton;

	public EditTeamPage(WebDriver driver) {
		super(driver);
	}

	public String getTeamCurrentName() {
		return nameField.getAttribute("value");
	}

	public String getTeamCurrentOrganization() {
		WebElement option = new Select(organizationField).getFirstSelectedOption();
		return option.getText();
	}

	public String getTeamCurrentRating() {
		return ratingField.getAttribute("value");
	}

	public void changeTeamName(String value) {
		nameField.clear();
		nameField.sendKeys(value);
	}

	public void changeTeamRating(String value) {
		ratingField.clear();
		ratingField.sendKeys(value);
	}

	public void changeTeamOrganization(String value) {
		new Select(organizationField).selectByVisibleText(value);
		organizationField.sendKeys(Keys.CONTROL);
	}

	public HomePage clickEditTeam() {
		editButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

}
