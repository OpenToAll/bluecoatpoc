package com.framework.tests.bluecoat;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.framework.core.Global;
import com.framework.core.SeleniumLibrary;
import com.framework.tests.bluecoat.page.BlueCoatDashboardPage;
import com.framework.tests.bluecoat.page.BlueCoatLoginPage;
import com.framework.tests.bluecoat.page.BlueCoatReportPage;


public class BlueCoatLibrary extends SeleniumLibrary {
	
	protected BlueCoatLoginPage bluecoatLoginPage = null;
	protected BlueCoatDashboardPage blueCoatDashboardPage = null;
	protected BlueCoatReportPage blueCoatReportPage = null;
	int timeout = 20;
	
	@Parameters("browser")
	@BeforeClass
	 public void setup(String browser) throws Exception {
		 PropertyConfigurator.configure("log4j.properties");
		 Global.setBrowserName(browser);
		 assignBrowser();
		 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		 String browserName = cap.getBrowserName().toLowerCase();
		 Global.TEST_SET.add(browserName + "@" + cap.getVersion().toString());
		 bluecoatLoginPage = new BlueCoatLoginPage(driver);
		 blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		 blueCoatReportPage = new BlueCoatReportPage(driver);
	 }

	 @AfterClass
	 public void tearDown() {
		 closeDriver();
	 }

}
