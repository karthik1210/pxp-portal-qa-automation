// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.helpers;


import com.pxp.apipath.ApiPath;
import io.restassured.response.Response;

import static com.pxp.model.BaseRest.requestSpec;
import static io.restassured.RestAssured.given;

public class PPNGEIntegrationService {

    public Response getNgeIntegration(int practiceId, String startTime, String endTime) {
        String url = ApiPath.GET_NGE_INTETGRATION(practiceId);
        return given().spec(requestSpec).queryParams("fetchStartTime",startTime).queryParam("fetchEndTime",endTime).when().get(url).then().extract().response();
    }

    public Response getNgeIntegrationWithInvalidURI(int practiceId, String startTime, String endTime) {
        String url = ApiPath.GET_NGE_INTETGRATION_INVALID_URI(practiceId);
        return given().spec(requestSpec).queryParams("fetchStartTime",startTime).queryParam("fetchEndTime",endTime).when().get(url).then().extract().response();
    }

    public Response getNgeIntegrationWithInvalidMethod(int practiceId, String startTime, String endTime) {
        String url = ApiPath.GET_NGE_INTETGRATION(practiceId);
        return given().spec(requestSpec).queryParams("fetchStartTime",startTime).queryParam("fetchEndTime",endTime).when().patch(url).then().extract().response();
    }

    public Response getNgeIntegration(String practiceId, String startTime, String endTime) {
        String url = ApiPath.GET_NGE_INTETGRATION(practiceId);
        return given().spec(requestSpec).queryParams("fetchStartTime",startTime).queryParam("fetchEndTime",endTime).when().get(url).then().extract().response();
    }
}
