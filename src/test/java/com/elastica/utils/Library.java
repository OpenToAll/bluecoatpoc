package com.elastica.utils;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;

/**
 * @author Suresh
 *
 */
public class Library {

	public Keyboard keyboard;
	public WebDriver driver;
	public WebElement element;
	public String fileName = "Elastica";
	public String uploadFileName = System.getProperty("user.dir")
			+ "\\resources\\upload.exe";
	protected Properties objectDBProp;
	protected InputStream inputStream;
	protected String downArrow;
	protected String enter;

	/**
	 * Assign browser
	 * 
	 * @throws AWTException
	 */

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		keyboard = new Keyboard();
		downArrow = getValue("DownArrow");
		enter = getValue("Enter");
//		String browser = "chrome";
//
//		if ("firefox".equals(browser)) {
//			driver = new FirefoxDriver();
////		} else if ("chrome".equals(browser)) {
//			System.out.println("Initialize Chrome");
//			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//				System.setProperty("webdriver.chrome.driver",
//						"libs/chromedriver.exe");
//			} else if (System.getProperty("os.name").toLowerCase()
//					.contains("mac")) {
				System.out.println("Assigning chrome");
				System.setProperty("webdriver.chrome.driver",
						"/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
				System.out.println("Done chrome");
//			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("--disable-bundled-ppapi-flash");
		//	driver = new  FirefoxDriver();
			
			//driver = new SafariDriver();
//		}
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * 
	 */
	private void assignProperty() {
		objectDBProp = new Properties();
		try {
			String propFileName = "Data.properties";
			inputStream = new FileInputStream(System.getProperty("user.dir")
					+ "/" + propFileName);
			if (inputStream != null) {
				objectDBProp.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Property file not found " + e);
		}
	}

	/**
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getValue(String key) throws IOException {
		assignProperty();
		System.out.println("key" + key);
		String value = null;
		try {
			value = objectDBProp.getProperty(key);
		} catch (Exception e) {
			System.out.println("The Exception " + e);
		}
		System.out.println("Value is " + value);
		return value;
	}

	/**
	 * Switch Window By Title
	 * 
	 * @param title
	 */
	public void switchToWindowByTitle(String title) {
		boolean found = false;

		while (found == false) {
			for (String handle : driver.getWindowHandles()) {
				String myTitle = driver.switchTo().window(handle).getTitle();
				if (myTitle.equalsIgnoreCase(title)) {
					found = true;
					break;
				}
				System.out.println("Title " + myTitle);
			}
			found = true;
		}
	}

	/**
	 * Switch IFrame By WebElement
	 * 
	 * @param element
	 */
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * Wait is used for delay in seconds
	 * 
	 * @param timeout
	 * @throws InterruptedException
	 */
	public void wait(int timeout) throws InterruptedException {
		System.out.println("Wait: Sleeping for [" + timeout + "] seconds");
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get Date and Time
	 * 
	 * @return
	 */
	public String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return df.format(new Date());
	}
}
