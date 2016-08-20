package com.framework.tests.bluecoat.smoke;

import org.testng.annotations.Test;

import com.framework.tests.bluecoat.BlueCoatLibrary;

public class BlueCoatPOC extends BlueCoatLibrary {
	
	@Test (description = "One Point Time Sheet Report Generation")
	public void Test_01_BlueCoat_Login() throws Exception { 
		bluecoatLoginPage.login();
		
	}

}
