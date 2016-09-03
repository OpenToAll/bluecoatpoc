package com.bluecoat.reporter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
	public static long m_totalDuration = 0;
	public static Map<String, List<String>> m_testResultMap = new LinkedHashMap<String, List<String>>();

	@Override
	public void onTestStart(ITestResult result) {
		Log.info("\n\n****************** START OF TEST CASE  " + result.getMethod().getMethodName()
				+ " ****************** \n");
	}

	@Override
	public void onFinish(ITestContext testContext) {

		generateAndMailReport(testContext);
	}

	@Override
	public void onTestFailure(ITestResult tr) {

		getTestResultDetails(tr);

		m_failCount++;
		printCurrentStatus();

	}

	@Override
	public void onTestSkipped(ITestResult tr) {

		getTestResultDetails(tr);

		m_skipCount++;
		printCurrentStatus();

	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		getTestResultDetails(tr);

		m_passCount++;
		printCurrentStatus();

	}

	public void printCurrentStatus() {
		System.out.println("\n CURRENT TEST EXECUTION STATUS -> PASS: " + m_passCount + "  FAIL: " + m_failCount
				+ "  SKIP: " + m_skipCount + "\n");
	}

	private void getTestResultDetails(ITestResult tr) {

		long testMethodDuration = 0;
		List<String> testDetails = new ArrayList<String>();

		testMethodDuration = tr.getEndMillis() - tr.getStartMillis();
		m_totalDuration += testMethodDuration;
		testDetails.add(String.valueOf(tr.getStatus()));
		testDetails.add(millisecondsToMS(testMethodDuration));

		m_testResultMap.put(tr.getName(), testDetails);
	}
	
	/**
	 * This Method is sued to convert to milliseconds to MM:SS (Minutes:Seconds)
	 * format
	 * 
	 * @param ms
	 * @return MM:SS
	 */
	private String millisecondsToMS(long ms) {

		String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(ms) % TimeUnit.MINUTES.toSeconds(1));
		return hms;
	}

	private void generateAndMailReport(ITestContext testContext) {

		if (null == JenkinsConnector.getJobName()) {
			ReportGenerator email = new ReportGenerator(testContext);
			try {
				email.generateReport();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
