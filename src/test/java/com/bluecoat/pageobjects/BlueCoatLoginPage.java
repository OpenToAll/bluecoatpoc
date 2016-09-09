package com.bluecoat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bluecoat.library.Global;

public class BlueCoatLoginPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * @throws Exception
	 */
	public void login() throws Exception{
		goToURL(Global.executionDetailsMap.get("Url"));
		wait(10);
		log("Enter User Name And Password");
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginuser")), Global.executionDetailsMap.get("Username"));
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginpassword")), Global.executionDetailsMap.get("Password"));
		click(By.xpath(getValue("bluecoatsubmit")), timeout);
		wait(20);
		log("Submit Successful");
	}

}
