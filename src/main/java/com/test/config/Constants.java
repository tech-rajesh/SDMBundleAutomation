package com.test.config;

public class Constants {
	
	//Setting
	public static final String URL = "https://product-definition.pitneybowes.com";
	public static final String DEL_URL = "https://product-definition.pitneybowes.com/#!/home/delivery";
	public static final String Path_OR = "src//main//java//com//test//config//OR.txt";
	public static final String Chrome_Driver = ".\\browserDrivers\\chromedriver.exe";
	public static final String HandleAuthentication = ".\\browserDrivers\\HandleAuthentication.exe";

		
	// Delivery page - Object Properties
	public static final String txtbx_Search = "//input[@type='text']";
	public static final String btn_record_count = "//button/span/b";
	public static final String btn_page_count = "//button[contains(text(),'Page')]";

	
	// Delivery Information page - Object Properties
	public static final String btn_Download ="(//button[@type='button'])[3]";
	public static final String btn_Back ="//button";
	public static final String txt_versionInfo = "//div[@class='row']/div[text()='Version']/following::div[@class='col-sm-4 _lText']";
	public static final String txt_producttypeInfo = "//div[@class='row']/div[text()='Data']/following::div[@class='col-sm-4 _lText'][1]";
	

}
