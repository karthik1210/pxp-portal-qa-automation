// Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.base;
import com.pxp.model.BaseClass;
import com.pxp.setup.EnvironmentTypeSetUp;
import com.pxp.setup.PropertyFileLoader;
import com.pxp.setup.TestConfig;
import com.pxp.util.DBUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
	public static Logger log = LogManager.getLogger(TestBase.class);

	protected IntegrationDb integrationDb;
	protected PropertyFileLoader testData;
	protected String mfDb = null;
	protected String ngDb = null;
	protected BaseClass baseClass;

	@BeforeClass(alwaysRun = true)
	public void setUpTestData() throws Exception {
		testData = new PropertyFileLoader();
		BasicConfigurator.configure();
		mfDb = testData.getProperty("db.config.name");
		ngDb = testData.getProperty("db.nextgen.name");
		initializeDB();
		log.info("Execution Environment: " + BaseClass.getEnvironmentType());
		log.info("Execution Browser: " + TestConfig.getBrowserType());
	}

	public BaseClass getBaseClass() {
		if(baseClass == null)
			baseClass = new BaseClass();
		return baseClass;
	}

	public void initializeDB() throws InterruptedException {
		integrationDb = new IntegrationDb(testData);
		String url = testData.getProperty("db.server") + "databaseName=" + testData.getProperty("db.nextgen.name") + ";encrypt=true;trustServerCertificate=true";
		integrationDb.getDbConnection().getConnection(url, testData.getProperty("db.user"), testData.getProperty("db.pass"));
	}

	
    @AfterClass(alwaysRun = true)
	public void closeDbConnection() throws Exception {
		DBUtils.closeConnection();
	}
}
