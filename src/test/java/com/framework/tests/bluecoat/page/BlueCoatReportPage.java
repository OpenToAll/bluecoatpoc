package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.framework.core.Global;
import com.framework.core.SeleniumLibrary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class BlueCoatReportPage extends BlueCoatCommon {
	protected int timeout = 10;
	String barImage = "barGraph.png";
	String yellowPie = "pieyellow.png";

	public BlueCoatReportPage(WebDriver driver) {
		this.driver = driver;
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
		wait(10);
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
