// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.util;

import com.pxp.model.BaseClass;
import com.pxp.model.BaseRest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static org.testng.internal.Utils.log;

public class Validations extends BaseClass {

    public void verifyRequestIdAndPracticeIdOnEventDataResponseBody(Response response, String requestId, String event, String practiceId) throws IOException {
        JsonPath jsonpath = new JsonPath(response.getBody().asString());
        BaseClass.assertNotNull(jsonpath, "Response is null");
        BaseClass.assertNotNull(jsonpath.get("eventRequestId"), "Request id is not in the response");
        BaseClass.assertNotNull(jsonpath.get("portalPracticeId"), "Client name is not in the response");
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        JSONObject jsonObject;
        int flag = 0;
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.get("eventRequestId").equals(requestId)) {
                    if(event.contains("DEACTIVATED"))
                        new AssertionError();
                    log(jsonObject.get("portalPracticeId").toString());
                    log(practiceId);
                    BaseClass.assertEquals(jsonObject.get("portalPracticeId"), practiceId);
                    log("Event Request Id on Response : " + jsonObject.get("eventRequestId") + " contains given request id : " + requestId);
                    log("Portal Practice Id on Response : " + jsonObject.get("portalPracticeId") + " contains given practice id : " + practiceId);
                    flag = 1;
                }
            }

            if (flag == 0 && (event.equalsIgnoreCase("LAB_SOLUTION_ACTIVATED_EVENT")))
                throw new AssertionError();

            if (flag == 0 && event.equalsIgnoreCase("LAB_SOLUTION_DEACTIVATED_EVENT")) {
                log("Given Request Id and practice Id not available in the API Response");
                log("For LAB DEACTIVATION EVENT, Data will not be fectched in API Response, It is working as expected");
            }
        }
    }


}
