// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.testcases.ui;

import com.pxp.model.BaseClass;
import com.pxp.objectmaps.PatientCreationUIPage;
import com.pxp.setup.BrowserTypeUtil;
import com.pxp.setup.PropertyFileLoader;
import com.pxp.util.OptionManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PPUi extends BaseClass {

	public static PropertyFileLoader testData;
	public static PPUi PPUI;
	public PatientCreationUIPage patientCreationUIPage;
	OptionManager optionManager;

	/////// Constructor
	public PPUi(PropertyFileLoader testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
		optionManager = new OptionManager(testData);
	}

	public WebDriver getDriver(BrowserTypeUtil.BrowserType browserType) throws MalformedURLException {
		if(driver == null) {
			String browser = browserType.toString();
			if (browser.contains("chrome")) {
				ChromeOptions options = optionManager.getChromeOptions();
				if (testData.getProperty("remoteWebDriver").equalsIgnoreCase("true"))
					driver = new RemoteWebDriver(new URL(testData.getProperty("hubUrl")), options);
				driver = new ChromeDriver(options);
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
				driver.manage().deleteAllCookies();
				return driver;
			} else if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = optionManager.getFirefoxOptions();
				if (testData.getProperty("remoteWebDriver").equalsIgnoreCase("true"))
					driver = new RemoteWebDriver(new URL(testData.getProperty("hubUrl")), options);
				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
				driver.manage().deleteAllCookies();
				return driver;
			} else if (browser.equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
				if (testData.getProperty("remoteWebDriver").equalsIgnoreCase("true"))
					driver = new RemoteWebDriver(new URL(testData.getProperty("hubUrl")), options);
				driver = new EdgeDriver(options);
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
				driver.manage().deleteAllCookies();
				return driver;
			}
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
