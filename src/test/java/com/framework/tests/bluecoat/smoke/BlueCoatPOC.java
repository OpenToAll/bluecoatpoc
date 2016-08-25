package com.framework.tests.bluecoat.smoke;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.tests.bluecoat.BlueCoatLibrary;

/**
 * @author BlueCoat Team
 *
 */
public class BlueCoatPOC extends BlueCoatLibrary {
	
	/**
	 * @throws Exception
	 */
	@Test (description = "Login To Blue Coat")
	public void Test_01_BlueCoat_Login() throws Exception { 
		log("*****Login Into Blue Coat*****");
		bluecoatLoginPage.login();
		Assert.assertTrue(bluecoatLoginPage.getTitle().contains("Blue Coat ThreatPulse"), "Title Not found");
	}

	/**
	 * @throws Exception
	 */
	@Test (description = "DashBoard Widget Validation")
	public void Test_02_BlueCoat_DashBoard() throws Exception { 
		log("*****Enable Grid Only And Verify Grid Displayed*****");
		blueCoatDashboardPage.clickRiskSetting("Grid Only");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		log("*****Verify Graph Is Not Displayed*****");
		Assert.assertFalse(blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartnotdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("*****Enable Chart Only And Verify Chart Displayed*****");
		blueCoatDashboardPage.clickRiskSetting("Chart Only");
		
		log("Verify Table Is Not Displayed");
		Assert.assertFalse(blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Disabled");
		
		log("Verify Graph is displayed");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("*****Enable Grid and Chart, Verify Grid And Chart Displayed*****");
		blueCoatDashboardPage.clickRiskSetting("Both Grid and Chart");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "Report Validation")
	public void Test_03_BlueCoat_Report() throws Exception { 
		log("*****Click On Report Center And Verify Grid Displayed*****");
		blueCoatReportPage.clickReportCenter();
		Assert.assertEquals(blueCoatReportPage.getText("bluecoatReportHeader"),"Year","Report Year is Not Displayed");
		
		log("*****Get Value of Grid From The Table*****");
		String getGridValue = blueCoatReportPage.getText("reportpagevalue");
		
		log("*****Download Year Data*****");
		blueCoatReportPage.clickPopUpDownload();
		
		log("*****Get Downloaded PDF Data Read And Verify Grid Table Is Present In PDF*****");
		String result=blueCoatReportPage.getTextFromPDF();
		Assert.assertTrue(result.contains(getGridValue), "PDF File Not contains Data "  + getGridValue);
		
	}
}
