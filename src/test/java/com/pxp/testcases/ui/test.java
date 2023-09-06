package com.pxp.testcases.ui;

import com.medfusion.common.utils.PropertyFileLoader;
import com.pxp.base.ConstantConfigData;
import com.pxp.base.IntegrationDb;
import com.pxp.base.TestBase;
import com.pxp.model.BaseClass;
import com.pxp.model.PXPInit;
import com.pxp.queries.PIDCQueries;
import com.pxp.util.PIDCInfo;
import com.pxp.util.PatientRandomDetails;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.Connection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.intuit.ifs.csscat.core.utils.Log4jUtil.log;

public class test extends TestBase {

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
        ppUi.getDriver("chrome");
        integrationDb = new IntegrationDb(testData);
        pidcQueries = new PIDCQueries();
    }

    @Test
    public void test() throws Exception {
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
