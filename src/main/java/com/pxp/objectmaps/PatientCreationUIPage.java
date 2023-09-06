//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.objectmaps;

import com.medfusion.common.utils.PropertyFileLoader;
import com.pxp.base.IntegrationDb;
import com.pxp.model.BaseClass;
import com.pxp.queries.PIDCQueries;
import com.pxp.util.PIDCInfo;
import com.pxp.util.PatientRandomDetails;
import com.pxp.util.TimeUtil;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.Month;
import java.util.List;

public class PatientCreationUIPage extends BaseClass {

    IntegrationDb integrationDb;
    PIDCInfo pidcInfo = new PIDCInfo();
    PIDCQueries dbQuerries = new PIDCQueries();
    static int count = 1;
    public static String subscriberFirstName = new String();
    public static String existingPersonForsubscriberFirstName = new String();
    public PropertyFileLoader testData;

    public PatientCreationUIPage() throws IOException {
        testData = new PropertyFileLoader();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[text()='Your appointment is coming up!'])[1]")
    private WebElement mailForPreCheck;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement login;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement mailinatorLogin;

    @FindBy(xpath = "//button[text()='GO']")
    private WebElement mailinatorGo;

    @FindBy(xpath = "//i[contains(text(),'')]")
    private WebElement selectEmail;

    @FindBy(xpath = "//a[text()='Start PreCheck']")
    private WebElement preCheck;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    private WebElement emailFrame;

    @FindBy(xpath = "//iframe[@name='ifinbox']")
    private WebElement inboxFrame;

    @FindBy(xpath = "//input[@id='zip']")
    private WebElement zipTextBoxOnPreCheck;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement enterOnPreCheck;

    @FindBy(xpath = "//div[@id='actionDropdown-form' and @class='actionDropdown-icon']")
    private WebElement formsDropdown;

    @FindBy(id = "associated-forms-route")
    private WebElement associatedForms;

    @FindBy(xpath = "//select[@id='dob-Month'] | //mat-select[@id='birthDate_month']")
    private WebElement selectMonth;

    @FindBy(xpath = "//select[@id='dob-Day']")
    private WebElement selectDay;

    @FindBy(xpath = "//input[@id='dob-Year']")
    private WebElement textBoxYear;

    @FindBy(xpath = "//button[text()='Save & Continue']")
    private WebElement saveAndContinueOnPatientInfo;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okOnPharmacy;

    @FindBy(xpath = "//button[text()='Add insurance'] | //span[text()='Add insurance card']")
    private WebElement addInsurance;

    @FindBy(xpath = "//div[@class=' css-tlfecz-indicatorContainer']")
    private WebElement insuranceNameDropDown;

    @FindBy(xpath = "//input[contains(@id,'react-select')]")
    private WebElement insuranceNameTextbox;

    @FindBy(xpath = "//input[@id='memberId']")
    private WebElement memberIdTextBox;

    @FindBy(xpath = "//input[@id='groupNumber']")
    private WebElement groupNumber;

    @FindBy(xpath = "//span[text()='Add insurance card'] | //button[text()='Add insurance']")
    private WebElement addInsuranceCard;

    @FindBy(xpath = "//span[text()='Skip and finish later']")
    private WebElement skipAndFinishLater;

    @FindBy(id = "refresh")
    private WebElement yopMailRefresh;

    @FindBy(id = "login_Username")
    private WebElement reviewqLoginTextbox;

    @FindBy(id = "login_Password")
    private WebElement reviewqPasswordTextbox;

    @FindBy(id = "login_Enterprise")
    private WebElement reviewqEnterprise;

    @FindBy(id = "login_Practice")
    private WebElement reveiwqPractice;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement reviewqLoginSubmit;

    //reviewq retrigger agent configuration for outbound appointments

    @FindBy(xpath = "//div[text()='Solution Configuration']")
    private WebElement solutionConfig;

    @FindBy(xpath = "(//td[text()='Other Solutions']/following::tr//a)[1]")
    private WebElement appointmentSection;

    @FindBy(xpath = "//p[text()='Outbound Appointment Details']/following::td//span/following::span/following::span[text()='STOP']")
    private WebElement outBoundAppStop;

    @FindBy(xpath = "(//ul/li/a/span)[1]")
    private WebElement reviewqLink;

    @FindBy(name = "reviewFirstname")
    private WebElement reviewqFirstNameTextBox;

    @FindBy(xpath = "//span[text()='Search']")
    private WebElement searchOnReviewq;

