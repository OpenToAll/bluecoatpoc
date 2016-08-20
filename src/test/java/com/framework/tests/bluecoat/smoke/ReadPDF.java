package com.framework.tests.bluecoat.smoke;

import java.io.File;
import java.io.IOException;

import com.asprise.util.pdf.PDFReader;

public class ReadPDF {

public static void main(String args[]) throws IOException {	
	PDFReader reader = new PDFReader(new File(System.getProperty("user.dir")+File.separator+
			"resources"+File.separator + "b.pdf"));
	reader.open(); // open the file. 
	int pages = reader.getNumberOfPages();
	 
	for(int i=0; i < pages; i++) {
	   String text = reader.extractTextFromPage(i);
	   System.out.println("Page " + i + ": " + text); 
	}
}
}
