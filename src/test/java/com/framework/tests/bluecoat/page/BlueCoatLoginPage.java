package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import com.framework.core.Global;
import com.framework.core.Keyboard;
import com.framework.core.SeleniumLibrary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class BlueCoatLoginPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login() throws Exception{
		goToURL("https://portal.qa3.bluecoatcloud.com/login.jsp");
		wait(10);
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginuser")), getValue("user"));
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginpassword")), getValue("password"));
		click(By.xpath(getValue("bluecoatsubmit")), timeout);
		wait(20);
	}
	
	public boolean isDisplay(String table) throws Exception {
		log("Is Found " + getWebElement((By.xpath(getValue(table)))).isDisplayed());
		return getWebElement((By.xpath(getValue(table)))).isDisplayed();
	}
	
	public String getText(String Element) throws Exception {
		String value = getText(By.xpath(getValue(Element)));
		log("Value " + value);
		return value;
	}
	

	 
	 
	 

}
