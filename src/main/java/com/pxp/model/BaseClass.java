package com.pxp.model;

import com.intuit.ifs.csscat.core.utils.Log4jUtil;
import com.intuit.ifs.csscat.core.wait.WaitForWEIsDisplayed;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.asserts.SoftAssert;

import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseClass {

    protected static WebDriver driver;

    public static int repeat = 0;
    private static final String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$&@?<>~!%#";
    private static final SecureRandom random = new SecureRandom();
    private static String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
    private static String alphaNumericStringIncludingSpecialCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"
            + "abcdefghijklmnopqrstuvxyz" + ".,!@#$%&*_-";
    private static String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
    public static SoftAssert softAssert = new SoftAssert();
    private static String numericString =  "0123456789";
    private static String alphaNumericStringWithoutNumbersAndSpecialCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";

    public static String elementToString(WebElement element) {
        return "Element (id: " + element.getAttribute("id") + ", tag: " + element.getTagName() + ")";
    }

    public static String fetchWebElementColor(WebElement element) {
        String value = element.getCssValue("color");
        return value;
    }

    public static List<WebElement> getDropdownListItems(By testData) {
        return new Select(driver.findElement(testData)).getOptions();
    }

    public static void navigateBackToPreviousPage(WebDriver driver) {
        driver.navigate().back();
    }

    public static org.openqa.selenium.WebElement getRootElement(org.openqa.selenium.WebElement element) {
        return (org.openqa.selenium.WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
    }

    public static String initCap(String testData) {
        return testData.substring(0, testData.length() - (testData.length() - 1)).toUpperCase()
                + testData.substring(1).toLowerCase();
    }


    public static void setTextFieldValueIfNull(org.openqa.selenium.WebElement element, String testData)
            throws HeadlessException, UnsupportedFlavorException, IOException, InterruptedException {
        try {
            StringSelection stringSelection = new StringSelection("");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            Thread.sleep(1000); // intended
            element.click();
            Thread.sleep(1000); // intended
            element.sendKeys(Keys.chord(Keys.CONTROL, "A"));
            element.sendKeys(Keys.chord(Keys.CONTROL, "C"));
            Thread.sleep(1000); // intended
            String clipData = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                    .getData(DataFlavor.stringFlavor);
            clipData = clipData.trim();
            if (clipData == null || clipData.equals("")) {
                Thread.sleep(1000); // intended
                element.sendKeys(testData);
            }
        } catch (HeadlessException | InterruptedException | UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void waitFor(int waitMilis) {
        try {
            Thread.sleep(waitMilis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void enterValue(org.openqa.selenium.WebElement element, int timeoutInSecond, String testData) {
        try {
            waitUntilElementIsClickable(element, timeoutInSecond);
            highlightElement(element);
            element.sendKeys(testData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitClearAndSendKeys(org.openqa.selenium.WebElement element, int timeoutInSecond, String testData) {
        waitUntilElementIsClickable(element, timeoutInSecond);
        element.clear();
        element.sendKeys(testData);
    }

    public static void highlightElementBackground(org.openqa.selenium.WebElement element, String flag) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (flag.equalsIgnoreCase("pass")) {
            // js.executeScript("arguments[0].style.border='1px groove green'", element);
            js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
        } else {
            // js.executeScript("arguments[0].style.border='4px solid red'", element);
            js.executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", element);
        }

        waitFor(2000);
    }

    public static void swithcFrameBasedOnId(String frameIDname) {
        logger.info("getting inside frame..........................>>>");
        driver.switchTo().frame(frameIDname);
    }

    public static void exitFromFrame(WebDriver driver) {
        logger.info("exiting from frame..........................>>>");
        driver.switchTo().defaultContent();

    }

    public static void ClickButtoninsideFrame(org.openqa.selenium.WebElement element, int frameInx) {
        // this.logger.debug("Navigating to My Providers");
        driver.switchTo().frame(frameInx);
        waitFor(1000);
        element.click();
        // driver.switchTo().defaultContent();

    }

    public static void ClickButtoninsideFrameWithExit(org.openqa.selenium.WebElement element, int frameInx) {
        // this.logger.debug("Navigating to My Providers");
        driver.switchTo().frame(frameInx);
        waitFor(1000);
        element.click();
        driver.switchTo().defaultContent();

    }

    public static void varifyText(String text, org.openqa.selenium.WebElement element) {

        if (element.getText().contains(text)) {
            element.isDisplayed();
        } else {
            throw new RuntimeException("Text Don't Match");
        }

    }

    public static String getText(org.openqa.selenium.WebElement element) {
        //highlightElementBackground(element, "pass");
        String getElement = null;
        try {
            getElement = element.getText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getElement;
    }

    public static void clickWithStrElement(String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scrollToElement(org.openqa.selenium.WebElement webElement) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded()", webElement);
        Thread.sleep(500);
    }

    public static void clickFromElements(List<org.openqa.selenium.WebElement> relements, String text) {

        for (int i = 0; i < relements.size(); i++) {

            String x = relements.get(i).getText();
            if (x.toLowerCase().contains(text.toLowerCase()) || x.contains(text)) {
                relements.get(i).click();
            }
        }

    }

    public static void clickFromElementsWithEquleText(List<org.openqa.selenium.WebElement> relements, String text) {

        for (int i = 0; i < relements.size(); i++) {

            String x = relements.get(i).getText();
            if (x.toLowerCase().contentEquals(text.toLowerCase())) {
                relements.get(i).click();
            }
        }

    }

    public static List<String> getAllText(List<org.openqa.selenium.WebElement> relements) {
        List<String> all_elements_text = new ArrayList<>();

        for (int i = 0; i < relements.size(); i++) {
            all_elements_text.add(relements.get(i).getText());
        }

        return all_elements_text;

    }

    public static List<String> getAllAttributeText(List<org.openqa.selenium.WebElement> relements, String attbName) {

        List<String> all_elements_text = new ArrayList<>();

        for (int i = 0; i < relements.size(); i++) {
            all_elements_text.add(relements.get(i).getAttribute(attbName));
        }

        return all_elements_text;
    }

    public static List<String> getSubStringFromText(List<String> strData, String openingStr, String closingStr) {

        List<String> all_elements_text = new ArrayList<>();
        for (int i = 0; i < strData.size(); i++) {
            String data = StringUtils.substringBetween(strData.get(i), openingStr, closingStr);
            all_elements_text.add(data);
        }

        return all_elements_text;

    }

    public static String getSubStringFromText(String strData, String openingStr, String closingStr) {
        String data = null;
        try {
            data = StringUtils.substringBetween(strData, openingStr, closingStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void clickButton(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
        logger.info("Clicking Button");
        waitUntilElementIsClickable(element, timeoutInSeconds);

        highlightElement(element);
        element.click();
    }

    public static void sendKeys(org.openqa.selenium.WebElement element, String data) {

        highlightElementBackground(element, "pass");
        element.sendKeys(data);
    }

    public static void sendKeysWithTimeout(WebElement element, String data, int timeoutInSeconds) {
        waitUntilElementIsVisible(element, timeoutInSeconds);
        highlightElementBackground(element, "pass");
        element.sendKeys(data);
    }

    public static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void selectFromDropDownByVal(org.openqa.selenium.WebElement element, int timeoutInSeconds, String testdata) {
        try {
            highlightElementBackground(element, "Pass");
            waitUntilElementIsClickable(element, timeoutInSeconds);
            Select option = new Select(element);
            option.selectByValue(testdata);
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }

    public static void selectFromDropDownByIndex(org.openqa.selenium.WebElement element, int timeoutInSeconds, int index) {
        try {
            highlightElementBackground(element, "Pass");
            waitUntilElementIsClickable(element, timeoutInSeconds);
            Select option = new Select(element);
            option.selectByIndex(index);
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }

    public static void selectFromDropDownByText(org.openqa.selenium.WebElement element, int timeoutInSeconds, String testdata) {
        try {
            highlightElement(element);
            waitUntilElementIsClickable(element, timeoutInSeconds);
            Select option = new Select(element);
            option.selectByVisibleText(testdata);
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }

    public static void highlightElement(WebElement ele) {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        WebElement element = ele;
        String highlightJavascript = "arguments[0].style.cssText = \"border-width: 3px; border-style: solid; border-color: red\";";
        jsDriver.executeScript("arguments[0].scrollIntoView(false);", element);
        jsDriver.executeScript(highlightJavascript, new Object[]{element});
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String waitNGetText(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
        try {
            highlightElementBackground(element, "pass");
            waitUntilElementIsClickable(element, timeoutInSeconds);
            String getElement = element.getText();
            return getElement;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void click(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
        waitUntilElementIsClickable(element, timeoutInSeconds);
        highlightElement(element);
        element.click();
    }

    public static void sendKeys(org.openqa.selenium.WebElement element, Keys keys) {
        try {
            waitFor(2000);
            element.sendKeys(keys);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean isElePresent(By testData) {
        boolean flag = false;
        try {
            flag = driver.findElements(testData).size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static boolean isCssVisible(By testData) {
        boolean flag = false;
        try {
            String elementStyle = driver.findElement(testData).getAttribute("style");
            flag = !(elementStyle.contentEquals("display: none;") || elementStyle.contentEquals("visibility: hidden"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static org.openqa.selenium.WebElement eligibilityOrAvality(By para1, By para2) {
        org.openqa.selenium.WebElement element = null;
        org.openqa.selenium.WebElement element1;
        org.openqa.selenium.WebElement element2;

        do {
            try {
                element1 = driver.findElement(para1);
            } catch (Exception e) {
                element1 = null;
            }
            try {
                element2 = driver.findElement(para2);
            } catch (Exception e) {
                element2 = null;
            }
            if (element1 != null) {
                element = element1;
            } else if (element2 != null) {
                element = element2;
            }
        } while (element == null);
        return element;
    }


    public static boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 3) {
            try {
                driver.findElement(by).click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static String getWebSiteUrl(WebDriver driver) {

        String url = driver.getCurrentUrl();
        return url;

    }

    public static void clickFromListOfElement(List<org.openqa.selenium.WebElement> elements) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        elements.forEach(e -> {
            js.executeScript("arguments[0].click();", e);
        });
    }

    public static void javaScriptExecutorClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public static org.openqa.selenium.WebElement getElementBasedOnText(org.openqa.selenium.WebElement element, String text) {
        return element.findElement(By.xpath("//span[contains(string(), " + text + ")]"));
    }


    public static String waitAndgetValue(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
        String value = null;
        try {
            waitUntilElementIsClickable(element, timeoutInSeconds);
            value = element.getAttribute("value");
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String waitAndgetValue(org.openqa.selenium.WebElement element, String attribute, int timeoutInSeconds) {
        String value = null;
        try {
            waitUntilElementIsClickable(element, timeoutInSeconds);
            value = element.getAttribute(attribute);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void clickWithActionCls(org.openqa.selenium.WebElement element) {

        Actions builder = new Actions(driver);
        builder.moveToElement(element).pause(2000).click(element);
        builder.perform();
    }

    public static void clickWithActionClsWithTime(org.openqa.selenium.WebElement element, int timeoutInSeconds) {

        waitUntilElementIsClickable(element, timeoutInSeconds);
        Actions builder = new Actions(driver);
        builder.moveToElement(element).pause(5000).click(element);
        builder.perform();
    }

    public static void clickWithActionCls(String element) {

        org.openqa.selenium.WebElement el = driver.findElement(By.xpath(element));
        Actions builder = new Actions(driver);
        builder.moveToElement(el).click(el);
        builder.perform();
    }

    public static ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);

            }
        }
        return result;
    }

    public static void switchToWindow(WebDriver driver) {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }


    public static void moveToElement(String element) {

        org.openqa.selenium.WebElement el = driver.findElement(By.xpath(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(el).build().perform();

    }


    public static void waitForElementVisible(WebElement element, long timeoutInSeconds) {
        Duration timeout = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Element with ID: " + element.getAttribute("id") + " not found within " + timeoutInSeconds + " seconds");
            throw new AssertionError(e);
            //Assert.fail("Element with ID: " + element.getAttribute("id") + " not found within " + timeoutInSeconds + " seconds");
        }
    }

    public static void waitUntilElementIsClickable(WebElement element, long timeoutInSeconds) {
        Duration waitTime = Duration.ofSeconds(timeoutInSeconds);
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilTextIsVisible(WebElement element, String testData, long timeoutInSeconds) {
        Duration timeout = Duration.ofSeconds(timeoutInSeconds);
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.textToBePresentInElement(element, testData));
    }

    public static void waitUntilElementIsVisible(WebElement element, long timeoutInSeconds) {
        Duration timeout = Duration.ofSeconds(timeoutInSeconds);
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsVisible(String element, long timeoutInSecond) {
        try {
            new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementIsVisibleLocated(By by, long timeoutInSecond) {
        try {
            new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean explicitlyWaitForElementText(WebElement ele, String text, long timeoutInSecond) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
            return wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
        } catch (Exception e) {
            System.out.println("Failed to Get the Expected Text After waiting " + timeoutInSecond + "Second...");
            e.printStackTrace();
            return false;
        }
    }

    public static void switchToFrame(WebElement element) {
        driver.switchTo().frame(element);
    }

    public static void launchUrl(String url) {
        driver.get(url);
    }

    public static void verifyElementPresence(WebElement element) {
        element.isDisplayed();
    }

    public static void responseTimeValidation(Response response) {
        long time = response.time();
        logger.info("Response time " + time);
        ValidatableResponse valRes = response.then();
        valRes.time(Matchers.lessThan(21000L));
        logger.info("Response Time in ms- " + time);
    }

    public static String generateGUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomNumber(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (numericString.length() * Math.random());
            sb.append(numericString.charAt(index));
        }
        logger.info("Initial Practice Id : " + sb.toString());
        String s = sb.toString().replaceFirst("0", "1");
        return s;
    }

    public static void verifyAndWaitForElement(WebElement we, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new WaitForWEIsDisplayed(we));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void verifyElementToBeClickable(WebElement we, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOf(we));
            wait.until(ExpectedConditions.elementToBeClickable(we));
        } catch (Exception exception) {
            verifyAndWaitForElement(we, driver);
        }
    }

    public static void waitForElementPresence(WebElement we, WebDriver driver) throws InterruptedException {
        try {
            Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(we));
            wait.until(ExpectedConditions.stalenessOf(we));
        } catch (Exception exception) {
            Thread.sleep(4000);
        }
    }

    public static void verifyAndWaitForElementPresence(WebElement we, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(we));
            wait.until(ExpectedConditions.stalenessOf(we));
        } catch (Exception exception) {
            verifyAndWaitForElement(we, driver);
        }
    }

    public static void verifyAndWaitForElementPresent(WebElement we, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(we));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(we)));
        } catch (Exception exception) {
            verifyAndWaitForElement(we, driver);
        }
    }

    public static void verifyAndWaitForElementPresenceForInterfaceSetup(String externalSystemName, WebElement we, WebDriver driver) {
        while (repeat <= 5) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+externalSystemName+"']/../../following-sibling::td//mat-icon[@mattooltip='More Options']"))));
                wait.until(ExpectedConditions.stalenessOf(we));
                wait.until(ExpectedConditions.visibilityOf(we));
                break;
            } catch (StaleElementReferenceException exception) {
                //verifyAndWaitForElementPresenceForInterfaceSetup(externalSystemName, we, driver);
            }
            repeat++;
        }
    }

    public static void verifyAndWaitForListOfElement(List<WebElement> web, WebDriver driver) {
        for (int i = 0; i < web.size(); i++) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(new WaitForWEIsDisplayed(web.get(i)));
            try {
                Thread.sleep(2300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void verifyAndWaitForTableList(WebElement we, WebDriver driver) {
        try {
            Log4jUtil.log("webdriverwait initiated");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='table']"))));
            Log4jUtil.log("webdriverwait Completed and tableClientTypeInSearchResult validation");
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(we)));
        } catch (StaleElementReferenceException e) {
            verifyAndWaitForTableList(we, driver);
        }
    }

    public static void verifyAndWaitForUserTableList(WebElement we, WebDriver driver) {
        try {
            Log4jUtil.log("webdriverwait initiated");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@role='grid']"))));
            Log4jUtil.log("webdriverwait Completed and tableClientTypeInSearchResult validation");
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(we)));
        } catch (StaleElementReferenceException e) {
            verifyAndWaitForUserTableList(we, driver);
        }
    }

    public static void assertEquals(Object actual, Object expected) throws RuntimeException {
        try {
            org.testng.Assert.assertEquals(actual, expected);
            Log4jUtil.log(
                    "Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - PASSED");
        } catch (Exception e) {
            Log4jUtil.log(
                    "Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - FAILED");
            throw new RuntimeException(e);
        }
    }

    public static void assertEqualsNull(Object actual, Object expected) throws RuntimeException {
        try {
            org.testng.Assert.assertEquals(actual, expected);
            Log4jUtil.log("Expected value: " + (expected == null ? null : expected.toString()) + " Actual value: "
                    + (actual == null ? null : actual.toString()) + " - PASSED");
        } catch (Exception e) {
            Log4jUtil.log("Expected value: " + (expected == null ? null : expected.toString()) + " Actual value: "
                    + (actual == null ? null : actual.toString()) + " - FAILED");
            throw new RuntimeException(e);
        }
    }

    public static void assertTrue(boolean condition, String message) throws RuntimeException {
        try {
            org.testng.Assert.assertTrue(condition);
            Log4jUtil.log("Expected value: true" + " Actual value: " + condition + " - PASSED", "\n" + message);
        } catch (Exception e) {
            Log4jUtil.log("Expected value: true" + " Actual value: " + condition + " - FAILED", "\n" + message);
            throw new RuntimeException(e);
        }
    }

    public static void assertNotNull(Object object, String message) throws RuntimeException {
        try {
            org.testng.Assert.assertNotNull(object, message);
            Log4jUtil.log("Object Value is : {} - PASSED," + object.toString());
        } catch (Exception e) {
            Log4jUtil.log("FAILED {}", message);
            throw new RuntimeException(e);
        }
    }

    public static void selectFromDropDown(WebElement webElement, String containsText) {
        Select select = new Select(webElement);
        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            if (option.getText().contains(containsText)) {
                select.selectByVisibleText(option.getText());
            }
        }
    }

    public static String generateRandomString() {

        while (true) {
            char[] newValue = new char[random.nextBoolean() ? 12 : 13];
            boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
            for (int i = 0; i < newValue.length; i++) {
                char ch = symbols.charAt(random.nextInt(symbols.length()));
                if (Character.isUpperCase(ch))
                    hasUpper = true;
                else if (Character.isLowerCase(ch))
                    hasLower = true;
                else if (Character.isDigit(ch))
                    hasDigit = true;
                else
                    hasSpecial = true;
                newValue[i] = ch;
            }
            if (hasUpper && hasLower && hasDigit && hasSpecial) {
                return new String(newValue);
            }
        }
    }

    public static String generateRandomStringWithoutSymbolsOrNumbers(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (alphaNumericStringWithoutNumbersAndSpecialCharacters.length() * Math.random());
            sb.append(alphaNumericStringWithoutNumbersAndSpecialCharacters.charAt(index));
        }
        return sb.toString();
    }

    public static void pressEnterKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Log4jUtil.log("Keyboard Action : Enter key ");
    }

    public static String generateAlphaNumericString(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static Long generateMobileNumber() {
        String mob = generateNumericString(10);
        mob = mob.toString().replace('0','9');
        return Long.parseLong(mob);
    }

    public static String generateNewEmail(int n) {
        String email = generateAlphaNumericString(n).concat("@nextgen.com");
        String[] emailList = email.split("@");
        int domainNameSize = emailList[1].length();
        if (emailList[0].length() <= 64 && domainNameSize <= 98) {
            if (email.length() <= 100) {
                return email;
            } else {
                int difference = email.length() - 100;
                return email.replaceAll(email.substring(0, difference), "").trim();
            }
        } else {
            int difference = emailList[0].length() - 64;
            String finalEmail = email.replaceAll(email.substring(0, difference), "").trim();
            if (finalEmail.length() <= 100) {
                return finalEmail;
            } else {
                int diff = finalEmail.length() - 100;
                return finalEmail.replaceAll(finalEmail.substring(0, diff), "").trim();
            }
        }
    }

    public static String generateAlphaNumericStringIncludingSpecialChar(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (alphaNumericStringIncludingSpecialCharacters.length() * Math.random());
            sb.append(alphaNumericStringIncludingSpecialCharacters.charAt(index));
        }
        return sb.toString();
    }

    public static void scrollPageToEnd(WebDriver driver)  {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
//        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    public static String verifyCharacterIsNumberOrDigit(String data) {
        for (int i = 0; i < data.length(); i++) {
            if (Character.isDigit(data.charAt(i))) {
                return "number";
            } else if (Character.isLetter(data.charAt(i))) {
                return "letter";
            } else if (Character.isLetter(data.charAt(i)) && Character.isDigit(data.charAt(i))) {
                return "numberletter";
            } else if (specialCharactersString.contains(Character.toString(data.charAt(i)))) {
                return "alphanumeric";
            }
        }
        return "InvalidData";
    }

    public static String dateformatter() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Log4jUtil.log(formatter.format(date));
        return formatter.format(date);
    }

    public static void clearTextboxField(WebElement element) {
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public static void pageRefresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void mouseHoverOverTheElement(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            action.moveToElement(element).pause(3000).build().perform();
        }
    }

    public static void assertNotEquals(Object actual, Object expected) throws RuntimeException {
        try {
            org.testng.Assert.assertNotEquals(actual, expected);
            Log4jUtil.log(
                    "Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - PASSED");
        } catch (Exception e) {
            Log4jUtil.log(
                    "Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - FAILED");
            throw new RuntimeException(e);
        }
    }

    public static String reverseCase(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }

    public static String generateStringWithSpace(String text) {
        String stringWithSpace = text.replaceAll("................................(?!$)", "$0 ");
        return stringWithSpace;
    }

    public static String getName(String name) {
        int size = name.trim().length();
        if (size <= 100) {
            return name.trim();
        } else {
            int difference = size - 100;
            return name.replaceAll(name.substring(size - difference), "").trim();
        }
    }

    public static String getSapNumber(String sapNumber) {
        int size = sapNumber.trim().length();
        if (size <= 50) {
            return sapNumber.trim();
        } else {
            int difference = size - 50;
            return sapNumber.replaceAll(sapNumber.substring(size - difference), "").trim();
        }
    }

    public static void performDoubleClick(WebElement element, WebDriver driver) {
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    public static void performMoveCursor(WebElement element, WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveByOffset(20, 150).click().build().perform();
    }

    public static void hover(WebElement element, WebDriver driver) throws AWTException {
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            Point coordinates = element.getLocation();
            int xcord = coordinates.getX();
            int ycord = coordinates.getY();
            Robot robot = new Robot();
            robot.mouseMove(xcord, ycord + 100);
            robot.setAutoDelay(2000);
            robot.mouseMove(xcord + 6, ycord + 135);
            robot.setAutoDelay(2000);
            robot.mouseMove(xcord + 6, ycord + 135);

        }
    }

    public static Long generateinvalidMobileNumber() {
        return (long) (Math.random() * 100000 + 33330000000L);
    }

    public static String generateAuthId() {

        String authId = UUID.randomUUID().toString();
        return authId;
    }

    public static void focusOnElement(WebElement element, WebDriver driver) {
        new Actions(driver).moveToElement(element).click().perform();
    }

    public static void mousehover(WebElement element, WebDriver driver) {
        new Actions(driver).moveToElement(element).build().perform();
    }

    public static void focusOnElementAndClick(WebElement element, WebDriver driver) {
        new Actions(driver).moveToElement(element).click().build().perform();
    }

    public String getDefaultItemsPerPageOfWebPage(WebElement ele, WebDriver driver) throws InterruptedException {
        BaseClass.verifyAndWaitForElement(ele, driver);
        return ele.getText().toString();
    }

    public static String getDefaultPaginationOfWebPage(WebElement ele, WebDriver driver) {
        String s1 = ele.getText().toString();
        String[] words = s1.split("of", 0);
        String data = null;
        for (String w : words) {
            data = w.trim();
            Log4jUtil.log("Print the value of default Pagination : " + w.trim());
            break;
        }
        return data.trim();
    }

    public static String getTotalNoOfClientsFromPaginationSection(WebElement ele, WebDriver driver) {
        String s1 = ele.getText().toString();
        String[] words = s1.split("of", 0);
        String data = null;
        for (String w : words) {
            data = w.trim();
            Log4jUtil.log("Print the value of default Pagination : " + w.trim());
        }
        return data.trim();
    }

    public String splitingPagination(WebElement ele, WebDriver driver) {
        BaseClass.verifyAndWaitForElement(ele, driver);
        String s1 = ele.getText().toString();
        String[] words = s1.split("\\s", 3);
        String data = null;
        for (String w : words) {
            data = w.trim();
        }
        Log4jUtil.log("Print the value of default Pagination : " + data.trim());
        return data.trim();
    }

    public String getNoOfRowsPagination(WebElement ele, WebDriver driver) {
        String data = splitingPagination(ele, driver);
        String noOfRowsPagination = data.substring(0, 2).trim();
        return noOfRowsPagination.trim();
    }

    public String getLHSPaginationOfWebPage(WebElement ele, WebDriver driver) throws AWTException {
        String data = getDefaultPaginationOfWebPage(ele, driver);
        String[] words = data.split("–", 2);
        for (String w : words) {
            data = w.trim();
            break;
        }
        Log4jUtil.log("Print the value of LHS Pagination : " + data.trim());
        return data.trim();
    }

    public static String getRHSPaginationOfWebPage(WebElement ele, WebDriver driver) throws AWTException {
        String data = getDefaultPaginationOfWebPage(ele, driver);
        String[] words = data.split("–", 0);
        for (String w : words) {
            data = w.trim();
        }
        Log4jUtil.log("Print the value of RHS Pagination : " + data.trim());
        return data.trim();
    }

    public static boolean isPaginationButtonDisabled(WebElement ele, WebDriver driver) {
        BaseClass.verifyAndWaitForElement(ele, driver);
        Log4jUtil.log("Verifying the visiblity of " + ele.getAttribute("aria-label") + " Button.");
        boolean buttonStatus = ele.isEnabled();
        Log4jUtil.log(
                "Print the value of " + ele.getAttribute("aria-label") + "Button visibility status : " + buttonStatus);
        return buttonStatus;
    }

    public static void scrollPageViaElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void verifyForElementToBeClickable(WebElement we, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(we));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void verifyAndWaitForPresenceOfListOfElement(List<WebElement> web, WebDriver driver) {
        for (int i = 0; i < web.size(); i++) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tbody[@role='rowgroup']//tr[@id='locationList']//td[1]")));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void scrollPageToStart() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }

    public static String generateNumericString(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (numericString.length() * Math.random());
            sb.append(numericString.charAt(index));
        }
        return sb.toString();
    }

    public static void enterValueOnTextField(WebElement element, String value) {
        element.sendKeys(value);
    }

}
