package com.framework.tests.bluecoat.page;

import org.openqa.selenium.By;

import com.framework.core.SeleniumLibrary;

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
}
