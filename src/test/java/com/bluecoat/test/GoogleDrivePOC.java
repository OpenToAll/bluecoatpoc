/**
 * 
 */
package com.bluecoat.test;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.elastica.utils.Library;

/**
 * @author Suresh
 *
 */
public class GoogleDrivePOC extends Library {

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("@AfterClass");
		driver.quit();
	}
	
	@Test(groups = { "POC1" }, description = "Login into drive")
	public void Test_01_New() throws Exception {
	driver = new FirefoxDriver();
	driver.get("https://drive.google.com");
	
	

	driver.findElement(
			By.xpath("//input[@id='Email']")).sendKeys("test.itc210@gmail.com");
	
	driver.findElement(
			By.xpath("//input[@id='next']")).click();
	Thread.sleep(2000);
	driver.findElement(
			By.xpath("//input[@id='Passwd']")).sendKeys("suresh12345");
	driver.findElement(
			By.xpath("//input[@id='signIn']")).click();
	
	Thread.sleep(10000);
	driver.findElement(
			By.xpath("//*[@id='drive_main_page']/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div")).click();
	String parent2Handle = driver.getWindowHandle();
	System.out.println("The string is" + parent2Handle);

	
	// Below code is used to create Empty Google Docs using AWT Robot


	keyboard.type("^");
	keyboard.type("^");
	keyboard.type("^");
	keyboard.type("^");
	keyboard.type("\n");
	Thread.sleep(10000);
	
	
}

	@Test(groups = { "POC" }, description = "Login into drive")
	public void Test_01_LoginDrive() throws Exception {
		driver.get(getValue("url"));
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(
				getValue("FirstEmailID"));

		driver.findElement(By.xpath(getValue("NextButtonXpath"))).click();
		wait(2);
		driver.findElement(By.xpath(getValue("PasswordXpath"))).sendKeys(
				getValue("FirstEmailpassword"));
		driver.findElement(By.xpath(getValue("SignInXpath"))).click();
	}

	@Test(groups = { "POC" }, description = "Creating Docs")
	public void Test_02_Create_NewDoc() throws Exception {
		// click New Button
		driver.findElement(By.xpath(getValue("NewButtonXpath"))).click();
		// Below code is used to create Empty Google Docs using AWT Robot
		wait(5);

		keyboard.type(downArrow);
		keyboard.type(downArrow);
		keyboard.type(downArrow);
		keyboard.type(downArrow);
		keyboard.type(enter);
		wait(10);
		switchToWindowByTitle("");
		keyboard.type("Elastica Demo");
		driver.findElement(By.xpath(getValue("FileNameTitleXpath"))).sendKeys(
				fileName + "_" + getDateTime());
		keyboard.type(enter);
		wait(2);
	}

	@Test(groups = { "POC" }, description = "Share File By Email")
	public void Test_03_Share_File_Email() throws Exception {
		shareToUser();
		driver.switchTo().defaultContent();
		wait(5);
	}

	@Test(groups = { "POC" }, description = "Upload File")
	public void Test_04_Upload_File() throws Exception {
		driver.close();
		wait(5);
		switchToWindowByTitle("My Drive - Google Drive");
		uploadFile();
		wait(10);
	}

	@Test(groups = { "POC" }, description = "Validate File")
	public void Test_05_Validate_File() throws Exception {
		String fileName = driver.findElement(
				By.xpath(getValue("UploadedFileFoundXpath"))).getText();
		Assert.assertTrue(fileName.contains("UploadNew"),
				"Uploaded file not found");
	}

	@Test(groups = { "POC" }, description = "Download File")
	public void Test_06_Download_File() throws Exception {
		// Select the File to Download
		driver.findElement(
				By.xpath(getValue("FileDownloadXpath1") + fileName
						+ getValue("FileDownloadXpath2"))).click();
		// Click on More Action (3 dots)
		driver.findElement(By.xpath(getValue("MoreActionButtonXpath"))).click();
		wait(2);
		// Click Down Arrow from click Download file
		for (int i = 0; i < 7; i++) {
			keyboard.type(downArrow);
		}
		keyboard.type(enter);
		wait(10);
	}

	/**
	 * Upload the file Used AutoIt to upload file, file contains in resources
	 * folder
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void uploadFile() throws InterruptedException, IOException {
		// Click My Drive and upload file
		driver.findElement(By.xpath(getValue("MyDriveButtonXpath"))).click();
		// Click down arrow and select upload file sub menu
		keyboard.type(downArrow);
		keyboard.type(downArrow);
		keyboard.type(enter);
		wait(5);
		System.out.println("Upload file path " + uploadFileName);
		try {
			Runtime.getRuntime().exec(uploadFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		wait(5);
	}

	/**
	 * Share File via Email
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private void shareToUser() throws InterruptedException, IOException {
		// Click share button(Right side of Docs)
		driver.findElement(By.xpath(getValue("ShareButtonXpath"))).click();
		switchFrame(driver.findElement(By.xpath(getValue("IFrameShareXpath"))));
		driver.findElement(By.xpath(getValue("SendEmailTextareaXpath")))
				.sendKeys(getValue("SecondEmailID"));// suresh12345 password

		driver.findElement(By.xpath(getValue("ShareSendXpath"))).click();
		wait(5);
		try {
			driver.findElement(By.xpath(getValue("ShareYesXpath"))).click();
		} catch (Exception e) {
			System.out.println("Already shared");
		}
	}

}