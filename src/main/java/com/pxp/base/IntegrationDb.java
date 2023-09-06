//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.base;

import com.pxp.objectmaps.PatientCreationAndActivationPage;
import com.pxp.util.DBUtils;
import org.openqa.selenium.WebDriver;
import com.medfusion.common.utils.PropertyFileLoader;
import java.io.IOException;

public class IntegrationDb {
	private WebDriver driver;
	private PropertyFileLoader testData;
	private IntegrationDb integrationDb;
	private PatientCreationAndActivationPage patientCreationAndActivationPage;

	/////// Constructor
	public IntegrationDb(PropertyFileLoader testData2) {
		this.testData = testData2;
	}

	///////////// Variables Page Objects
	DBUtils dbUtils;
	
	/////////// Variables Test Data
	public IntegrationDb getIntegrationDb() throws InterruptedException {
		if (integrationDb == null) {
			integrationDb = new IntegrationDb(testData);
		}
		return integrationDb;
	}

	public DBUtils getDbConnection() throws InterruptedException {
		if (dbUtils == null)
			dbUtils = new DBUtils();
		return dbUtils;
	}

	public PatientCreationAndActivationPage getPatientCreationAndActivationPage() throws IOException {
		if(patientCreationAndActivationPage == null)
			patientCreationAndActivationPage = new PatientCreationAndActivationPage();
		return patientCreationAndActivationPage;
	}

}
