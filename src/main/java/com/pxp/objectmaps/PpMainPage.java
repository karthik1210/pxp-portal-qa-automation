// Copyright 2023-2024 NXGN Management, LLC. All Rights Reserved.

package com.pxp.objectmaps;

import com.intuit.ifs.csscat.core.pageobject.BasePageObject;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
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

public class PpMainPage extends BasePageObject {

	private static String numericString =  "0123456789";

	public PpMainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public PpMainPage(WebDriver driver, String url) {
		super(driver);
		if (url != null) {
			String sanitizedUrl = url.trim();
			driver.get(sanitizedUrl);
		}
		driver.manage().window().maximize();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PageFactory.initElements(driver, this);
	}

/*	public void highlightElement(WebElement element) {
		jse.executeScript("arguments[0].setAttribute('style','border: solid 2px green');", element);
	}*/

	public String elementToString(WebElement element) {
		return "Element (id: " + element.getAttribute("id") + ", tag: " + element.getTagName() + ")";
	}

	public String fetchWebElementColor(WebElement element) {
		String value = element.getCssValue("color");
		return value;
	}

	public List<WebElement> getDropdownListItems(By testData) {
		return new Select(driver.findElement(testData)).getOptions();
	}

	public void navigateBackToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	public org.openqa.selenium.WebElement getRootElement(org.openqa.selenium.WebElement element) {
		return (org.openqa.selenium.WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
	}

	public String initCap(String testData) {
		return testData.substring(0, testData.length() - (testData.length() - 1)).toUpperCase()
				+ testData.substring(1).toLowerCase();
	}


	public void setTextFieldValueIfNull(org.openqa.selenium.WebElement element, String testData)
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


	public void waitFor(int waitMilis) {
		try {
			Thread.sleep(waitMilis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void enterValue(org.openqa.selenium.WebElement element, int timeoutInSecond, String testData) {
		try {
			waitUntilElementIsClickable(element, timeoutInSecond);
			highlightElement(element);
			element.sendKeys(testData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void waitClearAndSendKeys(org.openqa.selenium.WebElement element, int timeoutInSecond, String testData) {
		waitUntilElementIsClickable(element, timeoutInSecond);
		element.clear();
		element.sendKeys(testData);
	}

	public void highlightElementBackground(org.openqa.selenium.WebElement element, String flag) {
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

	public void swithcFrameBasedOnId(String frameIDname) {
		logger.info("getting inside frame..........................>>>");
		driver.switchTo().frame(frameIDname);
	}

	public void exitFromFrame(WebDriver driver) {
		logger.info("exiting from frame..........................>>>");
		driver.switchTo().defaultContent();

	}

	public void ClickButtoninsideFrame(org.openqa.selenium.WebElement element, int frameInx) {
		// this.logger.debug("Navigating to My Providers");
		driver.switchTo().frame(frameInx);
		waitFor(1000);
		element.click();
		// driver.switchTo().defaultContent();

	}

	public void ClickButtoninsideFrameWithExit(org.openqa.selenium.WebElement element, int frameInx) {
		// this.logger.debug("Navigating to My Providers");
		driver.switchTo().frame(frameInx);
		waitFor(1000);
		element.click();
		driver.switchTo().defaultContent();

	}

	public void varifyText(String text, org.openqa.selenium.WebElement element) {

		if (element.getText().contains(text)) {
			element.isDisplayed();
		} else {
			throw new RuntimeException("Text Don't Match");
		}

	}

	public String getText(org.openqa.selenium.WebElement element) {
		//highlightElementBackground(element, "pass");
		String getElement = null;
		try {
			getElement = element.getText();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getElement;
	}

	public void clickWithStrElement(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToElement(org.openqa.selenium.WebElement webElement) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded()", webElement);
		Thread.sleep(500);
	}

	public void clickFromElements(List<org.openqa.selenium.WebElement> relements, String text) {

		for (int i = 0; i < relements.size(); i++) {

			String x = relements.get(i).getText();
			if (x.toLowerCase().contains(text.toLowerCase()) || x.contains(text)) {
				relements.get(i).click();
			}
		}

	}

	public void clickFromElementsWithEquleText(List<org.openqa.selenium.WebElement> relements, String text) {

		for (int i = 0; i < relements.size(); i++) {

			String x = relements.get(i).getText();
			if (x.toLowerCase().contentEquals(text.toLowerCase())) {
				relements.get(i).click();
			}
		}

	}

	public List<String> getAllText(List<org.openqa.selenium.WebElement> relements) {
		List<String> all_elements_text = new ArrayList<>();

		for (int i = 0; i < relements.size(); i++) {
			all_elements_text.add(relements.get(i).getText());
		}

		return all_elements_text;

	}

	public List<String> getAllAttributeText(List<org.openqa.selenium.WebElement> relements, String attbName) {

		List<String> all_elements_text = new ArrayList<>();

		for (int i = 0; i < relements.size(); i++) {
			all_elements_text.add(relements.get(i).getAttribute(attbName));
		}

		return all_elements_text;
	}

	public List<String> getSubStringFromText(List<String> strData, String openingStr, String closingStr) {

		List<String> all_elements_text = new ArrayList<>();
		for (int i = 0; i < strData.size(); i++) {
			String data = StringUtils.substringBetween(strData.get(i), openingStr, closingStr);
			all_elements_text.add(data);
		}

		return all_elements_text;

	}

	public String getSubStringFromText(String strData, String openingStr, String closingStr) {
		String data = null;
		try {
			data = StringUtils.substringBetween(strData, openingStr, closingStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void clickButton(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
		logger.info("Clicking Button");
		waitUntilElementIsClickable(element, timeoutInSeconds);

		highlightElement(element);
		element.click();
	}

	public void sendKeys(org.openqa.selenium.WebElement element, String data) {

		highlightElementBackground(element, "pass");
		element.sendKeys(data);
	}

	public void sendKeysWithTimeout(WebElement element, String data, int timeoutInSeconds) {
		waitUntilElementIsVisible(element, timeoutInSeconds);
		highlightElementBackground(element, "pass");
		element.sendKeys(data);
	}

	public static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public void selectFromDropDownByVal(org.openqa.selenium.WebElement element, int timeoutInSeconds, String testdata) {
		try {
			highlightElementBackground(element, "Pass");
			waitUntilElementIsClickable(element, timeoutInSeconds);
			Select option = new Select(element);
			option.selectByValue(testdata);
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void selectFromDropDownByIndex(org.openqa.selenium.WebElement element, int timeoutInSeconds, int index) {
		try {
			highlightElementBackground(element, "Pass");
			waitUntilElementIsClickable(element, timeoutInSeconds);
			Select option = new Select(element);
			option.selectByIndex(index);
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void selectFromDropDownByText(org.openqa.selenium.WebElement element, int timeoutInSeconds, String testdata) {
		try {
			highlightElement(element);
			waitUntilElementIsClickable(element, timeoutInSeconds);
			Select option = new Select(element);
			option.selectByVisibleText(testdata);
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void highlightElement(WebElement ele) {
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

	public String waitNGetText(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
		try {
			highlightElementBackground(element, "pass");
			waitUntilElementIsClickable(element, timeoutInSeconds);
			String getElement = element.getText();
			return getElement;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void click(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
		waitUntilElementIsClickable(element, timeoutInSeconds);
		highlightElement(element);
		element.click();
	}

	public void sendKeys(org.openqa.selenium.WebElement element, Keys keys) {
		try {
			waitFor(2000);
			element.sendKeys(keys);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public boolean isElePresent(By testData) {
		boolean flag = false;
		try {
			flag = driver.findElements(testData).size() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public boolean isCssVisible(By testData) {
		boolean flag = false;
		try {
			String elementStyle = driver.findElement(testData).getAttribute("style");
			flag = !(elementStyle.contentEquals("display: none;") || elementStyle.contentEquals("visibility: hidden"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public org.openqa.selenium.WebElement eligibilityOrAvality(By para1, By para2) {
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


	public boolean retryingFindClick(By by) {
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

	public String getWebSiteUrl(WebDriver driver) {

		String url = driver.getCurrentUrl();
		return url;

	}

	public void clickFromListOfElement(List<org.openqa.selenium.WebElement> elements) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		elements.forEach(e -> {
			js.executeScript("arguments[0].click();", e);
		});
	}

	public void javaScriptExecutorClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

	public org.openqa.selenium.WebElement getElementBasedOnText(org.openqa.selenium.WebElement element, String text) {
		return element.findElement(By.xpath("//span[contains(string(), " + text + ")]"));
	}


	public String waitAndgetValue(org.openqa.selenium.WebElement element, int timeoutInSeconds) {
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

	public String waitAndgetValue(org.openqa.selenium.WebElement element, String attribute, int timeoutInSeconds) {
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

	public void clickWithActionCls(org.openqa.selenium.WebElement element) {

		Actions builder = new Actions(driver);
		builder.moveToElement(element).pause(2000).click(element);
		builder.perform();
	}

	public void clickWithActionClsWithTime(org.openqa.selenium.WebElement element, int timeoutInSeconds) {

		waitUntilElementIsClickable(element, timeoutInSeconds);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).pause(5000).click(element);
		builder.perform();
	}

	public void clickWithActionCls(String element) {

		org.openqa.selenium.WebElement el = driver.findElement(By.xpath(element));
		Actions builder = new Actions(driver);
		builder.moveToElement(el).click(el);
		builder.perform();
	}

	public ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
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

	public void switchToWindow(WebDriver driver) {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}


	public void moveToElement(String element) {

		org.openqa.selenium.WebElement el = driver.findElement(By.xpath(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(el).build().perform();

	}


	public void waitForElementVisible(WebElement element, long timeoutInSeconds) {
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

	public void waitUntilElementIsClickable(WebElement element, long timeoutInSeconds) {
		Duration waitTime = Duration.ofSeconds(timeoutInSeconds);
		new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilTextIsVisible(WebElement element, String testData, long timeoutInSeconds) {
		Duration timeout = Duration.ofSeconds(timeoutInSeconds);
		new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.textToBePresentInElement(element, testData));
	}

	public void waitUntilElementIsVisible(WebElement element, long timeoutInSeconds) {
		Duration timeout = Duration.ofSeconds(timeoutInSeconds);
		new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementIsVisible(String element, long timeoutInSecond) {
		try {
			new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitUntilElementIsVisibleLocated(By by, long timeoutInSecond) {
		try {
			new WebDriverWait(driver, timeoutInSecond).until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean explicitlyWaitForElementText(WebElement ele, String text, long timeoutInSecond) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
			return wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
		} catch (Exception e) {
			System.out.println("Failed to Get the Expected Text After waiting " + timeoutInSecond + "Second...");
			e.printStackTrace();
			return false;
		}
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void launchUrl(String url) {
		driver.get(url);
	}

	public void verifyElementPresence(WebElement element) {
		element.isDisplayed();
	}

	public static void assertEquals(Object actual, Object expected) throws RuntimeException {
		try {
			Assert.isTrue(actual.toString().equalsIgnoreCase(expected.toString()), "Actual and Excepted are same");
			logger.info(
					"Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - PASSED");
		} catch (Exception e) {
			logger.info(
					"Expected value: " + expected.toString() + " Actual value: " + actual.toString() + " - FAILED");
			throw new RuntimeException(e);
		}
	}

	public static void assertNotNull(Object object, String message) throws RuntimeException {
		try {
			Assert.notNull(object, message);
			logger.info("Object Value is : {} - PASSED," + object.toString());
		} catch (Exception e) {
			logger.log(Level.INFO, message);
			throw new RuntimeException(e);
		}
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
}
