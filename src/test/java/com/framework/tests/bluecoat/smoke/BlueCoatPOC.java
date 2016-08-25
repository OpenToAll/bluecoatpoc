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
		bluecoatLoginPage.login();
		Assert.assertTrue(blueCoatDashboardPage.getTitle().contains("Blue Coat ThreatPulse"), "Title Not found");
	}

	/**
	 * @throws Exception
	 */
	@Test (description = "Widget Validation")
	public void Test_02_Validate_Widget() throws Exception { 
		log("Enable Grid Only And Verify Grid Displayed");
		blueCoatDashboardPage.clickRiskSetting("Grid Only");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		log("Verify Graph Is Not Displayed");
		Assert.assertFalse(bluecoatLoginPage.isDisplay("bluecoatriskgroupchartnotdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("Enable Chart Only And Verify Chart Displayed");
		blueCoatDashboardPage.clickRiskSetting("Chart Only");
		log("Verify Table Is Not Displayed");
		Assert.assertFalse(bluecoatLoginPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Disabled");
		log("Verify Graph is displayed");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("Enable Grid and Chart, Verify Grid And Chart Displayed");
		blueCoatDashboardPage.clickRiskSetting("Both Grid and Chart");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "Report Validation")
	public void Test_03_Testing_Report_PDF() throws Exception { 
		log("Click on Report Center And Verify Grid Displayed");
		blueCoatReportPage.clickReportCenter();
		Assert.assertEquals(blueCoatReportPage.getText("bluecoatReportHeader"),"Year","Report Year is Not Displayed");
		String getGridValue = blueCoatReportPage.getText("reportpagevalue");
		blueCoatReportPage.clickPopUpDownload();
		String result=blueCoatReportPage.getTextFromPDF();
		Assert.assertTrue(result.contains(getGridValue), "PDF File Not contains Data "  + getGridValue);
		
	}
}
