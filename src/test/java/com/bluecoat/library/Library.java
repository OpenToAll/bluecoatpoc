package com.bluecoat.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.sikuli.script.ImagePath;
import org.testng.Reporter;

import com.bluecoat.reporter.Log;
import com.bluecoat.utils.HttpUtil;
import com.bluecoat.utils.MiscUtils;

public class Library {
	//static final Logger log = Logger.getLogger(Library.class);
	public static String curDirPath="";
	public static File path=new File(".");	
	public WebDriver driver;
	private final int ADMIN_CALL_RETRY_COUNT = 2;
	static final int CONNECTION_TIMEOUT = 10 * 1000;
    static final int SO_TIMEOUT = 120 * 1000;
    protected String IMAGE_PATH;
    protected int HIGH_TOLERANCE = 70;
    protected int TOLERANCE = 60;
    protected int LOW_TOLERANCE = 50;
    protected int TIME_OUT = 20;
	protected Properties objectDBProp;
	protected InputStream inputStream;
	
	public static final String SIKULI_IMAGE=System.getProperty("user.dir")+File.separator+
			"resources"+File.separator;
    
	public void assignBrowser() throws IOException {
		if ("firefox".equals(Global.getBrowserName())) {
			Log.debug("Starting Firefox driver");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("capability.policy.default.Window.frameElement", "allAccess");
			log("Save Download Profile Setting");
			profile.setPreference("browser.download.folderList",2);
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("browser.download.dir", Global.DOWNLOAD_DIR);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream");
			driver = new FirefoxDriver(profile);
			driver.manage().window().maximize();
		} else if ("ie".equals(Global.getBrowserName())) {
			Log.debug("Starting IE driver");
			File file = new File("libs\\IEDRiverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath() );
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		} else if ("chrome".equals(Global.getBrowserName())) {
				Log.debug("Starting Chrome driver");
				if (System.getProperty("os.name").toLowerCase().contains("windows")) {
					Log.debug("Found Windows: Setting system property [webdriver.chrome.driver=libs/chromedriver.exe]");
					System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
				} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
					Log.debug("Found Mac: Setting system property [webdriver.chrome.driver=libs/chromedriver]");
					System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
				}
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				Log.debug("Setting Chrome option: [start-maximized]");
				options.addArguments("start-maximized");
				Log.debug("Setting Chrome option: [disable-bundled-ppapi-flash]");
				options.addArguments("--disable-bundled-ppapi-flash");
				driver = new ChromeDriver(options);
		} else if ("safari".equals(Global.getBrowserName())) {
			Log.debug("Starting Safari driver");
			driver = new SafariDriver();
		} else if ("htmlunit".equals(Global.getBrowserName())){
			Log.debug("Starting HtmlUnit driver");
			driver = new HtmlUnitDriver();
		} else {
			Log.error("Could not find a suitable browser");
		}
		ImagePath.add(SIKULI_IMAGE);
	}

	
	/**
	 * 
	 */
	public void assignProperty() {
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
	
	
	public WebDriver launchBrowser() throws IOException {
		assignBrowser();
		if(!"CHROME".equalsIgnoreCase(Global.getBrowserName()))
		maximize();
		return driver;
	}
	 
	public void maximize() {
		Log.debug("Maximizing browser window to screen size");
		driver.manage().window().setPosition(new Point(0,0));
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver.manage().window().setSize(dim);
	}
	
	public void setRootDirectory(){
		try {
			curDirPath=path.getCanonicalPath();
		} catch (Exception e) {
			Log.error("Failed to set Current Directory Path", e);
		}
	}
	
	public String getRootDir(){
		if(curDirPath==""){
			setRootDirectory();
		}
		return curDirPath;
	}
		
	public void wait(int timeout) throws InterruptedException{
		log("Wait: Sleeping for [" + timeout + "] seconds" );
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// do nothing
		}
	}
	
	/*
	 *  Logs to HTML report and Framework logs
	 */
	public static void log(String message) {
		log(message, null);
	}
	
	
	public static void log(String message, String prefix) {
		if(prefix != null) {
			Log.info(prefix + ":: " + message);
			Reporter.log(prefix + ":: " + message);
		} else {
			Log.info(message);
			Reporter.log(message);
		}
	}
		
