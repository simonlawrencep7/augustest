package com.org.home.methods;

import java.util.*;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.org.constants.webConstant;
import com.org.reuse.Keywords;
import com.org.util.framework.Driver;
import com.org.util.framework.ExcelHelper;

public class HomePage extends Keywords implements webConstant{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	Driver driverProperty = new Driver();
	ExcelHelper excelSheet = new ExcelHelper();
	String sheetName = "TestCases";
	
	
	public void initializeExcel(String sheetName)
	{
		excelSheet.loadExcelsheet(sheetName);	
		  
	}
	public void launchapplication()
	{
		String url = driverProperty.getProperty("ApplicationUrl");
		getApplication(url);
		if(findWebElement(campaign.split("~")[0], campaign.split("~")[1], true))
		{
			clickObjectByXpath(campaign.split("~")[0], campaign.split("~")[1], true);
		}
		if(findWebElement(logo.split("~")[0], logo.split("~")[1], true))
		{
			writeToLog(true,"Application Loaded Successfuly");
		}
	}
	
	public void searchByProductName(String testId, String product)
	{
		initializeExcel("TestCases");
		String productName = excelSheet.getData(testId, sheetName, product);
		if(findWebElement(txtSearch.split("~")[0], txtSearch.split("~")[1], true));
		{
			setText(txtSearch.split("~")[1],productName);
			clickObjectByXpath(btnSearch.split("~")[0], btnSearch.split("~")[1], true);
			writeToLog(true,"Product Search is Successfull");
		}
	}
	
	public void getProductResults()
	{
		
		By element = By.xpath("//a[@class='history-item product ']");
		List<String> productName = getText(element);
		for (String strProductName : productName) {
			Reporter.log("Prodct Name: \"" + strProductName);
		}
	}
}
