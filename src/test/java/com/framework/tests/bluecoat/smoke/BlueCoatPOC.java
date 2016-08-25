package com.framework.tests.bluecoat.smoke;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.tests.bluecoat.BlueCoatLibrary;

public class BlueCoatPOC extends BlueCoatLibrary {
	
	@Test (description = "Login To Blue Coat")
	public void Test_01_BlueCoat_Login() throws Exception { 
		bluecoatLoginPage.login();
		Assert.assertTrue(bluecoatLoginPage.getTitle().contains("Blue Coat ThreatPulse"), "Title Not found");
	}

	@Test (description = "Widget Validation")
	public void Test_02_Validate_Widget() throws Exception { 
		log("Enable Grid Only And Verify Grid Displayed");
		bluecoatLoginPage.clickRiskSetting("Grid Only");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		log("Verify Graph Is Not Displayed");
		Assert.assertFalse(bluecoatLoginPage.isDisplay("bluecoatriskgroupchartnotdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("Enable Chart Only And Verify Chart Displayed");
		bluecoatLoginPage.clickRiskSetting("Chart Only");
		log("Verify Table Is Not Displayed");
		Assert.assertFalse(bluecoatLoginPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Disabled");
		log("Verify Graph is displayed");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("Enable Grid and Chart, Verify Grid And Chart Displayed");
		bluecoatLoginPage.clickRiskSetting("Both Grid and Chart");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		Assert.assertTrue(bluecoatLoginPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		
	}
	
	@Test (description = "Report Validation")
	public void Test_03_Testing_Report_PDF() throws Exception { 
		log("Click on Report Center And Verify Grid Displayed");
		bluecoatLoginPage.clickReportCenter();
		Assert.assertEquals(bluecoatLoginPage.getText("bluecoatReportHeader"),"Year","Report Year is Not Displayed");
		bluecoatLoginPage.clickPopUpDownload();
		String result=bluecoatLoginPage.getTextFromPDF();
		Assert.assertTrue(result.contains("Year"), "PDF File Not contains Year text");
		
	}
}
