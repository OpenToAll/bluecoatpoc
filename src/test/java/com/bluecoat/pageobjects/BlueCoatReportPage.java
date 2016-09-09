package com.bluecoat.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class BlueCoatReportPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatReportPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @throws Exception
	 */
	public void clickReportCenter(String value) throws Exception {
		log("Click Report Center Menu");
		click(By.xpath(getValue("bluecoatReportCenterMenu")), timeout);
		wait(2);
		log("Click Sampel Report");
		click(By.xpath(getValue("bluecoatRunSimpleReport")), timeout);
		wait(2);
		onMouseOver(By.xpath(getValue("bluecoatClickWhen")));
		wait(5);
		log("Click "+ value +" Submenu");
		click(By.xpath(getValue(value)), timeout);
		wait(10);
	}
	
	/**
	 * @throws Exception
	 */
	public void clickPopUpDownload() throws Exception {
		log("Click Download Button");
		click(By.xpath(getValue("bluecoatDownload")), timeout);
		wait(2);
		log("Click Popup Download Button");
		click(By.cssSelector(getValue("bluecoatPopUpDownload")), timeout);
		wait(10);
	}
	
	/**
	 * @throws Exception
	 */
	public void clickCSVPopUpDownload() throws Exception {
		log("Click Download Button");
		click(By.xpath(getValue("bluecoatDownload")), timeout);
		wait(2);
		click(By.cssSelector(getValue("bluecoatAllCSVRedioButton")), timeout);
		wait(2);
		log("Click Popup Download Button");
		click(By.cssSelector(getValue("bluecoatPopUpDownload")), timeout);
		wait(10);
	}
	
	/**
	 * @throws Exception
	 */
	public void clickXMLPopUpDownload() throws Exception {
		log("Click Download Button");
		click(By.xpath(getValue("bluecoatDownload")), timeout);
		wait(2);
		click(By.cssSelector(getValue("bluecoatAllXMLRadioButton")), timeout);
		wait(2);
		log("Click Popup Download Button");
		click(By.cssSelector(getValue("bluecoatPopUpDownload")), timeout);
		wait(10);
	}
	
	
	public void addGrid(boolean status, String mMenu, String sMenu) throws Exception {
		if (!status){
			log("bluecoat Click AddReport");
			click(By.xpath(getValue("bluecoatClickAddReport")), timeout);
			wait(2);
			log("Selecting Option Main menu");
			onMouseOver(By.xpath(getValue("bluecoatMainMenu").replaceAll("TITLE",mMenu)));
			wait(2);
			log("Selecting Option Sub menu");
			click(By.xpath(getValue("bluecoatSubmenu").replaceAll("TITLE",sMenu)), timeout);
			log("Successfully Selected Option To All Dates");
			wait(10);
		}else
		{
			log("Allready grid is Present");
		}
	}
	
	
}
