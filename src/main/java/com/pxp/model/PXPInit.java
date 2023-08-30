// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.model;

import com.medfusion.common.utils.PropertyFileLoader;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class PXPInit {

	private PropertyFileLoader properties;
	public String url;
	public String username;
	public String password;

	public PXPInit(PropertyFileLoader property) {
		properties = property;
	}

	public String getProperty(String prop) throws Exception {
		if (properties.getProperty(prop) == null)
			throw new Exception("Property " + prop + " not found in the property file.");
		return properties.getProperty(prop);
	}

	public void init() throws Exception {
		/*setUrl(getProperty("base.url"));
		setUsername(getProperty("user.name"));
		setPassword(getProperty("user.password"));*/

	}
}
