package com.framework.tests.bluecoat.page;
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
	

	
	
}
