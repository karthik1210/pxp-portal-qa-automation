package com.pxp.setup;

import com.pxp.model.BaseClass;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyFileLoader {

    Properties property = new Properties();

    public PropertyFileLoader() throws IOException {
        String env = BaseClass.getEnvironmentType().toString();
        String propertyFileNameString = env + ".properties";
        URL url = ClassLoader.getSystemResource("data-driven/" + propertyFileNameString);
        FileReader inputStream = new FileReader(url.getFile());
        this.property.load(inputStream);
    }

    public String getProperty(String prop) throws NullPointerException {
        if (this.property.getProperty(prop) == null) {
            throw new NullPointerException("Property " + prop + " not found in the property file.");
        } else {
            return this.property.getProperty(prop);
        }
    }
}
