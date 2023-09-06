// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.testcases.ui;

import com.pxp.model.BaseClass;
import com.pxp.objectmaps.PatientCreationUIPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;

import com.medfusion.common.utils.PropertyFileLoader;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PPUi extends BaseClass {

	public static PropertyFileLoader testData;
	public static PPUi PPUI;
	public PatientCreationUIPage patientCreationUIPage;

	/////// Constructor
	public PPUi(PropertyFileLoader testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
	}

	public WebDriver getDriver(String browserName) {
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if (testData.getProperty("headless").equals("true"))
				options.addArguments("headless");
			if (testData.getProperty("incognito").equals("true"))
				options.addArguments("Incognito");
			options.addArguments("disable-infobars");
			options.addArguments("start-maximized");
			options.addArguments("--remote-allow-origins=*");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			if(driver == null)
				driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		}
		else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			FirefoxOptions options = new FirefoxOptions();

		}
		return driver;
	}

	public PPUi getPPui() throws InterruptedException {
		if (PPUI == null) {
			PPUI = new PPUi(testData, driver);
		}
		return PPUI;
	}

	public PatientCreationUIPage getPatientCreationUIPage() throws IOException {
		if(patientCreationUIPage == null)
			patientCreationUIPage = new PatientCreationUIPage();
		return patientCreationUIPage;
	}
}
