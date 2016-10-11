package com.sprhib.ui.page.member;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class ListMemberPage extends PageObject {

	@FindBy(how = How.CLASS_NAME, using = "memberName")
	private List<WebElement> memberNames;

	@FindBy(how = How.CLASS_NAME, using = "memberTeams")
	private List<WebElement> memberTeams;

	@FindBy(css = "a[href*='edit/2000.html']")
	private WebElement editButton;

	@FindBy(css = "a[href*='delete/2000.html']")
	private WebElement deleteButton;

	public ListMemberPage(WebDriver driver) {
		super(driver);
	}

	public EditMemberPage clickEditMember() {
		editButton.click();
		return PageFactory.initElements(driver, EditMemberPage.class);
	}

	public HomePage clickDeleteMember() {
		deleteButton.click();
		return PageFactory.initElements(driver, HomePage.class);
	}

	public List<String> getMemberNames() {
		return getTextsFromListElements(memberNames);
	}

	public List<String> getMemberTeams() {
		return getTextsFromListElements(memberTeams);
	}
	
}
