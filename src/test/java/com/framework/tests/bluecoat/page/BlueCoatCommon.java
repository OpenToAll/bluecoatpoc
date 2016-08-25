package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;

import com.framework.core.Global;
import com.framework.core.SeleniumLibrary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class BlueCoatCommon extends SeleniumLibrary {
	
	public String getText(String Element) throws Exception {
		String value = getText(By.xpath(getValue(Element)));
		log("Value " + value);
		return value;
	}

	public boolean isDisplay(String table) throws Exception {
		log("Is Found " + getWebElement((By.xpath(getValue(table)))).isDisplayed());
		return getWebElement((By.xpath(getValue(table)))).isDisplayed();
	}
	
	public String getTitle() throws Exception {
		String title = driver.getTitle();
		log("Title " + title);
		return title;
	}
	
	
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
}
