package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;

import com.framework.core.SeleniumLibrary;

public class BlueCoatCommon extends SeleniumLibrary {
	
	public String getText(String Element) throws Exception {
		String value = getText(By.xpath(getValue(Element)));
		log("Value " + value);
		return value;
	}

}
