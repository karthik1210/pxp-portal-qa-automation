// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.testcases.ui;

import org.openqa.selenium.WebDriver;

import com.medfusion.common.utils.PropertyFileLoader;

public class PpUi extends GenericTest {

	public static PropertyFileLoader testData;
	public static WebDriver driver;
	public static PpUi PPUI;

	/////// Constructor
	public PpUi(PropertyFileLoader testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
	}

	///////////// Variables Page Objects

	/////////// Variables Test Data


	public PpUi getPpui() throws InterruptedException {
		if (PPUI == null) {
			PPUI = new PpUi(testData, driver);
		}
		return PPUI;
	}
}
