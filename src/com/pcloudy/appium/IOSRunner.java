package com.pcloudy.appium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSRunner {

	public static String userName = System.getenv("PCLOUDY_USERNAME") == null ? "Enter your email-id"
			: System.getenv("PCLOUDY_USERNAME");
	public static String apiKey = System.getenv("PCLOUDY_APIKEY") == null ? "Enter your API Key"
			: System.getenv("PCLOUDY_APIKEY");

	private static AppiumDriver<WebElement> driver;
	private static String folder_name;
	private static DateFormat df;

	public static void main(String[] args) throws MalformedURLException, InterruptedException, IOException {

		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("appium:trust", false);
			capabilities.setCapability("appium:newCommandTimeout", 600);
			capabilities.setCapability("appium:launchTimeout", 90000);
			capabilities.setCapability("appium:platformVersion", "15.0.0");
			capabilities.setCapability("appium:platformName", "iOS");
			capabilities.setCapability("appium:acceptAlerts", true);
			capabilities.setCapability("appium:automationName", "XCUITest");
			capabilities.setCapability("appium:bundleId", "com.pcloudy.TestmunkDemo");
			capabilities.setCapability("appium:pCloudy_ApplicationName", "pCloudy_Appium_Demo.ipa");

			HashMap<String, Object> pcloudyOptions = new HashMap<String, Object>();
			pcloudyOptions.put("pCloudy_Username", "anjali.y@opkey.com");
			pcloudyOptions.put("pCloudy_ApiKey", "r2s7km88f328xrzqz58rtk5c");
			pcloudyOptions.put("pCloudy_DeviceFullName", "APPLE_iPhone11ProMax_iOS_15.0.0_b99a8");
			pcloudyOptions.put("pCloudy_WildNet", false);
			pcloudyOptions.put("pCloudy_EnableVideo", false);
			pcloudyOptions.put("pCloudy_EnablePerformanceData", false);
			pcloudyOptions.put("pCloudy_EnableDeviceLogs", false);
			pcloudyOptions.put("appiumVersion", "3.1.1");
			capabilities.setCapability("pcloudy:options", pcloudyOptions);

			driver = new IOSDriver<WebElement>(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"),
					capabilities);

			System.out.println("execution started");
			driver.findElement(By.xpath(
					"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]"))
					.sendKeys("test@testname.com");
			captureScreenShots();
			Thread.sleep(3000);

			// Enter Password
			driver.findElement(By.xpath(
					"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]"))
					.sendKeys("testmunk");
			captureScreenShots();
			Thread.sleep(8000);

			// Click on login button
			driver.findElement(By.xpath(
					"//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"))
					.click();
			captureScreenShots();

			// Click Skip
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Skip']")).click();
			Thread.sleep(1800);

			// Click Show view button
			driver.findElement(By.xpath("//*[contains(@name,'SHOW ALERT VIEW')]")).click();
			captureScreenShots();

		} catch (AssertionError a) {
			a.printStackTrace();
		}

		driver.quit();
	}

	//Capture screenshot
	public static void captureScreenShots() throws IOException {
		folder_name = "screenshot";
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//Date format for screenshot file name
		df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		//create dir with given folder name
		new File(folder_name).mkdir();
		//Setting file name
		String file_name = df.format(new Date()) + ".png";
		//copy screenshot file into screenshot folder.
		FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
	}
}
