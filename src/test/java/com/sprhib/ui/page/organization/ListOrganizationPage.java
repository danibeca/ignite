package com.sprhib.ui.page.organization;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class ListOrganizationPage extends PageObject {

	@FindBy(how = How.CLASS_NAME, using = "orgName")
	private List<WebElement> organizationNames;

	@FindBy(css = "a[href*='edit/1000.html']")
	private WebElement editButton;

	@FindBy(css = "a[href*='delete/1000.html']")
	private WebElement deleteButton;

	public ListOrganizationPage(WebDriver driver) {
		super(driver);
	}

	public EditOrganizationPage editOrganization() {
		editButton.click();
		return PageFactory.initElements(driver, EditOrganizationPage.class);
	}

	public HomePage clickDeleteOrganization() {
		deleteButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

	public List<String> getOrganizationNames() {
		return getTextsFromListElements(organizationNames);
	}

}
