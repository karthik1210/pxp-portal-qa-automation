package com.pxp.util;

import com.pxp.setup.PropertyFileLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class OptionManager {

    PropertyFileLoader testData;

    public OptionManager(PropertyFileLoader testData) {
        this.testData = testData;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        if (testData.getProperty("headless").equals("true"))
            options.addArguments("headless");
        if (testData.getProperty("incognito").equals("true"))
            options.addArguments("Incognito");
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    public FirefoxOptions getFirefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (testData.getProperty("headless").equals("true"))
            options.addArguments("headless");
        if (testData.getProperty("incognito").equals("true"))
            options.addArguments("-private");
        return options;
    }

    public EdgeOptions getEdgeOptions() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        return options;
    }
}
