package com.bluecoat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		goToURL(getValue("loginurl"));
		wait(10);
		log("Enter User Name And Password");
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginuser")), getValue("user"));
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginpassword")), getValue("password"));
		click(By.xpath(getValue("bluecoatsubmit")), timeout);
		wait(20);
		log("Submit Successful");
	}

}
