// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.model;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.intuit.ifs.csscat.core.BaseTestNG;
import com.medfusion.common.utils.PropertyFileLoader;
import com.pxp.apipath.ApiPath;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseRest extends BaseTestNG {
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	public JsonPath jsonPath;
	public PXPInit PXPInit;
	public PropertyFileLoader testData;

	@BeforeClass(alwaysRun = true)
	public void setUpTestData() throws Exception {
		testData = new PropertyFileLoader();
		log("Setting up the Test Data.");
		PXPInit = new PXPInit(testData);
		PXPInit.init();
		BasicConfigurator.configure();
	}

	@BeforeMethod(enabled = true)
	public void getMethodName(ITestResult result) throws IOException {
		log("Method Name-- " + result.getMethod().getMethodName());
	}

/*	public String getTokenValue() throws IOException {
		RestAssured.baseURI = PXPInit.getAuthBaseUrl();
		Response response = given().auth().preemptive()
				.basic(PXPInit.getCommandCenterUser(), PXPInit.getCommandCenterPassword()).when()
				.post(ApiPath.POST_SIGNIN).then().log().all().extract().response();
		String token = response.jsonPath().get("token").toString();
		return token;
	}*/


	public void setupServiceRequestSpecificationBuilder() throws IOException {
		RestAssured.baseURI = testData.getProperty("base.uri");
		requestSpec = new RequestSpecBuilder().setContentType(ApiPath.CONTENT_TYPE)
				.addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();
	}


}
