package com.sprhib.ui.page.organization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class EditOrganizationPage extends PageObject {

	@FindBy(name = "name")
	private WebElement nameField;

	@FindBy(name = "btnEdit")
	private WebElement editButton;

	public EditOrganizationPage(WebDriver driver) {
		super(driver);
	}

	public String getOrganizationName() {
		return nameField.getAttribute("value");
	}

	public void changeOrganizationName(String value) {
		nameField.clear();
		nameField.sendKeys(value);		
	}

	public HomePage clickEditOrganization() {
		editButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

}
