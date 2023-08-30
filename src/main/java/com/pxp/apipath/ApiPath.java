// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.apipath;

public class ApiPath {

    public static final String CONTENT_TYPE = "application/json";
    public static final String SERVICE_HR_EVENTDATA = "/service/hr-event-data";
    public static final String SERVICE_NGE_INTEGRATION = "/service/nge-integration";

    public static final String GET_HR_EVENTDATA() {
        return SERVICE_HR_EVENTDATA + "/v1/healthrecord/event/";
    }

    public static final String GET_NGE_INTETGRATION(int practiceId) {
        return SERVICE_NGE_INTEGRATION + "/v1/ngeintegration/lab/orders/test-results/practice/" + practiceId;
    }

    public static final String GET_NGE_INTETGRATION_INVALID_URI(int practiceId) {
        return SERVICE_NGE_INTEGRATION + "/v1/ngeintegration/lab/orders/test-resultsssss/practice/" + practiceId;
    }

    public static final String GET_NGE_INTETGRATION(String practiceId) {
        return SERVICE_NGE_INTEGRATION + "/v1/ngeintegration/lab/orders/test-results/practice/" + practiceId;
    }
}
