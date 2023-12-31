// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.model;

import static io.restassured.RestAssured.given;
import static org.testng.internal.Utils.log;

import java.io.IOException;

import com.pxp.setup.PropertyFileLoader;
import org.apache.log4j.BasicConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.pxp.apipath.ApiPath;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseRest {
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	public JsonPath jsonPath;
	public com.pxp.model.PXPInit PXPInit;
	public PropertyFileLoader testData;

	@BeforeMethod(enabled = true)
	public void getMethodName(ITestResult result) throws IOException {
		log("Method Name-- " + result.getMethod().getMethodName());
	}

	public BaseRest() throws Exception {
		testData = new PropertyFileLoader();
		log("Setting up the Test Data.");
		PXPInit = new PXPInit(testData);
		PXPInit.init();
		BasicConfigurator.configure();
	}

	public void setupServiceRequestSpecificationBuilder() throws IOException {
		RestAssured.baseURI = testData.getProperty("base.uri");
		requestSpec = new RequestSpecBuilder().setContentType(ApiPath.CONTENT_TYPE)
				.addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();
	}
}
