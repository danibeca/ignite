package com.sprhib.ui;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.sprhib.ui.page.HomePage;
import com.sprhib.ui.page.PageObject;

public class HomeTest {

	private WebDriver driver;
	private HomePage homePage;

	@Before
	public void initialize() {
		driver = PageObject.getDriver();
		homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.open();
	}

	@After
	public void closeBrowser() {
		PageObject.closeDriver(this.driver);
	}

	@Test
	public void whenPageIsLoaded() {
		assertEquals("Home page", homePage.getTitle());
	}
}
