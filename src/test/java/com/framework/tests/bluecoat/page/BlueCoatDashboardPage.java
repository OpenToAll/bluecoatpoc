package com.framework.tests.bluecoat.page;

import java.io.IOException;

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
	
	public String overViewPageReportValue(String item) throws Exception {
		/* Client IP, Protocol, Verdict, Location, , Site, Web Application, Subnet, User, Web Application Action */
		return getText("bluecoatoverviewpage", item);
	}
	
	public void clickLinkInGrid(String option) throws Exception {
		click(By.xpath(getValue("bluecoatriskgrouptabletitle").replace("TITLE", option)), timeout);
		wait(20);
	}

	public String getValueInGrid(String value) throws Exception {
		String valueOfGrid = getText("bluecoatriskgrouptablevalue", value);
		log("Get Value " + valueOfGrid + " For " + value);
		return valueOfGrid;
	}
	
	public String getToolTipValue() throws Exception {
		String tooltipValue = getText("bluecoatriskgroupcharttooltip").replace("Requests: ", "").trim();
		log("Tool Tip Value " + tooltipValue);
		return tooltipValue;
	}
	
	public boolean isValuePresentInGraph(String value) throws Exception {
		log("Click Graph To Get Tooltip " + value);
		wait(10);
		for (int i = 1 ; i<= 8; i++) {
			try {
				String element = getValue("bluecoatriskgroupchartgraph").replaceAll("INDEX", i +"");
				log("Element " + element);
				onMouseOver(By.xpath(element));
				click(By.xpath(element), 1);
				wait(1);
				String tooltipvalue = getToolTipValue();
				onMouseOver(By.xpath(getValue("bluecoatriskgrouptable")));
				
				log("Value of Tooptip" + tooltipvalue);
				if (tooltipvalue.contains(value)) {
					log("Successful Tooltip Value" +getToolTipValue() + "Assert Value "  + value);
					return true;
				}
			} catch(Exception e) {
				log("Skipped " + i);
			}
		}
		log("Value Not Present");
		return false;
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
