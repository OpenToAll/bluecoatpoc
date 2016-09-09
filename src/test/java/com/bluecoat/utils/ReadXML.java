package com.bluecoat.utils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXML {

	public static void parseXML(String path) {

		// boolean result = false;
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					if (attributes != null && attributes.getLength() > 0) {
						System.out.print(qName + " tag has attributes - ");
						for (int i = 0; i < attributes.getLength(); i++) {
							System.out.println(attributes.getLocalName(i));
						}

					} else
						throw new SAXException("XML doesnot conatin any values");
				}

				public void endElement(String uri, String localName, String qName) throws SAXException {

				}

				public void characters(char ch[], int start, int length) throws SAXException {

				}

			};

			saxParser.parse(path, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
