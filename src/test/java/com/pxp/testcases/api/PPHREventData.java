package com.pxp.testcases.api;

import com.medfusion.common.utils.PropertyFileLoader;
import com.pxp.helpers.PPHREventDataService;
import com.pxp.helpers.Sample;
import com.pxp.model.BaseClass;
import com.pxp.model.BaseRest;
import com.pxp.util.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PPHREventData extends BaseRest {

    Response getHREVENTDATA;
    PPHREventDataService pphrEventDataService;
    Validations validations = new Validations();
    Sample sample;

    public PPHREventData() throws Exception {
        sample = new Sample();
        testData = new PropertyFileLoader();
    }

    @BeforeClass()
    public void setBaseUri() throws Exception {
        setupServiceRequestSpecificationBuilder();
        pphrEventDataService = new PPHREventDataService();
    }

    @DataProvider(name = "getRealTime")
    public Object[][] getRealTime() {
        Object[][] obj = new Object[][]{{1, testData.getProperty("activate_Event")}, {0, testData.getProperty("activate_Event")},
                {1, testData.getProperty("deactivate_Event")}, {0, testData.getProperty("deactivate_Event")}};
        return obj;
    }

    @Test(priority = 1, dataProvider = "getRealTime")
    public void getHREVENTDATA(int realTime, String event) throws Exception {
        String requestId = BaseClass.generateGUID();
        String practiceId = BaseClass.generateRandomNumber(5);
        sample.sqs_ecs_logs_workflow(requestId, event, practiceId);
        getHREVENTDATA = pphrEventDataService.getHREventData(1);
        BaseClass.logger.info("Validating response information");
        BaseClass.responseTimeValidation(getHREVENTDATA);
        BaseClass.assertEquals(getHREVENTDATA.getStatusCode(), 200);
        validations.verifyRequestIdAndPracticeIdOnEventDataResponseBody(getHREVENTDATA, requestId, event, practiceId);
    }
}