    @FindBy(xpath = "//tr/td//div//div//span/following::a//span[@aria-label='exception']")
    private WebElement insuranceIcon;

    @FindBy(xpath = "//h5[text()='Insurance']")
    private WebElement insuranceSection;

    @FindBy(xpath = "//a[text()='Map']")
    private WebElement mapInsurance;

    @FindBy(xpath = "//input[@id='externalID']")
    private WebElement mapPayerTextBox;

    @FindBy(xpath = "//button[@class='ant-btn ant-btn-primary ant-input-search-button']")
    private WebElement searchButtonForPayer;

    @FindBy(xpath = "//a[text()='Select']")
    private WebElement selectPayer;

    @FindBy(xpath = "//span[text()='Accept']/../../button/following::button[@type='submit']")
    private WebElement acceptPayer;

    @FindBy(xpath = "//span[text()='Accept']/../../button[@type='submit']")
    private WebElement acceptGuarantor;

    @FindBy(xpath = "(//span[text()='Reject'])[2]/../../button[@type='button']")
    private WebElement rejectPharmacyDeletion;

    @FindBy(xpath = "//div[text()='Submitted'] | //span[text()='Acknowledge']")
    private WebElement submittedForInsuranceInReviewq;

    @FindBy(xpath = "//h1[text()=\"You're PreChecked!\"]")
    private WebElement preCheckedConfirmation;

    @FindBy(id = "username")
    private WebElement practiceProvisioningUsernameTextBox;

    @FindBy(id = "password")
    private WebElement practiceProvisioningPasswordTextBox;

    @FindBy(xpath = "//a[normalize-space(text())='Login']")
    private WebElement practiceProvisioningLoginButton;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    private WebElement patientNameInAppointmentDash;

    @FindBy(xpath = "//button[text()='Launch Patient Mode']")
    private WebElement launchPatientMode;

    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueToPreCheck;

    @FindBy(xpath = "//button[text()='DELETE']")
    private WebElement deletePharmacy;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement deleteConfirmation;

    @FindBy(xpath = "//span[text()='Skip adding insurance']")
    private WebElement skipInsurance;

    @FindBy(xpath = "//span[@aria-label='reconciliation']")
    private WebElement healthDataIcon;

    @FindBy(xpath = "//h5[text()='Pharmacy']")
    private WebElement pharmacySection;

    @FindBy(xpath = "//button/span[text()='Yes']")
    private WebElement yesForPharmacyDeletion;

    @FindBy(xpath = "//div[text()='Are you the policy holder for this insurance?']/following::input/following::input")
    private WebElement noForPolicyHolder;

    @FindBy(xpath = "//input[@id='subscriberFirstName']")
    private WebElement subscriberFirstNameTextBox;

    @FindBy(xpath = "//input[@id='subscriberLastName']")
    private WebElement subscriberLastNameTextBox;

    @FindBy(id = "subscriberDoB-Month")
    private WebElement monthDropDown;

    @FindBy(id = "subscriberDoB-Day")
    private WebElement dayDropDown;

    @FindBy(id = "subscriberDoB-Year")
    private WebElement yearTextBox;

    @FindBy(xpath = "//label[text()='Sex assigned at birth']/following-sibling::div//div/div//div//div/input")
    private WebElement genderTextBox;

    @FindBy(xpath = "(//div[text()='Select one']/following::div[@class=' css-tlfecz-indicatorContainer'])[1]")
    private WebElement genderDropDownArrow;

    @FindBy(xpath = "//label[text()='Relationship to the patient']/following-sibling::div//div/div//div//div/input")
    private WebElement relationshipTextBox;

    @FindBy(xpath = "//div[text()='Select the Relationship']/following::div[@class=' css-tlfecz-indicatorContainer']")
    private WebElement relationshipDropDownArrow;

    @FindBy(xpath = "//span[text()='Add New']")
    private WebElement addNewInsuranceForSubscriber;

    @FindBy(xpath = "//tr/td//div//div//span/following::a//span[@aria-label='solution']")
    private WebElement demographicIcon;

    @FindBy(xpath = "//button[text()='DELETE']")
    private WebElement deleteInsuranceFromPreCheck;

    @FindBy(xpath = "//b[@class='profile-name']/../span/span")
    private WebElement profileNameIcon;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement reviewqLogout;

    @FindBy(xpath = "//h3[normalize-space(text())='Health Forms']")
    private WebElement healthFormIconOnPortal;

    @FindBy(id = "iframe")
    private WebElement frameIntoForm;

    @FindBy(id = "userid")
    private WebElement userNameTextBoxForPortal;

