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
	protected String userId = "8631";
	protected String password = "Itcinfotech9(";

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
		
		wait(500);
	}

	 public boolean clickGraph(String path, int waitTimeInSeconds ){
		 Screen s = new Screen();

		 if(s.exists("")!=null){
			 try {
				// s.type(path);
				 return true;
			 } catch (Exception e) {
				 e.printStackTrace();
			 }

		 }
		 else{
		 }
		 return false;

	 }
	 
}
