package com.framework.helper;

import java.io.File;
import java.io.IOException;
import com.framework.core.Global;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class ReadPDF {

    /** The original PDF that will be parsed. */
    public static final String PREFACE =  Global.USER_HOME +  File.separator+ "Downloads" +File.separator +  "Year.PDF";
    /** The resulting text file. */
    public static final String RESULT = Global.USER_HOME +  File.separator+ "Downloads" +File.separator +  "a.txt";

    /**
     * Parses a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            out.println(strategy.getResultantText());
        }
        reader.close();
        out.flush();
        out.close();
    }

    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new ReadPDF().parsePdf(PREFACE, RESULT);
        System.out.println("Done");
    }
}



/*
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
*/
