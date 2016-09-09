package com.bluecoat.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bluecoat.pageobjects.BlueCoatDashboardPage;
import com.bluecoat.pageobjects.BlueCoatLoginPage;
import com.bluecoat.pageobjects.BlueCoatReportPage;
import com.bluecoat.reporter.TestDetail;

/**
 * BlueCoatPOCTest Class contains test scripts for identified POC Test Cases
 * 
 * @author ITC Infotech
 *
 */
public class BlueCoatPOCTest extends BaseTest {

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
	@Test(priority = 1)
	@TestDetail(testCaseID = "BC_1", testCaseName = "Verify Login To Blue Coat", author = "abc@bluecoat.com")
	public void verify_BlueCoat_Login() throws Exception {

		log("*****Login Into Blue Coat*****");

		m_bluecoatLoginPage = new BlueCoatLoginPage(driver);
		m_bluecoatLoginPage.login();
		Assert.assertTrue(m_bluecoatLoginPage.getTitle().contains("XXXX XXXX"), "Title Not found");

	}

	/**
	 * This method is used to validate "Web Browsing per Site" grid values with
	 * View full Report link Bar each Tool tip
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	@TestDetail(testCaseID = "BC_2", testCaseName = "DashBoard Web Browsring Per site Full Report Validation", author = "abc@bluecoat.com")
	public void verify_BlueCoat_DashBoard_WebBrowsingPerSite_Full_Report() throws Exception {

		log("*****Get Grid Value Of Web Browser Site And Verify Same In Full Report*****");

		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatReportPage = new BlueCoatReportPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		String webBrowsingWidget = "Web Browsing per Site";
		boolean exists = m_blueCoatDashboardPage.checkWidgetIsPresent(webBrowsingWidget);
		if (!exists)
			m_blueCoatReportPage.addGrid(exists, "User Behavior", webBrowsingWidget);
		
		m_blueCoatDashboardPage.ScrollToElement("bluecoatwebbrowsingfullreportlink");
		m_blueCoatDashboardPage.getGridValueOfWebBrowserSite();
		m_blueCoatDashboardPage.clickWebBrowsingSiteFullReport();
		m_blueCoatDashboardPage.clickFullReportWebBrowserSitePoints();
		Assert.assertTrue(m_blueCoatDashboardPage.verifyGridAndGraphOfWebBrowserSite(),
				"Web Browse Site Grid And Graph Value Is Not Same");
		
	}


	/**
	 * 
	 * This method is used to Validate different Portlet Options
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	@TestDetail(testCaseID = "BC_3", testCaseName = "Dashboard Risk Group Portlet Options validation", author = "abc@bluecoat.com")
	public void verify_BlueCoat_RiskGroup_Portlet_Options() throws Exception {

		log("*****Enable Grid Only, Verify Grid Is Displayed, And Graph Is Not Displayed*****");
		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatReportPage = new BlueCoatReportPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		String riskGroupWidget = "Risk Groups";
		boolean exists = m_blueCoatDashboardPage.checkWidgetIsPresent(riskGroupWidget);
		if (!exists)
			m_blueCoatReportPage.addGrid(exists,"Security",riskGroupWidget);
		
		m_blueCoatDashboardPage.clickRiskSetting("Grid Only");
		Assert.assertTrue(m_blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"),
				"Risk Table Widget Is Not Enable");
		Assert.assertFalse(m_blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartnotdisplayed"),
				"Risk Chart Widget Is Displayed");

		log("*****Enable Chart Only, Verify Chart Is Displayed, And Grid Is Not Displayed*****");
		m_blueCoatDashboardPage.clickRiskSetting("Chart Only");
		Assert.assertTrue(m_blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartdisplayed"),
				"Risk Chart Widget Is Displayed");
		Assert.assertFalse(m_blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"),
				"Risk Table Widget Is Not Disabled");

		log("*****Enable Grid and Chart, Verify Grid Is Displayed, And Chart Is Displayed*****");
		m_blueCoatDashboardPage.clickRiskSetting("Both Grid and Chart");
		Assert.assertTrue(m_blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"),
				"Risk Table Widget Is Not Enable");
		Assert.assertTrue(m_blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartdisplayed"),
				"Risk Chart Widget Is Displayed");
	}

	/**
	 * This method is used to validate "Business Related" and "Non-Productive"
	 * values in Risk Groups widget
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	@TestDetail(testCaseID = "BC_4", testCaseName = "DashBoard Risk Group Graph with Grid validation", author = "abc@bluecoat.com")
	public void verify_BlueCoat_DashBoard_Risk_Group_GridValues() throws Exception {

		log("*****Validate Security In Grid And Graph Tooltip Value Same*****");
		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();

		log("*****Validate Business Related Graph Count With Grid*****");
		String value = m_blueCoatDashboardPage.getValueInGrid("Business Related");
		Assert.assertTrue(m_blueCoatDashboardPage.isValuePresentInGraph(value), "Business Related Value Not Found");

		log("*****Validate Non-Productive Graph Count With Grid*****");
		value = m_blueCoatDashboardPage.getValueInGrid("Non-Productive");
		Assert.assertTrue(m_blueCoatDashboardPage.isValuePresentInGraph(value), "Non-Productive Value Not Found");
	}

	/**
	 * This method is used to validate "Day" widget with line graph and gird
	 * data
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	@TestDetail(testCaseID = "BC_5", testCaseName = "DashBoard Overview of Risk Group Day Graph Validation", author = "abc@bluecoat.com")
	public void verify_BlueCoat_DashBoard_RiskGroup_OverviewDayGraphCounts() throws Exception {

		log("*****Dashboard Overview Count*****");
		
		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		log("*****Get Graph Value And Validate With Grid Value*****");
		dashBoardValueBusinessRelated = m_blueCoatDashboardPage.getValueInGrid(selectField);
		m_blueCoatDashboardPage.clickLinkInGrid(selectField);

		m_blueCoatDashboardPage.clickOverviewSetting("Both Grid and Chart", "Day");
		m_blueCoatDashboardPage.clickDayGraphAndGetValue();
		m_blueCoatDashboardPage.getDayGRidValue();
		Assert.assertTrue(m_blueCoatDashboardPage.verifyDayGridAndGraph(), "Day Grid And Graph Value Is Not Same");
		log("*****Successful Dashboard Overview Count*****");
	}

	/**
	 * This method is sued to Validate "Business Related" values with Overview
	 * of Risk Group each widget gird Report Totals
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6)
	@TestDetail(testCaseID = "BC_6", testCaseName = "DashBoard Overview of Risk Group Widgets Validation", author = "abc@bluecoat.com")
	public void verify_BlueCoat_DashBoard_RiskGroup_Overview_Widgets() throws Exception {

		log("*****Dashboard Overview Count*****");
		
		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		dashBoardValueBusinessRelated = m_blueCoatDashboardPage.getValueInGrid(selectField);
		m_blueCoatDashboardPage.clickLinkInGrid(selectField);
		m_blueCoatDashboardPage.clickOverviewSetting("Both Grid and Chart", "Day");
		log("*****Client IP Count*****");
		String count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Client IP");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  protocol Count " + count);

		log("*****Protocol Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Protocol");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Protocol");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  protocol Count " + count);

		log("*****Verdict Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Verdict");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Verdict");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  Verdict Count " + count);

		log("*****Location Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Location");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Location");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  Location Count " + count);

		log("*****Site Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Site");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Site");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  Site Count " + count);

		log("*****Web Application Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Web Application");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Web Application");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  Web Application Count " + count);

		log("*****Subnet Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Subnet");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Subnet");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  Subnet Count " + count);

		log("*****User Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "User");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "User");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  User Count " + count);

		log("*****Web Application Action Count*****");
		m_blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Web Application Action");
		count = m_blueCoatDashboardPage.getText("bluecoatoverviewpage", "Web Application Action");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated), selectField + "Dashboard Value "
				+ dashBoardValueBusinessRelated + " Overview page  Web Application Action Count " + count);
	}

	/**
	 * This method is used to Run Sample Report for Month & Verify same data
	 * with the download PDF
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	@TestDetail(testCaseID = "BC_7", testCaseName = "Report Validation Month", author = "abc@bluecoat.com")
	public void verify_BlueCoat_Report_Month() throws Exception {

		log("*****Click On Report Center And Verify Grid Displayed*****");

		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		m_blueCoatReportPage.clickReportCenter("bluecoatClickMonth");
		Assert.assertEquals(m_blueCoatReportPage.getText("bluecoatReportHeader"), "Month",
				"Report Month is Not Displayed");

		List<String> listOfMonth = m_blueCoatDashboardPage.getGridValueByMonth();

		log("*****Download Month Data*****");
		m_blueCoatReportPage.clickPopUpDownload();

		log("*****Get Downloaded PDF, Data Read And Verify Grid Table Data Is Present In PDF*****");
		String result = m_blueCoatReportPage.getTextFromPDF();

		log("*****Validate Month Is Present*****");
		for (String month : listOfMonth) {
			Assert.assertTrue(result.contains(month), "PDF File Not contains Data " + month);
		}

	}
	

	/**
	 * This method is used to open Message and close Message box using Sikuli
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	@TestDetail(testCaseID = "BC_8", testCaseName = "Report Message Box", author = "abc@bluecoat.com")
	public void verify_BlueCoat_Message_Open_And_Close_Sikuli() throws Exception {

		log("*****Click On Message And Close*****");

		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		for (int i = 0; i< 2; i++) {
			m_blueCoatDashboardPage.clickImage("MessageLabel.png");
			m_blueCoatDashboardPage.clickImage("MessageClose.png");
			wait(10);
		}
	}
	
	/**
	 * This method is used to Run Sample Report for Month & Verify same data
	 * with the download PDF
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	@TestDetail(testCaseID = "BC_9", testCaseName = "CSV Report Validation Month", author = "abc@bluecoat.com")
	public void verify_BlueCoat_CSV_Report_Month() throws Exception {

		log("*****Click On Report Center And Verify Grid Displayed*****");

		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatReportPage= new BlueCoatReportPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		m_blueCoatReportPage.clickReportCenter("bluecoatClickMonth");
		
		Assert.assertEquals(m_blueCoatReportPage.getText("bluecoatReportHeader"), "Month",
				"Report Month is Not Displayed");

		List<String> listOfMonth = m_blueCoatDashboardPage.getGridValueByMonth();
		log("listOfMonth : " + listOfMonth);

		log("*****Download Month Data*****");
		m_blueCoatReportPage.clickCSVPopUpDownload();

		log("*****Get Downloaded PDF, Data Read And Verify Grid Table Data Is Present In PDF*****");
		String result = m_blueCoatReportPage.getTextFromCSV();
		log("result : " + result);
		
		log("*****Validate Month Is Present*****");
		for (String month : listOfMonth) {
			Assert.assertTrue(result.contains(month.replaceAll(",", "")), "CSV File Not contains Data " + month);
		}

	}
	
	
	/**
	 * This method is used to Run Sample Report for Month & Verify same data
	 * with the download PDF
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10)
	@TestDetail(testCaseID = "BC_10", testCaseName = "XML Report Validation Month", author = "abc@bluecoat.com")
	public void verify_BlueCoat_XML_Report_Month() throws Exception {

		log("*****Click On Report Center And Verify Grid Displayed*****");

		m_blueCoatDashboardPage = new BlueCoatDashboardPage(driver);
		m_blueCoatReportPage= new BlueCoatReportPage(driver);
		m_blueCoatDashboardPage.goToDashboard();
		m_blueCoatDashboardPage.changeDateToAllDates();
		
		m_blueCoatReportPage.clickReportCenter("bluecoatClickMonth");
		
		Assert.assertEquals(m_blueCoatReportPage.getText("bluecoatReportHeader"), "Month",
				"Report Month is Not Displayed");

		List<String> listOfMonth = m_blueCoatDashboardPage.getGridValueByMonth();
		log("listOfMonth : " + listOfMonth);

		log("*****Download Month Data*****");
		m_blueCoatReportPage.clickXMLPopUpDownload();

		log("*****Get Downloaded XML file and verify if the xml contain data");
		m_blueCoatReportPage.validateXML();
		log("XML validated successfully");
	}
	

}
