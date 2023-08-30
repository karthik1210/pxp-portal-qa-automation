// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.util;

import com.pxp.model.BaseRest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Validations extends BaseRest {

    public void verifyRequestIdAndPracticeIdOnEventDataResponseBody(Response response, String requestId, String event, String practiceId) throws IOException {
        JsonPath jsonpath = new JsonPath(response.getBody().asString());
        PpUtils.assertNotNull(jsonpath, "Response is null");
        PpUtils.assertNotNull(jsonpath.get("eventRequestId"), "Request id is not in the response");
        PpUtils.assertNotNull(jsonpath.get("portalPracticeId"), "Client name is not in the response");
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        JSONObject jsonObject;
        int flag = 0;
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.get("eventRequestId").equals(requestId)) {
                    if(event.contains("DEACTIVATED"))
                        new AssertionError();
                    logStep(jsonObject.get("portalPracticeId").toString());
                    logStep(practiceId);
                    PpUtils.assertEquals(jsonObject.get("portalPracticeId"), practiceId);
                    logStep("Event Request Id on Response : " + jsonObject.get("eventRequestId") + " contains given request id : " + requestId);
                    logStep("Portal Practice Id on Response : " + jsonObject.get("portalPracticeId") + " contains given practice id : " + practiceId);
                    flag = 1;
                }
            }

            if (flag == 0 && (event.equalsIgnoreCase("LAB_SOLUTION_ACTIVATED_EVENT")))
                throw new AssertionError();

            if (flag == 0 && event.equalsIgnoreCase("LAB_SOLUTION_DEACTIVATED_EVENT")) {
                logStep("Given Request Id and practice Id not available in the API Response");
                logStep("For LAB DEACTIVATION EVENT, Data will not be fectched in API Response, It is working as expected");
            }
        }
    }


}
