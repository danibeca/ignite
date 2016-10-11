package com.sprhib.ui.page.member;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class AddMemberPage extends PageObject {

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(name = "teams")
	private WebElement teamsField;

	@FindBy(name = "btnAdd")
	private WebElement addButton;

	public AddMemberPage(WebDriver driver) {
		super(driver);
	}

	public void setMemberName(String name) {
		nameField.sendKeys(name);
	}

	public void addMemberTeam(String value) {
		new Select(teamsField).selectByVisibleText(value);
		teamsField.sendKeys(Keys.CONTROL);
	}

	public HomePage clickAddMember() {
		addButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
}
