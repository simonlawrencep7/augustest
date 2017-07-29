package com.org.reuse;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class Keywords {

	public static WebDriver driver;
	WebElement element;
	String xpathValue;

	public Keywords(WebDriver driver) {
		Keywords.driver = driver;
	}

	public void setText(String name, String value) {

		element = driver.findElement(By.name(name));
		element.clear();
		element.sendKeys(value);
		System.out.println("Type in : " + value);
	}
	
	public void clickObjectByXpath(String attrib, String value, Boolean isExactValue) {
		try {

			if (isExactValue) {
				xpathValue = "//*[@" + attrib + "='" + value + "']";
			} else {
				xpathValue = "//*[contains(@" + attrib + ",'" + value + "')]";
			}
			if (xpathValue.contains("@text")) {
				xpathValue = xpathValue.replace("@text", "text()");
			}
			element = driver.findElement(By.xpath(xpathValue));
			element.click();
			writeToLog(true, value + ":: got a click");

		} catch (Exception e) {
			writeToLog(false, value + ":: is not clicked");
		}

	}

	public void switchToDefault() {
		try {
			driver.switchTo().defaultContent();
			writeToLog(true, "default Content is loaded in the webpage");
		} catch (Exception e) {
			e.printStackTrace();
			writeToLog(false, "default Content is not loaded in the webpage");
		}
	}

	public void comboSelectByValue(By field, String value, String desc) {
		try {
			Select element = new Select(driver.findElement(field));
			element.selectByVisibleText(value);
			Reporter.log(value + " is selected in  " + desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveToElement(By strobj) {
		try {
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(strobj);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			writeToLog(false, "Element is not focused in the webpage");
		}
	}

	public void getApplication(String url) {
		try {
			driver.get(url);
			driver.manage().window().maximize();
			writeToLog(true, "Application url not opened in the browser");
		} catch (Exception e) {
			e.printStackTrace();
			writeToLog(false, "Application url: " + url + " is not opened in the browser");
		}
	}
	
	public boolean findWebElement(String attrib, String value,
			Boolean isExactValue) {
		try {
			if (isExactValue) {
				xpathValue = "//*[@" + attrib + "='" + value + "']";
			} else {
				xpathValue = "//*[contains(@" + attrib + ",'" + value + "')]";
			}
			if (xpathValue.contains("@text")) {
				xpathValue = xpathValue.replace("@text", "text()");
			}
			else if(attrib.equalsIgnoreCase("css"))
			{
				xpathValue = value;
			}
			element = driver.findElement(By.xpath(xpathValue));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> getText(By objElement) {
		List<String> values = new ArrayList<String>();
		try {
			List<WebElement> list = driver.findElements(objElement);
			for (WebElement element : list) {
				String value = element.getText().trim();
				if (value.equals(""))
					value = element.getAttribute("value");
				values.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}

/**/
	public void writeToLog(boolean state, String msg) {
		Reporter.log(msg);
		if (state) {
			Assert.assertTrue(state, msg);
		}
		else{
			Assert.assertFalse(state, msg);
		}
		
	}

	
}
