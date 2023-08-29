// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.testcases.ui;

import com.intuit.ifs.csscat.core.BaseTestNGWebDriver;
import com.medfusion.common.utils.PropertyFileLoader;
import com.pxp.model.PXPInit;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.BeforeClass;

public class GenericTest extends BaseTestNGWebDriver {

	protected PropertyFileLoader testData;
	protected PXPInit pxpInit;

	@BeforeClass(alwaysRun = true)
	public void setUpTestData() throws Exception {
		testData = new PropertyFileLoader();
		pxpInit = new PXPInit(testData);
		pxpInit.init();
		BasicConfigurator.configure();
	}
}
