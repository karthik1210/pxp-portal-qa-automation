// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.testcases.api;

import com.pxp.base.IntegrationDb;
import com.pxp.base.TestBase;
import com.pxp.helpers.PPNGEIntegrationService;
import com.pxp.helpers.Sample;
import com.pxp.model.BaseClass;
import com.pxp.model.BaseRest;
import com.pxp.queries.PIDCQueries;
import com.pxp.util.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.intuit.ifs.csscat.core.utils.Log4jUtil.log;

public class PPNGEIntegration extends TestBase {

    Response getNgeIntegration;
    PPNGEIntegrationService ppNGEIntegrationService;
    Validations validations = new Validations();
    Sample sample;
    BaseRest baseRest;
    IntegrationDb integrationDb;
    PIDCQueries pidcQueries;

    public PPNGEIntegration() throws Exception {
        baseRest = new BaseRest();
        pidcQueries = new PIDCQueries();
    }

    @BeforeClass()
    public void setBaseUri() throws Exception {
        baseRest.setupServiceRequestSpecificationBuilder();
        ppNGEIntegrationService = new PPNGEIntegrationService();
        integrationDb = new IntegrationDb(testData);
    }

    @DataProvider(name = "getPracticeId")
    public Object[][] getPracticeId() {
        Object[][] obj = new Object[][]{{testData.getProperty("invalidPracticeId")},
                {null}, {"karthik"}};
        return obj;
    }

    @DataProvider(name = "getStartAndEndTime")
    public Object[][] getStartAndEndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Object[][] obj = new Object[][]{{"", ""},
                {"null", "null"}, {"karthik", "karthik"}, {dtf.format(now), dtf.format(now)}};
        return obj;
    }

    @Test(priority = 1, description = "verify 200 ok status is displayed when valid parameter is passed to fetch lab results")
    public void getNGEIntegrationWithValidPracticeId() throws Exception {
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegration(Integer.parseInt(testData.getProperty("validPracticeId")), testData.getProperty("fetchStartTime"), testData.getProperty("fetchEndTime"));
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 200 || getNgeIntegration.getStatusCode() == 204;
    }

    @Test(priority = 2, description = "verify 204 No Content status is displayed when valid parameter is passed to fetch lab results which doesn't have lab results")
    public void getNGEIntegrationWithValidPracticeIdAndNoLabOrder() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String endTime = (dtf.format(now) + ".002").replace("/", "-").replace(" ", "T");
        String startTime = (dtf.format(now) + ".001").replace("/", "-").replace(" ", "T");
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegration(Integer.parseInt(testData.getProperty("validPracticeId")), startTime, endTime);
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 204;
    }

    @Test(priority = 3, description = "verify 404 Not Found is displayed when invalid url is passed to fetch lab results ")
    public void getNGEIntegrationWithInvalidURI() throws Exception {
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegrationWithInvalidURI(Integer.parseInt(testData.getProperty("validPracticeId")), testData.getProperty("fetchStartTime"), testData.getProperty("fetchEndTime"));
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 404;
    }

    @Test(priority = 4, description = "verify 405 Method Not Allowed is displayed when valid parameter is passed to fetch lab results using Patch Request")
    public void getNGEIntegrationWithInvalidMethod() throws Exception {
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegrationWithInvalidMethod(Integer.parseInt(testData.getProperty("validPracticeId")), testData.getProperty("fetchStartTime"), testData.getProperty("fetchEndTime"));
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 405;
    }

    @Test(priority = 5, dataProvider = "getPracticeId", description = "verify 400 Bad Request is displayed when Non-Existing practice Id/null/invalid datatype on practice id is passed to fetch lab results")
    public void getNGEIntegrationWithInvalidPracticeId(String practiceId) throws Exception {
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegration(practiceId, testData.getProperty("fetchStartTime"), testData.getProperty("fetchEndTime"));
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 400;
    }

    @Test(priority = 6, description = "verify 404 Not Found is displayed when empty on practice id is passed to fetch lab results ")
    public void getNGEIntegrationWithEmptyPracticeId() throws Exception {
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegration("", testData.getProperty("fetchStartTime"), testData.getProperty("fetchEndTime"));
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 404;
    }

    @Test(priority = 7, dataProvider = "getStartAndEndTime", description = "verify 400 Bad Request is displayed when Null/Invalid datatype/empty/Invalid timestamp on start /end time and valid practice id  to fetch lab results")
    public void getNGEIntegrationWithInvalidStartAndEndTime(String startTime, String endTime) throws Exception {
        getNgeIntegration = ppNGEIntegrationService.getNgeIntegration(Integer.parseInt(testData.getProperty("validPracticeId")), startTime, endTime);
        log("Validating response information");
        BaseClass.responseTimeValidation(getNgeIntegration);
        assert getNgeIntegration.getStatusCode() == 400;
    }

}
