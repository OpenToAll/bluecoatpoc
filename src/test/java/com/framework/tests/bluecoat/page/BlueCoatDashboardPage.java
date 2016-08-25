package com.framework.tests.bluecoat.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BlueCoatDashboardPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * @param option
	 * @throws Exception
	 * This function is used to change setting 
	 */
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
	
	/**
	 * @return
	 * @throws Exception
	 * This function used to get all titles of widgets
	 */
	public List<String> getAllWidgetTitles() throws Exception  {
		wait(10);
		List<String> listOfWidgetList = new ArrayList<String>();
		List<WebElement> listElement = getWebElements((By.xpath(getValue("bluecoastdashboardtitlewidget"))));
		for (WebElement resultItem : listElement){
			listOfWidgetList.add(resultItem.getText());
	    }
		log("Before Value " + Arrays.toString(listOfWidgetList.toArray()));
		ScrollToElement("bluecoastdashboarddownwidget");
		listElement = getWebElements((By.xpath(getValue("bluecoastdashboardtitlewidget"))));
		for (WebElement resultItem : listElement){
			listOfWidgetList.add(resultItem.getText());
	    }
		
		Set<String> hs = new HashSet<>();
		hs.addAll(listOfWidgetList);
		listOfWidgetList.clear();
		listOfWidgetList.addAll(hs);
		listOfWidgetList.removeAll(Arrays.asList("", null));
		ScrollToElement("bluecoastdashboardupwidget");
		log("Value " + Arrays.toString(listOfWidgetList.toArray()));
		return listOfWidgetList;
	}
	
	/**
	 * @param titleList
	 * @param title
	 * @return
	 * Return true if title is present in list, else false
	 */
	public boolean titleIsPresent(List<String> titleList, String title) {
		for (String widgetTitle : titleList) {
			if (widgetTitle.contains(title)) {
				return true;
			}
		}
		return false;
	}
	
	public String overViewPageReportValue(String item) throws Exception {
		/* Client IP, Protocol, Verdict, Location, , Site, Web Application, Subnet, User, Web Application Action */
		return getText("bluecoatoverviewpage", item);
	}
	
	/**
	 * @param option
	 * @throws Exception
	 */
	public void clickLinkInGrid(String option) throws Exception {
		click(By.xpath(getValue("bluecoatriskgrouptabletitle").replace("TITLE", option)), timeout);
		wait(20);
	}

	/**
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getValueInGrid(String value) throws Exception {
		String valueOfGrid = getText("bluecoatriskgrouptablevalue", value);
		log("Get Value " + valueOfGrid + " For " + value);
		return valueOfGrid;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String getToolTipValue() throws Exception {
		String tooltipValue = getText("bluecoatriskgroupcharttooltip").replace("Requests: ", "").trim();
		log("Tool Tip Value " + tooltipValue);
		return tooltipValue;
	}
	
	/**
	 * @param value
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * @throws InterruptedException
	 */
	public void validateImage() throws InterruptedException{
		clickBarGraph(barImage);
		wait(10);
		clickBarGraph(yellowPie);
		wait(300);
	}
	
	/**
	 * @param imageName
	 * @return
	 */
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
