package com.bluecoat.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bluecoat.pageobjects.BlueCoatDashboardPage;
import com.bluecoat.pageobjects.BlueCoatLoginPage;
import com.bluecoat.pageobjects.BlueCoatReportPage;
import com.bluecoat.reporter.TestDetail;

public class BlueCoatPOC_SikuliTest extends BaseTest {

	private BlueCoatLoginPage m_bluecoatLoginPage = null;
	private BlueCoatDashboardPage m_blueCoatDashboardPage = null;
	private BlueCoatReportPage m_blueCoatReportPage = null;

	String dashBoardValueBusinessRelated;
	String selectField = "Business Related";

	/**
	 * This test method is sued to validate login functionality with valid
	 * credentials
	 * 
	 * @throws Exception
	 */
	@Test(priority = 0)
	@TestDetail(testCaseID = "BC_1", testCaseName = "Verify Login To Blue Coat", author = "abc@bluecoat.com")
	public void verify_BlueCoat_Login() throws Exception {

		log("*****Login Into Blue Coat*****");

		m_bluecoatLoginPage = new BlueCoatLoginPage(driver);
		m_bluecoatLoginPage.login();
		Assert.assertTrue(m_bluecoatLoginPage.getTitle().contains("Blue Coat ThreatPulse"), "Title Not found");

	}

	@Test(priority = 0)
	@TestDetail(testCaseID = "BC_1", testCaseName = "Verify Login To Blue Coat", author = "abc@bluecoat.com")
	public void verify_BlueCoat_Message_Close_Sikuli() throws Exception {
		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		for (int i = 0; i< 3; i++) {
			m_blueCoatDashboardPage.clickImage("MessageLabel.png");
			m_blueCoatDashboardPage.clickImage("MessageClose.png");
			wait(10);
		}
	}

	
	

}
