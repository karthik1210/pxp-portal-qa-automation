// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.helpers;

import com.pxp.apipath.ApiPath;
import io.restassured.response.Response;

import static com.pxp.model.BaseRest.requestSpec;
import static io.restassured.RestAssured.given;

public class PP_HR_EVENT_DATA_SERVICE {

    public Response getHREventData(int realTime) {
        String url = ApiPath.GET_HR_EVENTDATA();
        return given().spec(requestSpec).queryParam("realTime", realTime).when().get(url).then().extract().response();
    }
}
