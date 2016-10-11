package com.sprhib.ui.page.organization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class AddOrganizationPage extends PageObject {

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(name = "btnAdd")
	private WebElement addButton;

	public AddOrganizationPage(WebDriver driver) {
		super(driver);
	}

	public void setOrganizationName(String name) {
		nameField.sendKeys(name);
	}

	public HomePage clickAddOrganization() {
		addButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}
}
