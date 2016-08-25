package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BlueCoatDashboardPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickRiskSetting(String option) throws Exception {
		log("Selecting Option " + option + " From Risk Seting");
		click(By.xpath(getValue("bluecoatrisksetting")), timeout);
		wait(2);
		log("Select From Option");
		click(By.xpath(getValue("bluecoatinput")), timeout);
		wait(2);
		click(By.xpath(getValue("selecttype").replace("TITLE", option)), timeout);
		wait(2);
		click(By.xpath(getValue("bluecoatsettingsave")), timeout);
		wait(10);
		log("Successfully Selected Option From Risk Setting");
	}
	
	public void validateImage() throws InterruptedException{
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
