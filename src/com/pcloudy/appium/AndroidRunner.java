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
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidRunner {

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
			capabilities.setCapability("appium:newCommandTimeout", 600);
			capabilities.setCapability("appium:launchTimeout", 90000);
			capabilities.setCapability("appium:platformVersion", "11.0.0");
			capabilities.setCapability("appium:platformName", "Android");
			capabilities.setCapability("appium:automationName", "uiautomator2");
			capabilities.setCapability("appium:appPackage", "com.pcloudy.appiumdemo");
			capabilities.setCapability("appium:appActivity", "com.ba.mobile.LaunchActivity");
			capabilities.setCapability("appium:pCloudy_ApplicationName", "pCloudyAppiumDemo.apk");

			HashMap<String, Object> pcloudyOptions = new HashMap<String, Object>();
			pcloudyOptions.put("pCloudy_Username", userName);
			pcloudyOptions.put("pCloudy_ApiKey", apiKey);
			pcloudyOptions.put("pCloudy_DeviceFullName", "SAMSUNG_GalaxyA10s_Android_11.0.0_99cde");
			pcloudyOptions.put("pCloudy_WildNet", false);
			pcloudyOptions.put("pCloudy_EnableVideo", true);
			pcloudyOptions.put("pCloudy_EnablePerformanceData", false);
			pcloudyOptions.put("pCloudy_EnableDeviceLogs", false);
			pcloudyOptions.put("appiumVersion", "3.1.1");
			capabilities.setCapability("pcloudy:options", pcloudyOptions);

			driver = new AndroidDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);

			//Click on Accept button
			driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/accept']")).click();
			captureScreenShots();

			//Click on Flight button
			driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/flightButton']")).click();
			captureScreenShots();

			//Select from location
			driver.findElement(By.xpath("//android.widget.Spinner[@resource-id='com.pcloudy.appiumdemo:id/spinnerfrom']")).click();
			captureScreenShots();
			driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Bangalore, India (BLR)']")).click();
			captureScreenShots();

			//Select to location
			driver.findElement(By.xpath("//android.widget.Spinner[@resource-id='com.pcloudy.appiumdemo:id/spinnerto']")).click();
			captureScreenShots();
			driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Pune, India (PNQ)']")).click();
			captureScreenShots();

			//Select one way trip
			driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id='com.pcloudy.appiumdemo:id/singleTrip']")).click();

			//Select departure time
			driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pcloudy.appiumdemo:id/txtdepart']")).click();
			captureScreenShots();
			driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1' and @text='OK']")).click();
			captureScreenShots();

			//Click on search flights button
			driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/searchFlights']")).click();
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
