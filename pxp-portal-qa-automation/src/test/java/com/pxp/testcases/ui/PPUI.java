// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.testcases.ui;

import org.openqa.selenium.WebDriver;

import com.medfusion.common.utils.PropertyFileLoader;

public class PPUI extends GenericTest {

	public static PropertyFileLoader testData;
	public static WebDriver driver;
	public static PPUI PPUI;

	/////// Constructor
	public PPUI(PropertyFileLoader testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
	}

	///////////// Variables Page Objects

	/////////// Variables Test Data


	public PPUI getPpui() throws InterruptedException {
		if (PPUI == null) {
			PPUI = new PPUI(testData, driver);
		}
		return PPUI;
	}
}
