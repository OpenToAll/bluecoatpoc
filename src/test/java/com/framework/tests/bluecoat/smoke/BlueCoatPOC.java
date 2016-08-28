package com.framework.tests.bluecoat.smoke;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.tests.bluecoat.BlueCoatLibrary;

/**
 * @author BlueCoat Team
 *
 */
public class BlueCoatPOC extends BlueCoatLibrary {
	String dashBoardValueBusinessRelated;
	String selectField = "Business Related";
	
	/**
	 * @throws Exception
	 */
	@Test (description = "Login To Blue Coat")
	public void Test_01_BlueCoat_Login() throws Exception { 
		log("*****Login Into Blue Coat*****");
		bluecoatLoginPage.login();
		Assert.assertTrue(bluecoatLoginPage.getTitle().contains("Blue Coat ThreatPulse"), "Title Not found");
		log("*****Successful DashBoard Widget*****");
	}
	
	
	@Test (description = "DashBoard Widget Validation")
	public void Test_02_BlueCoat_DashBoard_WebSite_Full_Report() throws Exception { 
		log("*****Get Grid Value Of Web Browser Site And Verify Same In Full Report*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatwebbrowsingfullreportlink");
		blueCoatDashboardPage.getGridValueOfWebBrowserSite();
		blueCoatDashboardPage.clickWebBrowsingSiteFullReport();
		blueCoatDashboardPage.clickFullReportWebBrowserSitePoints();
		Assert.assertTrue(blueCoatDashboardPage.verifyGridAndGraphOfWebBrowserSite(), "Web Browse Site Grid And Graph Value Is Not Same");
		log("*****Successful Dashboard Overview Count*****");
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "DashBoard Widget Validation")
	public void Test_03_BlueCoat_DashBoard_Widget_Validate() throws Exception { 
		blueCoatDashboardPage.goToDashboard();
		List<String> listOfWidgetTitle = blueCoatDashboardPage.getAllWidgetTitles();
		Assert.assertTrue(blueCoatDashboardPage.titleIsPresent(listOfWidgetTitle, "Trend of Threats"), "Title Not found");
		Assert.assertTrue(blueCoatDashboardPage.titleIsPresent(listOfWidgetTitle, "Social Media Applications"), "Title Not found");
		Assert.assertTrue(blueCoatDashboardPage.titleIsPresent(listOfWidgetTitle, "Risk Groups"), "Title Not found");
		Assert.assertTrue(blueCoatDashboardPage.titleIsPresent(listOfWidgetTitle, "Web Browsing per Site"), "Title Not found");
		//Assert.assertTrue(blueCoatDashboardPage.titleIsPresent(listOfWidgetTitle,  "Web Browsing per Category"), "Title Not found");
		Assert.assertTrue(blueCoatDashboardPage.titleIsPresent(listOfWidgetTitle, "Web Browsing per User"), "Title Not found");
		Assert.assertTrue(listOfWidgetTitle.size() == 6, "All Title Not Found Count " +  listOfWidgetTitle.size());
	}

	/**
	 * @throws Exception
	 */
	@Test (description = "DashBoard Widget Validation")
	public void Test_04_BlueCoat_DashBoard_Widget_Enable() throws Exception { 
		log("*****Enable Grid Only, Verify Grid Is Displayed, And Graph Is Not Displayed*****");
		blueCoatDashboardPage.goToDashboard();
		blueCoatDashboardPage.clickRiskSetting("Grid Only");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		Assert.assertFalse(blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartnotdisplayed"), "Risk Chart Widget Is Displayed");
		
		log("*****Enable Chart Only, Verify Chart Is Displayed, And Grid Is Not Displayed*****");
		blueCoatDashboardPage.clickRiskSetting("Chart Only");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		Assert.assertFalse(blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Disabled");

		log("*****Enable Grid and Chart, Verify Grid Is Displayed, And Chart Is Displayed*****");
		blueCoatDashboardPage.clickRiskSetting("Both Grid and Chart");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgrouptable"), "Risk Table Widget Is Not Enable");
		Assert.assertTrue(blueCoatDashboardPage.isDisplay("bluecoatriskgroupchartdisplayed"), "Risk Chart Widget Is Displayed");
		log("*****Successful DashBoard Widget*****");
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "DashBoard Widget Validation")
	public void Test_05_BlueCoat_DashBoard_Risk_Graph_Value() throws Exception { 
		log("*****Validate Security In Grid And Graph Tooltip Value Same*****");
		
		log("*****Validate Business Related Graph Count With Grid*****");
		String value = blueCoatDashboardPage.getValueInGrid("Business Related");
		Assert.assertTrue(blueCoatDashboardPage.isValuePresentInGraph(value), "Business Related Value Not Found" );
		
		
		log("*****Validate Non-Productive Graph Count With Grid*****");
		value = blueCoatDashboardPage.getValueInGrid("Non-Productive");
		Assert.assertTrue(blueCoatDashboardPage.isValuePresentInGraph(value), "Non-Productive Value Not Found");
		log("*****Successful DashBoard_Graph_Value*****");
	}
	
	@Test (description = "DashBoard Widget Validation")
	public void Test_06_BlueCoat_DashBoard_OverviewDayCounts() throws Exception { 
		log("*****Dashboard Overview Count*****");
		blueCoatDashboardPage.goToDashboard();
		log("*****Get Graph Value And Validate With Grid Value*****");
		dashBoardValueBusinessRelated = blueCoatDashboardPage.getValueInGrid(selectField);
		blueCoatDashboardPage.clickLinkInGrid(selectField);
	
		blueCoatDashboardPage.clickOverviewSetting("Both Grid and Chart", "Day");
		blueCoatDashboardPage.clickDayGraphAndGetValue();
		blueCoatDashboardPage.getDayGRidValue();
		Assert.assertTrue(blueCoatDashboardPage.verifyDayGridAndGraph(), "Day Grid And Graph Value Is Not Same");
		log("*****Successful Dashboard Overview Count*****");
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "DashBoard Widget Validation")
	public void Test_07_BlueCoat_DashBoard_Overview_Count() throws Exception { 
		log("*****Dashboard Overview Count*****");

		log("*****Client IP Count*****");
		String count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Client IP");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value " 
				+  dashBoardValueBusinessRelated + " Overview page  protocol Count " + count);
		
		log("*****Protocol Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Protocol");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Protocol");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value " 
				+  dashBoardValueBusinessRelated + " Overview page  protocol Count " + count);

		log("*****Verdict Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Verdict");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Verdict");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value "
				+  dashBoardValueBusinessRelated + " Overview page  Verdict Count " + count);

