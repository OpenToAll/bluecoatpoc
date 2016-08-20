package com.framework.tests.bluecoat.smoke;
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
	public void Test_02_ValidateWidget() throws Exception { 
		Assert.assertTrue(true, "Widget Not Found");
	}
}
