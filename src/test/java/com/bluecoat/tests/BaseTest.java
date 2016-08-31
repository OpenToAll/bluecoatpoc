package com.bluecoat.tests;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.bluecoat.library.Global;
import com.bluecoat.library.SeleniumLibrary;
import com.bluecoat.reporter.Log;

public class BaseTest extends SeleniumLibrary {

	/**
	 * Attributes to hold different folder structures
	 */
	public static String m_logDir = null;
	int timeout = 20;

	@Parameters({ "outdir", "logAndReportFilePath", "browser" })
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(@Optional String outdir, @Optional String logAndReportFilePath, @Optional String browser,
			@Optional ITestContext context) throws Exception {

		if (null != outdir && !outdir.isEmpty() && outdir.charAt(outdir.length() - 1) != '/')
			outdir = outdir + "/";

		m_logDir = (outdir == null ? "" : outdir) + "log/" + (logAndReportFilePath == null ? "" : logAndReportFilePath);

		File dir = new File(m_logDir);
		dir.mkdirs();

		if (browser.isEmpty())
			browser = System.getenv("BROWSER");

	}

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		Global.setBrowserName(browser);
		assignBrowser();
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		Global.executionDetailsMap.put("Browser", cap.getBrowserName() + " - " + cap.getVersion());
		Global.executionDetailsMap.put("Platform", cap.getPlatform().name());

	}

	@BeforeMethod(alwaysRun = true)
	public void initialize(ITestContext context, Method method) {

		Log.setLog(m_logDir, method.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestContext context, Method method) {

		String methodName = method.getName();

		if (driver != null) {

			try {
				try {
					takeScreenShot(driver, context.getOutputDirectory(), methodName);
				} catch (Exception e) {
					Log.error("Failed taking screenshot");
				}

			} catch (Exception e) {
				Log.fatal("Error in @AfterMethod : " + e.getMessage());
			}
		}

		try {
			dumpHTMLSource(driver, context.getOutputDirectory(), methodName);
		} catch (Exception e) {
			Log.error("Failed to dump the HTML Source");
		}

		Log.info(" \n\n**********************  END OF TEST CASE " + method.getName() + "  ********************** \n\n");
		Log.flushLog();
	}

	@AfterSuite(alwaysRun = true)
	public void shutdown() {

		Log.info("@AfterSuite Shutdown WebDrivers");

		if (driver != null) {

			try {

				Log.info("@AfterSuite Quit the driver:" + driver);
				wait(3);
				driver.quit();
				Log.info("@AfterSuite WebDriver Quit Success");

			} catch (Exception e) {
				Log.error("@AfterSuite Exception Caught while quitting driver: " + e.getMessage());
				e.printStackTrace();
			}

		}

	}

}
