package com.sprhib.ui.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

	protected static String URL_BASE = "http://localhost:8080/spr-mvc-hib";
	protected WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getSource() {
		return driver.getPageSource();
	}

	public static WebDriver getDriver() {
		//return new FirefoxDriver();
		/*FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.ipc.plugins.enabled", false);		
		File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary fb = new FirefoxBinary(pathToBinary);
		
		return  new FirefoxDriver(fb, profile);*/
		//MarionetteDriverManager.getInstance().setup();
		/*return new org.openqa.selenium.firefox.MarionetteDriver();*/
		//return new org.openqa.selenium.chrome.
		//System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
		//return new  org.openqa.selenium.
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		return new ChromeDriver();
		
	}

	public List<String> getTextsFromListElements(List<WebElement> webElements) {
		List<String> result = new ArrayList<String>();
		for (WebElement webElement : webElements) {
			result.add(webElement.getText().replace("\n", "::").replace("\r", "::"));
		}
		return result;
	}
	
	public static void closeDriver(WebDriver driver) {
		driver.quit();
				
	}
}
