// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.model;

import com.medfusion.common.utils.PropertyFileLoader;

import lombok.Data;

@Data
public class PxpInit {

	private PropertyFileLoader properties;
	public String url;
	public String username;
	public String password;

	public PxpInit(PropertyFileLoader property) {
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