    @FindBy(id = "password")
    private WebElement passwordTextBoxForPortal;

    @FindBy(id = "signin_btn")
    private WebElement signInForPortal;

    @FindBy(name = "filterSubmit:submit")
    private WebElement clickOnLocation;

    @FindBy(xpath = "//input[@value='Select Provider']")
    private WebElement clickOnProvider;

    @FindBy(xpath = "//input[@name='filterSubmit:submit']")
    private WebElement selectLocationSubmit;

    @FindBy(xpath = "//a[text()='General Registration and Health History EHR']")
    private WebElement generalFormForInsurance;

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement proceed;

    @FindBy(id = "nextPageButton")
    private WebElement proceedToNextPage;

    @FindBy(id = "primary_insurance_company")
    private WebElement insuranceTextBoxOnPortal;

    @FindBy(id = "primary_insurance_company_phone")
    private WebElement insuranceSectionPhoneNumber;

    @FindBy(id = "primary_insurance_claims_address")
    private WebElement insuranceSectionAddress;

    @FindBy(id = "primary_insurance_claims_city")
    private WebElement insuranceSectionCity;

    @FindBy(id = "primary_insurance_claims_state")
    private WebElement insuranceStateDropdown;

    @FindBy(id = "primary_guarantor_phone_type")
    private WebElement guarantorPhoneType;

    @FindBy(id = "primary_insurance_claims_zipcode")
    private WebElement zipCodeInsuranceSection;

    @FindBy(id = "idonot_symptoms_general_group")
    private WebElement donotSymptoms;

    @FindBy(id = "idonot_medications")
    private WebElement iDontMedicationsCheckBox;

    @FindBy(id = "idonot_drug_allergies")
    private WebElement donotDrugAllergiesCheckBox;

    @FindBy(id = "idonot_food_allergies")
    private WebElement donotFoodAllergiesCheckBox;

    @FindBy(id = "idonot_surgerieshospitalizations")
    private WebElement donotSurgeries;

    @FindBy(id = "idonot_hospitalizations")
    private WebElement donotHospitalization;

    @FindBy(id = "idonot_conditions")
    private WebElement donotConditions;

    @FindBy(id = "idonot_familymedicalhistory")
    private WebElement donotFamilyMedicalHistory;

    @FindBy(xpath = "//a[normalize-space(text())='Submit']")
    private WebElement finalSubmit;

    @FindBy(xpath = "(//button[text()='Same As Patient'])[1]")
    private WebElement sameAsPatientButton;

    @FindBy(xpath = "(//button[text()='Same As Patient'])[2]")
    private WebElement sameAsPatientButton2;

    @FindBy(id = "primary_guarantor_firstname")
    private WebElement guarantorFirstNameTextBox;

    @FindBy(id = "primary_guarantor_lastname")
    private WebElement guarantorLastNameTextBox;

    @FindBy(id = "primary_guarantor_zipcode")
    private WebElement guarantorZip;

    @FindBy(id = "primary_guarantor_phone_phone")
    private WebElement guarantorPhone;

    @FindBy(xpath = "//a[text()='Save and finish another time']")
    private WebElement saveAndFinishAnotherTime;

    @FindBy(id = "providerFilter")
    private WebElement selectProviderDropDown;

    @FindBy(xpath = "//div[text()='No Review data found for the given search criteria']")
    private WebElement noDataInReviewQ;


    //Patient enrollment
    @FindBy(xpath = "//a[contains(text(),'Sign Up!')]")
    private WebElement signUpInYopMail;

    @FindBy(xpath = "//input[@id='postalCode']")
    private WebElement zipCode;

    @FindBy(xpath = "//select[@id='birthDate_month']")
    private WebElement monthOnYopmail;

    @FindBy(xpath = "//*[@id='birthDate_day']")
    private WebElement dayOnYopmail;

    @FindBy(xpath = "//*[@id='birthDate_year']")
    private WebElement selectYear;

    @FindBy(xpath = "//*[@id='nextStep']/span[1]")
    private WebElement nextStep;

    @FindBy(xpath = "//*[@id='secretQuestion']")
    private WebElement selectSecretQuestion;

    @FindBy(xpath = "//*[@id='secretAnswer']")
    private WebElement secretQAns;

    @FindBy(xpath = "//*[@id='userid']")
    private WebElement userName;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passWord;

    @FindBy(xpath = "//*[@id='phone1']")
    private WebElement phone1;

    @FindBy(xpath = "//*[@id='phone2']")
    private WebElement phone2;

