package com.pxp.testcases.ui;
import com.pxp.base.IntegrationDb;
import com.pxp.model.BaseClass;
import com.pxp.model.PXPInit;
import com.pxp.queries.PIDCQueries;
import com.pxp.setup.PropertyFileLoader;
import com.pxp.setup.TestConfig;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.internal.Utils.log;

public class PatientCreationAndActivation extends BaseClass {

    PropertyFileLoader testData;
    String personId = "";
    PIDCQueries pidcQueries;
    PPUi ppUi;

    @BeforeClass
    public void beforeClass() throws Exception {
        testData = new PropertyFileLoader();
        log("Setting up the Test Data.");
        PXPInit pxpInit= new PXPInit(testData);
        pxpInit.init();
        BasicConfigurator.configure();
        ppUi = new PPUi(testData, driver);
        ppUi.getDriver(TestConfig.getBrowserType());
        integrationDb = new IntegrationDb(testData);
        pidcQueries = new PIDCQueries();
    }

    @Test
    public void createPatientAndActivation() throws Exception {
        personId = integrationDb.getIntegrationDb().getPatientCreationAndActivationPage().createPatient();
        String email = pidcQueries.getEmail(personId);
        ppUi.getPPui().getPatientCreationUIPage().activatePatient(email);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

}
