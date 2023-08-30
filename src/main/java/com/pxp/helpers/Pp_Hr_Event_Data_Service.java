// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.helpers;

import com.pxp.apipath.ApiPath;
import io.restassured.response.Response;

import static com.pxp.model.BaseRest.requestSpec;
import static io.restassured.RestAssured.given;

public class Pp_Hr_Event_Data_Service {

    public Response getHREventData(int realTime) {
        String url = ApiPath.GET_HR_EVENTDATA();
        return given().spec(requestSpec).queryParam("realTime", realTime).when().get(url).then().extract().response();
    }
}
