package com.org.util.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class Driver {

	public static WebDriver driver;

	public void initializeDriver() {
		String browser = getProperty("Browser");
		if ("chrome".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.chrome.driver","./resources/lib/chromedriver.exe");
			driver = new ChromeDriver();
			Reporter.log("Chrome Browser launched");
		} else if ("firefox".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.firefox.marionette","./resources/lib/geckodriver.exe");
			driver = new FirefoxDriver();
			Reporter.log("Firefox Browser launched");
		} else
			Reporter.log("No Browser available");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void quitDriver() {
		driver.quit();
	}

	public String getProperty(String name) {
		Properties prop = new Properties();
		InputStream input = null;
		String propertyName = null;
		try {
			input = new FileInputStream(System.getProperty("user.dir") + "/resources/property/core.properties");
			prop.load(input);
			propertyName = prop.getProperty(name);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return propertyName;
	}

}
