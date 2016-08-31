package com.bluecoat.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BlueCoatDashboardPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";
	List<String> dayGraphDateAndValue;
	List<String> dayGridDateAndValue;
	List<String> webBarGraphSiteAndValue;
	List<String> webBarGrideSiteAndValue;
	
	public BlueCoatDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToDashboard() throws Exception {
		goToURL(getValue("dashboardurl"));
		wait(10);
	}
	
	/**
	 * @param option
	 * @throws Exception
	 * This function is used to change setting 
	 */
	public void clickOverviewSetting(String option, String title ) throws Exception {
		log("Selecting Option " + option + " From Risk Seting");
		click(By.xpath(getValue("bluecoatoverviewtitlesetting").replace("TITLE", title)), timeout);
		wait(2);
		log("Select From Option");
		click(By.xpath(getValue("bluecoatinput")), timeout);
		wait(2);
		click(By.xpath(getValue("selecttype").replace("TITLE", option)), timeout);
		wait(2);
		click(By.xpath(getValue("bluecoatsettingsave")), timeout);
		wait(5);
		log("Successfully Selected Option From Risk Setting");
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
		wait(5);
		log("Successfully Selected Option From Risk Setting");
	}
	
	public void clickDayGraphAndGetValue() throws Exception {
		log("Click Day Graph And Value"); 
		wait(10);
		dayGraphDateAndValue = new ArrayList<String>();
		String requestStrTooltipValue, dayTooltipDay;
		List<WebElement> dayGraphPoints = getWebElements((By.xpath(getValue("bluecoatoverviewdaygraphpoint"))));
		for (WebElement dayGrapnPoint : dayGraphPoints){
			onMouseOver(dayGrapnPoint);
			dayGrapnPoint.click();
			requestStrTooltipValue = getText("bluecoattooltiprequest").replaceAll("Requests: ", "").trim();
			dayTooltipDay = getText("bluecoattooltipday").replaceAll("Day: ", "").trim();
			dayGraphDateAndValue.add( dayTooltipDay +"@" + requestStrTooltipValue);
			log("***** REquestSTr " + requestStrTooltipValue + " Day value " +  dayTooltipDay + "****");
			wait(2);
	    } 
		log("Value Of ToolTip " + Arrays.toString(dayGraphDateAndValue.toArray()));
	}
	
	public void clickWebBrowsingSiteFullReport() throws Exception {
		log("Click Day Graph And Value"); 
		click(By.xpath(getValue("bluecoatwebbrowsingfullreportlink")), timeout);
		wait(20);
	}
	
	public void getGridValueOfWebBrowserSite() throws Exception {
		log("Click Day Graph And Value"); 
		webBarGrideSiteAndValue = new ArrayList<String>();
		 int count = getWebElements((By.xpath(getValue("bluecoatwebbrowsingsitegridcount")))).size();
		for (int i = 1; i <= count; i++) {
			String siteColumn= getText("bluecoatwebbrowsingsitegridvalue", i+"", "1");
			String viewCountColumn = getText("bluecoatwebbrowsingsitegridvalue", i+"", "2");
			webBarGrideSiteAndValue.add( siteColumn +"@" + viewCountColumn);
			log("Day Column :" + siteColumn + "Value Column " + viewCountColumn);
		}
		log("Value Of Grid " + Arrays.toString(webBarGrideSiteAndValue.toArray()));
		
	}

	public void clickFullReportWebBrowserSitePoints() throws Exception {
		log("Click Day Graph And Value"); 
		wait(5);
		webBarGraphSiteAndValue = new ArrayList<String>();
		String site, viewPageCount;
		String element = getValue("bluecoatwebbrowsingfullreportgraph");
		List<WebElement> webSiteGraphPoints = getWebElements((By.xpath(element)));
		log("Size Of WebSite" + webSiteGraphPoints.size());
		for (WebElement webGrapnPoint : webSiteGraphPoints){
			try {
				ScrollToElement(webGrapnPoint);
				onMouseOver(webGrapnPoint);
				webGrapnPoint.click();
				site = getText("bluecoatwebbrowsingfulltooltipsite").replaceAll("Site: ", "").trim();
				viewPageCount = getText("bluecoatwebbrowsingfulltooltipviewcount").replaceAll("Page Views: ", "").trim();
				webBarGraphSiteAndValue.add( site +"@" + viewPageCount);
				log("***** Site " + site + " Page Count" +  viewPageCount + "****");
				wait(2);
			} catch (Exception e) {
				log("Error " + e.getMessage());
			}
	    } 
		log("Value Of ToolTip " + Arrays.toString(webBarGraphSiteAndValue.toArray()));
	}
	
	public boolean verifyGridAndGraphOfWebBrowserSite() throws Exception  {
		log("Value Of Web Browser Grid " + Arrays.toString(webBarGrideSiteAndValue.toArray())
			+ " Value of  Web Browser Graph " + webBarGraphSiteAndValue  );
		return webBarGrideSiteAndValue.containsAll(webBarGraphSiteAndValue);
	}
	
	
	public void getDayGRidValue() throws Exception {
		log("Get Grid Value "); 
		dayGridDateAndValue = new ArrayList<String>();
		 int count = getWebElements((By.xpath(getValue("bluecoatoverviewdaysgridcount")))).size();
		for (int i = 1; i <= count; i++) {
			
			String dayColumn= getText("bluecoatoverviewdaysgridvalue", i+"", "1");
			String valueColumn = getText("bluecoatoverviewdaysgridvalue", i+"", "2");
			dayGridDateAndValue.add( dayColumn +"@" + valueColumn);
			log("Day Column :" + dayColumn + "Value Column " + valueColumn);
		}
		log("Value Of Grid " + Arrays.toString(dayGridDateAndValue.toArray()));
	}
	
	public boolean verifyDayGridAndGraph() throws Exception  {
		log("Value Of Day Grid " + Arrays.toString(dayGridDateAndValue.toArray())
			+ " Value of Graph " + dayGraphDateAndValue);
		return CollectionUtils.isEqualCollection(dayGridDateAndValue, dayGraphDateAndValue);
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
		
		Set<String> hs = new HashSet<String>();
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
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String getToolTipValueforMonthCircle() throws Exception {
		String tooltipMonth = getText("reportchartCircletooltipMonth").replace("Month: ", "").trim();
		String tooltipValue = getText("reportchartCircletooltipValue").replace("Requests: ", "").trim();
		log("Tool Tip Value " + tooltipMonth + " " + tooltipValue);
		return  tooltipMonth + " " + tooltipValue;
	}
	
	/**
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<String> getGridValueByMonth() throws Exception {
		log("Click Graph To Get Tooltip ");
		List<String> itemsToAdd = new ArrayList<String>();
		wait(10); 
		for (int i = 1 ; i<= 12; i++) {
			try {
				String elementValue = getValue("reportpageMonthCircle").replaceAll("INDEX", i +"");
				log("elementValue " + elementValue);
				click(By.xpath(elementValue), 1);
				wait(1);
				String tooltipvalue = getToolTipValueforMonthCircle();
				log("tooltipvalue " + tooltipvalue); 
				itemsToAdd.add(tooltipvalue);
			} catch(Exception e) {
				log("Skipped " + i);
			}
		}
		log("tooltipvalue :: " + itemsToAdd);
		return itemsToAdd;
	}
	
	
}
