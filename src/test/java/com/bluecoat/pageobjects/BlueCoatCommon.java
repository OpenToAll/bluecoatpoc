package com.bluecoat.pageobjects;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.bluecoat.library.Global;
import com.bluecoat.library.SeleniumLibrary;
import com.bluecoat.utils.ReadXML;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.opencsv.CSVReader;

public class BlueCoatCommon extends SeleniumLibrary {
	
	/**
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String getText(String data) throws Exception {
		String value = getText(By.xpath(getValue(data)));
		log("Value " + value);
		return value;
	}

	/**
	 * @param data
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String getText(String data, String index) throws Exception {
		String element = getValue(data).replaceAll("TITLE", index);
		log("element" + element);
		String value = getText(By.xpath(element));
		log("Value " + value);
		return value;
	}
	
	public String getText(String data, String row, String column) throws Exception {
		String element = getValue(data).replaceAll("ROW", row).replace("COLUMN", column);
		log("element" + element);
		String value = getText(By.xpath(element));
		log("Value " + value);
		return value;
	}
	
	
	/**
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public boolean isDisplay(String table) throws Exception {
		log("Is Found " + getWebElement((By.xpath(getValue(table)))).isDisplayed());
		return getWebElement((By.xpath(getValue(table)))).isDisplayed();
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String getTitle() throws Exception {
		String title = driver.getTitle();
		log("Title " + title);
		return title;
	}
	
	/**
	 * @param data
	 * @param title
	 * @throws Exception
	 */
	public void ScrollToElement(String data, String title) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = getWebElement((By.xpath(getValue(data).replaceAll("TITLE", title))));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * @param data
	 * @throws Exception
	 */
	public void ScrollToElement(String data) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = getWebElement((By.xpath(getValue(data))));
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
	/**
	 * @param data
	 * @throws Exception
	 */
	public void ScrollToElement(WebElement element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String getTextFromPDF() throws Exception {
		String downloadedFile = getTheNewestFile(Global.DOWNLOAD_DIR, "PDF").toString();
		String pdfText = "";
	    PdfReader reader = new PdfReader(downloadedFile);
	    PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	    TextExtractionStrategy strategy;
	    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
	        strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
	        pdfText += strategy.getResultantText();
	    }
	    reader.close();
	    log("File " + downloadedFile + " Contains \n" + pdfText);
		return pdfText;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
		public String getTextFromCSV() throws Exception {
		
		String csvFile = getTheNewestFile(Global.DOWNLOAD_DIR, "CSV").toString();
		CSVReader reader = null;
		String pdfText = "";
	    try {
	        reader = new CSVReader(new FileReader(csvFile));
	        String[] line;
	        while ((line = reader.readNext()) != null) {
	            System.out.println("[ Month = " + line[0] + ", Requests= " + line[1] + "]");
	            pdfText+=line[0]+ " "+ line[1] ;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return pdfText;
	}
		
		/**
		 * @return
		 * @throws Exception
		 */
	public void validateXML() throws Exception {

		String xmlFile = getTheNewestFile(Global.DOWNLOAD_DIR, "XML").toString();
		ReadXML.parseXML(xmlFile);

	}
		
	
}
