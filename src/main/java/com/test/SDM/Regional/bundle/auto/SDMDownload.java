package com.test.SDM.Regional.bundle.auto;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.json.JSONObject;
import com.test.json.RestClient;


public class SDMDownload {

	public static int firstIndex;
	public static int pageIndex;
	public static String product_type;
	public static com.test.config.Constants Constants;

	/**Created by : Rajesh Singh
	 * 
	 * This method login into SDM portal and start downloading the specific bundle
	 * as per JSON response from SDM API Defined the configuration setting.
	 * Pre-requisuite: 
	 * 1.) Browser should installed into running machine. 
	 * 2.) Machine should access the SDM portal.
	 * 
	 * 
	 * Steps: 
	 * 1.) Opening the Chrome Browser - defined browser driver setting.
	 * 2.) Launch the SDM application into Chrome browser 
	 * 3.) Login into application with valid credentials - AUTOIT
	 * 4.) Go to "Delivery" tab option and clear the filter 
	 * 5.) Search for bundle in filter option- JSON response 
	 * 6.) Select the specific bundle - version info defined as MAVEN property. 
	 * 7.) Validate the version info before download. 
	 * 8.) For successful validation start downloading the bundle - browser default location 
	 * 9.) Iterate and search for next bundle - Repeat step 6 and 7.
	 * 
	 * @return
	 */

	public static void main(String[] args) throws Exception {

		Constants = new com.test.config.Constants();

		if (args == null || args.length == 0) {
			System.out.println("Version in POM is missing !!!!");
			System.exit(1);
		}
		
		System.setProperty("webdriver.chrome.driver", Constants.Chrome_Driver);
		
		WebDriver driver = new ChromeDriver();
		driver.get(Constants.URL);
		Thread.sleep(15000);
		Runtime.getRuntime().exec(Constants.HandleAuthentication);

		Thread.sleep(15000);
		driver.navigate().to(Constants.DEL_URL);
		Thread.sleep(7000);
		RestClient client = new RestClient();
		List<JSONObject> jsonSDMList = client.getSDMProductList();
		//setting required
		String bundle_product_type = "GEOCODING";

		for (Iterator iterator = jsonSDMList.iterator(); iterator.hasNext();) {
			Thread.sleep(3000);
			JSONObject jsonObject = (JSONObject) iterator.next();

			if (jsonObject.getSdmproductName().trim().length() > 0
					&& !jsonObject.getSdmproductName().trim().equalsIgnoreCase("?")) {
				System.out.println("Sending SDM Name as:-->" + jsonObject.getSdmproductName());
				Thread.sleep(5000);
				driver.findElement(By.xpath(Constants.txtbx_Search)).clear();
				Thread.sleep(2000);
				driver.findElement(By.xpath(Constants.txtbx_Search)).sendKeys(jsonObject.getSdmproductName());
				Thread.sleep(4000);

				String numberofrecords = driver.findElement(By.xpath(Constants.btn_record_count)).getText();
				String numberofpages = driver.findElement(By.xpath(Constants.btn_page_count)).getText();

				Thread.sleep(4000);

				if (numberofrecords.contains("No")) {
					Thread.sleep(4000);
					System.out.println("Downloadable bundle not found for following search : "
							+ jsonObject.getSdmproductName() + "-----FAILED");
					System.out.println("ERROR : "
							+ jsonObject.getSdmproductName() + "-----DISCREPENCY Observed");
					driver.findElement(By.xpath(Constants.txtbx_Search)).clear();

				} else if (numberofrecords.contains("(")) {
					firstIndex = numberofrecords.indexOf('R');
					int loop_elements = Integer.parseInt(numberofrecords.substring(0, firstIndex).trim());

					pageIndex = numberofpages.indexOf('f');
					int page_loop_elements = Integer
							.parseInt(numberofpages.substring(pageIndex + 1, numberofpages.length()).trim());

					for (int p = 1; p <= page_loop_elements; p++)

					{

						Thread.sleep(5000);
						WebElement ele_specific_bundle = driver.findElement(
								By.xpath("//div[(text()='GEOCODING')]/following::div[text()='" + args[0] + "'][1]"));
						ele_specific_bundle.click();
						Thread.sleep(5000);

						String version_info = driver.findElement(By.xpath(Constants.txt_versionInfo)).getText();
						if (version_info.contains(args[0])) {
							WebElement ele_product_type = driver.findElement(By.xpath(Constants.txt_producttypeInfo));
							product_type = ele_product_type.getText();

							if (product_type.contains(bundle_product_type)) {
								System.out.println("Product type & version validation PASS - " + "Product Type: "
										+ product_type + ", Version: " + version_info);
								driver.findElement(By.xpath(Constants.btn_Download)).click();
								System.out.println(
										"Downloading the bundle version info: " + version_info + "  ---STARTED");
							}

						} else {
							System.out.println("Skipping the bundle for donwload: " + version_info + "  ---SKIPPED");
						}

						Thread.sleep(7000);
						driver.findElement(By.xpath(Constants.btn_Back)).click();
						Thread.sleep(5000);


					}
				}
			} else {
				System.out.println("Search result not found");
				continue;

			}
		}

	}

}
