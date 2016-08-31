package com.bluecoat.suite;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bluecoat.library.Global;

public class SendMail {
	
	@BeforeClass
	public void f(ITestContext context) {
		Global.SUITE_NAME= context.getCurrentXmlTest().getSuite().getName();
		//System.out.println("The suite  Output: " + Global.SUITE_NAME);
	}
	
	@Test (description = "Send Email Report")
	public void Test01_SendEmail() throws Exception { 
		//EmailFunctions email = new EmailFunctions();
		//email.send();
		System.out.println("Sent email");
	}
}