    @FindBy(xpath = "//*[@id='phone3']")
    private WebElement phone3;

    @FindBy(xpath = "//*[@id='phone_type']")
    private WebElement selectPhoneType;

    @FindBy(xpath = "//div[@class='ng-input']")
    private WebElement selectLocation;

    @FindBy(xpath = "//*[@id='preferredLocationId_field']/mflocations/div/ng-select/div/div/div[2]/input")
    private WebElement enterLocation;

    @FindBy(xpath = "//*[@id='finishStep']/span[1]")
    private WebElement finishStep;

    @FindBy(xpath = "//*[@id='updateMissingInfoButton']")
    private WebElement updateMissingInfo;

    public void selectMonthDropDownOption() {
        int month = Integer.parseInt(String.valueOf(pidcInfo.getDob()).substring(4, 6));
        String monthstr = Month.of(month).toString().toLowerCase();
        String monthName = monthstr.substring(0, 1).toUpperCase() + monthstr.substring(1);
        /*Select Month = new Select(selectMonth);
        Month.selectByVisibleText(monthName);*/
        driver.findElement(By.xpath("//mat-select[@id='birthDate_month']")).click();
        driver.findElement(By.xpath("//mat-option["+month+"]"));
    }

    public void selectDayDropDownOption() {
        int day = Integer.parseInt(String.valueOf(pidcInfo.getDob()).substring(6, 8));
        Select daySelect = new Select(selectDay);
        daySelect.selectByVisibleText(String.valueOf(day));
    }

    public void selectSecretQuestion() {
        Select que = new Select(selectSecretQuestion);
        que.selectByVisibleText("What was the name of your childhood best friend?");
    }

    public void selectPhoneType(int index) {
        Select type = new Select(selectPhoneType);
        type.selectByIndex(index);
    }

    public void selectProvider() {
        Select provider = new Select(selectProviderDropDown);
        provider.selectByVisibleText(testData.getProperty("22510.provider.name"));
    }

    public void selectStateOnInsuranceSection() {
        Select state = new Select(insuranceStateDropdown);
        state.selectByVisibleText("NY");
    }

    public void selectPhoneTypeForGuarantor() {
        Select phoneType = new Select(guarantorPhoneType);
        phoneType.selectByVisibleText("Home");
    }



    public boolean activatePatient(String email) throws Exception {
        boolean flag = true;
        launchUrl(testData.getProperty("yopmail.url"));
        Thread.sleep(5000);
        sendKeys(login, email);
        clickButton(selectEmail, 5);
        waitForElementVisible(emailFrame, 5);
        switchToFrame(emailFrame);
        for (int i = 1; i <= 15; i++) {
            try {
                System.out.println(i);
                clickButton(signUpInYopMail, 5);
                break;
            } catch (Exception exception) {
                waitFor(10000);
                exitFromFrame(driver);
                clickButton(yopMailRefresh, 5);
                waitForElementVisible(emailFrame, 5);
                switchToFrame(emailFrame);
            }
        }
        switchToWindow(driver);
        sendKeysWithTimeout(zipCode, String.valueOf(pidcInfo.getZipCode()), 50);
        selectMonthDropDownOption();
        sendKeys(dayOnYopmail, String.valueOf(pidcInfo.getDob()).substring(6, 8));
        sendKeys(selectYear, String.valueOf(pidcInfo.getDob()).substring(0, 4));
        clickButton(nextStep, 10);
        selectSecretQuestion();
        sendKeys(secretQAns, "Friend");
        sendKeys(userName, pidcInfo.getPatientFirstName());
        sendKeys(passWord, "Admin@123");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        sendKeys(phone1, "999");
        sendKeys(phone2, "999");
        sendKeys(phone3, "9999");
        selectPhoneType(1);
        waitFor(2000);
        clickButton(selectLocation, 5);
        sendKeys(enterLocation, "Alaska");
        sendKeys(enterLocation, Keys.ENTER);
        clickButton(finishStep, 10);
        if (driver.getPageSource().contains(" Please correct the problems indicated below. ")) {
            js.executeScript("window.scrollBy(0,250)", "");
            sendKeys(enterLocation, "Another Location");
            sendKeys(enterLocation, Keys.ENTER);
            clickButton(finishStep, 10);
        }
        System.out.println("Patient activated successfully");
        clickButton(updateMissingInfo, 30);
        return flag;
    }

    public void clickIfAvailable(WebElement element) {
        try {
            clickButton(element, 30);
        } catch (Exception exception) {
        }
    }
}

