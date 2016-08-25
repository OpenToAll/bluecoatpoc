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

public class BlueCoatLoginPage extends SeleniumLibrary {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatLoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login() throws Exception{
		goToURL("https://portal.qa3.bluecoatcloud.com/login.jsp");
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginuser")), getValue("user"));
		clearAndsendKeys(By.cssSelector(getValue("bluecoatloginpassword")), getValue("password"));
		click(By.xpath(getValue("bluecoatsubmit")), timeout);
		wait(20);
	}
	
	public void clickRiskSetting(String option) throws Exception {
		click(By.xpath(getValue("bluecoatrisksetting")), timeout);
		wait(2);
		click(By.xpath(getValue("bluecoatinput")), timeout);
		wait(2);
		click(By.xpath(getValue("selecttype").replace("TITLE", option)), timeout);
		wait(2);
		click(By.xpath(getValue("bluecoatsettingsave")), timeout);
		wait(10);
	}
	
	public void clickReportCenter() throws Exception {
		click(By.xpath(getValue("bluecoatReportCenterMenu")), timeout);
		wait(2);
		click(By.xpath(getValue("bluecoatRunSimpleReport")), timeout);
		wait(2);
		onMouseOver(By.xpath(getValue("bluecoatClickWhen")));
		wait(2);
		click(By.xpath(getValue("bluecoatClickYear")), timeout);
		wait(10);
	}
	
	public void clickPopUpDownload() throws Exception {
		click(By.xpath(getValue("bluecoatDownload")), timeout);
		wait(2);
		click(By.cssSelector(getValue("bluecoatPopUpDownload")), timeout);
		wait(10);
	}
	
	public String getTextFromPDF() throws Exception {
		Keyboard awt = new Keyboard();
		awt.type("\n");
		wait(15);
		String pdfText = "";
	    PdfReader reader = new PdfReader(Global.PDF_FILE);
	    PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	    TextExtractionStrategy strategy;
	    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
	        strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
	        pdfText += strategy.getResultantText();
	    }
	    reader.close();
	    log("File Contains" + pdfText);
		return pdfText;
	}
	
	public boolean isDisplay(String table) throws Exception {
		log("Is Found " + getWebElement((By.xpath(getValue(table)))).isDisplayed());
		return getWebElement((By.xpath(getValue(table)))).isDisplayed();
	}
	
	public String getText(String Element) throws Exception {
		String title = getText(By.xpath(getValue(Element)));
		log("Title " + title);
		return title;
	}
	

	public String getTitle() throws Exception {
		String title = driver.getTitle();
		log("Title " + title);
		return title;
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