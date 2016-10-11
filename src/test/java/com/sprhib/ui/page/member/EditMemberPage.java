package com.sprhib.ui.page.member;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class EditMemberPage extends PageObject {

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(name = "teams")
	private WebElement teamsField;

	@FindBy(name = "btnEdit")
	private WebElement editButton;

	public EditMemberPage(WebDriver driver) {
		super(driver);
	}

	public String getMemberCurrentName() {
		return nameField.getAttribute("value");
	}

	public List<String> getMemberCurrentTeams() {
		List<WebElement> options = new Select(teamsField).getAllSelectedOptions();
		return getTextsFromListElements(options);
	}

	public void changeMemberName(String value) {
		nameField.clear();
		nameField.sendKeys(value);
	}

	public void addMemberTeam(String value) {
		new Select(teamsField).selectByVisibleText(value);
		teamsField.sendKeys(Keys.CONTROL);
	}

	public HomePage clickEditMember() {
		editButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

}
