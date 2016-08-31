package com.bluecoat.reporter;

/**
 * Class to fetch iTAF version. iTAF version can be fetched from the command
 * prompt by executing command "--version"
 * 
 * @author Madhusudhan
 * 
 */
public class BuildVersion {

	/**
	 * Default Version Constructor
	 */
	public BuildVersion() {

	}

	/**
	 * This method is used to set and print the iTAF version in the command
	 * prompt
	 */
	public static String printVersion() {

		String version = "Blue Coat Test Automation Framework version 1.0";

		System.out.println(version);
		
		return version;
	}

}