	/*
	 *  Logs to HTML report with screenshot.
	 *  Logs to Framework logs
	 */
	public static void logWithScreenshot(String message) {
		logWithScreenshot(message, null);
	}
	
	public static void logWithScreenshot(String message, String prefix) {
		String path = Global.getReportsDir() + File.separator + "screenshots" + File.separator;
		String fileName = System.currentTimeMillis() + ".png";
		String screenshotsPath = "./screenshots/" + fileName;
		try {
			if (MiscUtils.empty(path) || !new MiscUtils().captureScreen(path, fileName)) {
				Log.error("Screenshot Capture Failed");
				Reporter.log(message);
				return;
			}
		} catch (Exception e) {
		Log.error("Screenshot Capture Failed with Exception", e);
		}
		log(message + ", [<a href='" + screenshotsPath + "'>Screenshot</a>]", prefix);
	}
	/*
	 *  Kills all browser process specified in the list array
	 */
	public void killBrowser(String[] browserList) {
		for (String browser : browserList) {
			try {
				Process p = Runtime.getRuntime().exec("taskkill /F /IM " + browser);
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					Log.debug(line);
				}
			} catch (Exception e) {
				Log.error("Exception in KillBrowser", e);
			}
		}
	}
	
	public void clearCacheAndDeleteCookies(){
		driver.manage().deleteAllCookies();
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			log("attempting to clear cache and cookies");
			p  = rt.exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 4351");
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
			Log.debug(line);
			}
		} catch (Exception e) {
			Log.error("Exception in clearCacheAndDeleteCookies", e);
		}
	}
	
	
	/*
	 *  Make admin call with params
	 */
	public String makeAdminCall(Map<String, String> params) throws Exception {
		String urlParams = "?";
		int count = 0;
		HttpResponse r;
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				urlParams = urlParams + entry.getKey() + "=" + entry.getValue()	+ "&";
			}
			urlParams = urlParams.substring(0,urlParams.length()-1);
			String url = "http://transliteration.reverie.co.in/api/processString" + urlParams;
			Library.log("Requesting Admin call [" + url + "]");
			
			while(count < ADMIN_CALL_RETRY_COUNT) {
				count++;
				try {
					r = HttpUtil.get(url, CONNECTION_TIMEOUT, SO_TIMEOUT);
					if(r.getStatusLine().getStatusCode() == 200) {
						String response = HttpUtil.getResponse(r);
						Reporter.log("Response: [" + response + "]");
						return response;
					} else {
						Log.debug("Admin call did not return a 200 OK, retrying (" + count + ") ...");
						Thread.sleep(3000);
					}
				} catch(Exception e) {
					Log.error("Exception occured in making the admin call, retrying(" + count + ") ...", e);
					Thread.sleep(3000);
				}
			}
			return HttpUtil.getResponse(HttpUtil.get(url));			
		} catch (Exception e) {
			Log.error("Admin Call Failed", e);
			throw e;
		}
	}

	
	public void makeAdminCall(String url) throws Exception {
		driver.get(url);
		//Assert.assertTrue(driver.findElement(By.xpath ("//body")).getText().length() > 10);
		
//		int count = 0;
//		HttpResponse r;
//		try {
//			
//			Library.log("Requesting Admin call [" + url + "]");
//			while(count < ADMIN_CALL_RETRY_COUNT) {
//				count++;
//				try {
//					r = HttpUtil.get(url, CONNECTION_TIMEOUT, SO_TIMEOUT);
//					if(r.getStatusLine().getStatusCode() == 200) {
//						String response = HttpUtil.getResponse(r);
//						Reporter.log("Response: [" + response + "]");
//						return response;
//					} else {
//						Log.debug("Admin call did not return a 200 OK, retrying (" + count + ") ...");
//						Thread.sleep(3000);
//					}
//				} catch(Exception e) {
//					Log.error("Exception occured in making the admin call, retrying(" + count + ") ...", e);
//					Thread.sleep(3000);
//				}
//			}
//			return HttpUtil.getResponse(HttpUtil.get(url));			
//		} catch (Exception e) {
//			Log.error("Admin Call Failed", e);
//			throw e;
//		}
	}
}
