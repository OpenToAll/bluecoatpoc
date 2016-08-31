package com.bluecoat.reporter;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Implementing the TestNG listener to customize the console out put and also
 * push the execution results to a excel file with case ID and test methods
 * 
 */
public class TestListener extends TestListenerAdapter {

	public static boolean m_quitExecution = false;
	private int m_passCount = 0;
	private int m_failCount = 0;
	private int m_skipCount = 0;


	@Override
	public void onTestStart(ITestResult result) {
		Log.info("\n\n****************** START OF TEST CASE  " + result.getMethod().getMethodName() + " ****************** \n");
	}

	@Override
	public void onFinish(ITestContext testContext) {

	}

	@Override
	public void onTestFailure(ITestResult tr) {

		m_failCount++;
		printCurrentStatus();

	}

	@Override
	public void onTestSkipped(ITestResult tr) {

		m_skipCount++;
		printCurrentStatus();

	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		m_passCount++;
		printCurrentStatus();

	}

	public void printCurrentStatus() {
		System.out.println("\n CURRENT TEST EXECUTION STATUS -> PASS: " + m_passCount + "  FAIL: " + m_failCount
				+ "  SKIP: " + m_skipCount + "\n");
	}

}
