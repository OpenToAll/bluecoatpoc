package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import com.framework.core.SeleniumLibrary;

import junit.framework.Assert;

public class LoginPage extends SeleniumLibrary {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login() throws Exception{
		goToURL("https://portal.qa3.bluecoatcloud.com/login.jsp");
		wait(20);
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginuser")), "Raghunandan.dixit@itcinfotech.com");
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginpassword")), "Itcinfotech@123");
		click(By.xpath(getValue("bluecoatsubmit")), timeout);
		wait(20);
		clickBarGraph(barImage);
		wait(10);
		clickBarGraph(yellowPie);
		wait(300);
	}

	 public boolean clickBarGraph(String imageName){
		 log("Click image " + imageName);
		 Screen s = new Screen();

		 if(s.exists(imageName)!=null){
			 try {
				 s.click(new Pattern (imageName).targetOffset(0, 0));
				 return true;
			 } catch (Exception e) {
				 System.out.println("Message " + e.getMessage());
				 e.printStackTrace();
			 }
		 } else{
			 log("Image not found");
		 }
		 return false;

	 }

}