		log("*****Location Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Location");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Location");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value " 
				+  dashBoardValueBusinessRelated + " Overview page  Location Count " + count);
		
		log("*****Site Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Site");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Site");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value "
				+  dashBoardValueBusinessRelated + " Overview page  Site Count " + count);
		
		log("*****Web Application Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Web Application");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Web Application");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value "
				+  dashBoardValueBusinessRelated + " Overview page  Web Application Count " + count);

		log("*****Subnet Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Subnet");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Subnet");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value "
				+  dashBoardValueBusinessRelated + " Overview page  Subnet Count " + count);

		log("*****User Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "User");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "User");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value " 
				+  dashBoardValueBusinessRelated + " Overview page  User Count " + count);

		log("*****Web Application Action Count*****");
		blueCoatDashboardPage.ScrollToElement("bluecoatoverviewpage", "Web Application Action");
		count = blueCoatDashboardPage.getText("bluecoatoverviewpage", "Web Application Action");
		Assert.assertTrue(count.equals(dashBoardValueBusinessRelated),   selectField + "Dashboard Value " 
				+  dashBoardValueBusinessRelated + " Overview page  Web Application Action Count " + count);
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "Report Validation Year")
	public void Test_08_BlueCoat_Report_Year() throws Exception { 
		log("*****Click On Report Center And Verify Grid Displayed*****");
		blueCoatDashboardPage.goToDashboard();
		blueCoatReportPage.clickReportCenter("bluecoatClickYear");
		Assert.assertEquals(blueCoatReportPage.getText("bluecoatReportHeader"),"Year","Report Year is Not Displayed");
		
		log("*****Get Value of Grid From The Table*****");
		String getGridValue = blueCoatReportPage.getText("reportpagevalue");
		
		log("*****Download Year Data*****");
		blueCoatReportPage.clickPopUpDownload();
		
		log("*****Get Downloaded PDF, Data Read And Verify Grid Table Data Is Present In PDF*****");
		String result=blueCoatReportPage.getTextFromPDF();
		Assert.assertTrue(result.contains(getGridValue), "PDF File Not contains Data "  + getGridValue);
	}
	
	/**
	 * @throws Exception
	 */
	@Test (description = "Report Validation Month")
	public void Test_09_BlueCoat_Report_Month() throws Exception { 
		log("*****Click On Report Center And Verify Grid Displayed*****");
		blueCoatDashboardPage.goToDashboard();
		blueCoatReportPage.clickReportCenter("bluecoatClickMonth");
		Assert.assertEquals(blueCoatReportPage.getText("bluecoatReportHeader"),"Month","Report Month is Not Displayed");
		
		List<String> listOfMonth = blueCoatDashboardPage.getGridValueByMonth();
		
		log("*****Download Month Data*****");
		blueCoatReportPage.clickPopUpDownload();
		
		log("*****Get Downloaded PDF, Data Read And Verify Grid Table Data Is Present In PDF*****");
		String result=blueCoatReportPage.getTextFromPDF();
	
		log("*****Validate Month Is Present*****");
		for(String month: listOfMonth) {
			Assert.assertTrue(result.contains(month), "PDF File Not contains Data "  + month);
		}
		
	}
}
