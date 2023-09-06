//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.base;

import com.medfusion.common.utils.PropertyFileLoader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.pxp.util.PatientRandomDetails;
import com.pxp.util.TimeUtil;
import org.apache.commons.lang.RandomStringUtils;

public class ConstantConfigData {

	static PropertyFileLoader testData;
	static IntegrationDb integrationDb;

	static {
		try {
			testData = new PropertyFileLoader();
			integrationDb = new IntegrationDb(testData);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	// DB
	public static final String MFDB = testData.getProperty("db.config.name");
	public static final String NGDB = testData.getProperty("db.nextgen.name");

	public static final String PRACTICE_URL = testData.getProperty("practice.url");
	public static final int PSTATUS_NEW = Integer.parseInt(testData.getProperty("processingstatus.new"));
	public static final int PSTATUS_INITIATE = Integer.parseInt(testData.getProperty("processingstatus.initiated"));
	public static final int PSTATUS_PENDING = Integer.parseInt(testData.getProperty("processingstatus.pending"));
	public static final int PSTATUS_COMPLETED = Integer.parseInt(testData.getProperty("processingstatus.completed"));
	public static final int PSTATUS_DELETED = Integer.parseInt(testData.getProperty("processingstatus.deleted"));
	public static final int PSTATUS_DEACTIVATE = Integer.parseInt(testData.getProperty("processingstatus.deactivated"));
	public static final int PSTATUS_FAILED = Integer.parseInt(testData.getProperty("processingstatus.failed"));
	public static final int PSTATUS_NOCREDS = Integer
			.parseInt(testData.getProperty("processingstatus.noportalcredential"));
	public static final int STATUS_PENDING = Integer.parseInt(testData.getProperty("enrollstatus.Pending"));
	public static final int STATUS_COMPLETED = Integer.parseInt(testData.getProperty("enrollstatus.completed"));
	public static final int STATUS_NOTENROLL = Integer.parseInt(testData.getProperty("enrollstatus.notEnrolled"));
	public static final int STATUS_DEACTIVATED = Integer.parseInt(testData.getProperty("enrollstatus.deactivated"));
	public static final String PROCSTATUS_COMPLETED = testData.getProperty("mfprocessingstatus.completed");
	public static final String PROCSTATUS_PENDING = testData.getProperty("mfprocessingstatus.pending");
	public static final String NULL_VALUE = null;
	public static final int PLUS_ONE = Integer.parseInt(testData.getProperty("plus.one"));
	public static final int PLUS_TWO = Integer.parseInt(testData.getProperty("plus.two"));
	public static final int PLUS_ZERO = Integer.parseInt(testData.getProperty("plus.zero"));
	public static final String EVENTID = testData.getProperty("event.id");
	public static final String EVENTNAME = testData.getProperty("event.name");
	public static final String LOCID = testData.getProperty("loc.id");
	public static final String LOCNAME = testData.getProperty("loc.name");
	public static final String RESOURCEID = testData.getProperty("provider.id");
	public static final String RESOURCENAME = testData.getProperty("provider.name");
	public static final int PRACTICEID = Integer.parseInt(testData.getProperty("default.mf.practice.id"));
	public static final int APPTTYPE = Integer.parseInt(testData.getProperty("coreType.apptType"));
	public static final int LOCATION = Integer.parseInt(testData.getProperty("coreType.loc"));
	public static final int RESOURCE = Integer.parseInt(testData.getProperty("coreType.resource"));
	public static final String USERID_1 = testData.getProperty("mstr.user1.id");
	public static final String USERFIRSTNAME_1 = testData.getProperty("mstr.user1.firstname");
	public static final String USERLASTNAME_1 = testData.getProperty("mstr.user1.lastname");
	public static final String USERID_2 = testData.getProperty("mstr.user2.id");
	public static final String USERFIRSTNAME_2 = testData.getProperty("mstr.user2.firstname");
	public static final String USERLASTNAME_2 = testData.getProperty("mstr.user2.lastname");
	public static final String USERID_3 = testData.getProperty("mstr.user3.id");
	public static final String USERFIRSTNAME_3 = testData.getProperty("mstr.user3.firstname");
	public static final String USERLASTNAME_3 = testData.getProperty("mstr.user3.lastname");
	public static final String ROUTELOC_NAME = testData.getProperty("routelocation.name");
	public static final String ROUTELOC_ID = testData.getProperty("routelocation.id");
	public static final String ROUTEPROV_NAME = testData.getProperty("routeprovider.name");

	// Create Patient
	public String FIRSTNAME = PatientRandomDetails.getFirst_name();
	public String LASTNAME = PatientRandomDetails.getLast_name();
	public String EMAIL = PatientRandomDetails.getEmail();
	public int ZIPCODE = PatientRandomDetails.newZipcode();
	public int DOBVAR = PatientRandomDetails.dob;
	public int DOBFORAGELIMIT = PatientRandomDetails.dobForAgeLimit;
	public static final String DEFAULTPRACTICEID = testData.getProperty("default.practice.id");
	public static final String DEFAULTLOCATIONID = testData.getProperty("location.id");

	// Patient Invite
	public String ADDRESS1 = PatientRandomDetails.getAdd1();
	public String ADDRESS2 = PatientRandomDetails.getAdd2();

	// Duration
	public static final int TENSEC = Integer.parseInt(testData.getProperty("time.ten.sec"));
	public static final int ONEMIN = Integer.parseInt(testData.getProperty("time.one.min"));
	public static final int TWOMINS = Integer.parseInt(testData.getProperty("time.two.min"));
	public static final int THREEMINS = Integer.parseInt(testData.getProperty("time.three.min"));
	public static final int FIVEMINS = Integer.parseInt(testData.getProperty("time.five.min"));
	public static final int TENMINS = Integer.parseInt(testData.getProperty("time.ten.min"));
	public static final int FIFTEENMINS = Integer.parseInt(testData.getProperty("time.fifteen.min"));
	public static final int TWENTYSECS = Integer.parseInt(testData.getProperty("time.tewnty.sec"));

	// Medication
	public static final String MEDICATION = testData.getProperty("db.medication.dologen");
	public static final String NDCID = testData.getProperty("db.dologen.ndc.id");
	public static final String MEDICATIONSTATUS = testData.getProperty("db.status");
	public static final String ENTERPRISEID = testData.getProperty("enterprise.id");
	public static final String EVENT = testData.getProperty("db.event");
	public static final String MEDICATIONONE = testData.getProperty("db.medication.crestor");
	public static final String NDCIDONE = testData.getProperty("db.crestor.ndc.id");
	public static final String USERNAME = testData.getProperty("user.name");
	public static final String REQUEST = PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String BASEURL = "http"
			+ PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(10);
	public static final String KEYVALUE = PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String STATE = testData.getProperty("state");
	public static final String FAX = PatientRandomDetails.getmobileNo();
	public static final String PHONE = PatientRandomDetails.getmobileNo();
	public static final String ZIP = testData.getProperty("zip.code");
	public static final String NAMENEW = PatientRandomDetails.getFirst_name()
			+ PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String ADDONENEW = PatientRandomDetails.updateAdd1()
			+ PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String ADDTWONEW = PatientRandomDetails.updateAdd2()
			+ PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String CITYNEW = PatientRandomDetails.updateAdd2()
			+ PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(4);
	public static final String ZIPNEW = PatientRandomDetails.newZipcodes();
	public static final String PHONEONE = PatientRandomDetails.getmobileNo();
	public static final String RACE = testData.getProperty("person.race");
	public static final String ETHNICITY = testData.getProperty("person.ethnicity");

	// CCD
	public static final String CCDEXPECTEDSTATUS = testData.getProperty("ccd.status");
	public static final String EXPECTEDSTATUSONE = testData.getProperty("ccd.status.one");
	public static final int EXPENROLCCDTYPE = Integer.parseInt(testData.getProperty("enr.ccd.type"));
	public static final int EXPFILTEREDCCDTYPE = Integer.parseInt(testData.getProperty("filtered.ccd.type"));
	public static final int EXPLOCKEDENCCCDTYPE = Integer.parseInt(testData.getProperty("locked.enc.ccd.type"));
	public static final int EXPMANUALCCDTYPE = Integer.parseInt(testData.getProperty("manual.ccd.type"));
	public static final int EXPMSUCCDTYPE = Integer.parseInt(testData.getProperty("msu.ccd.type"));
	public static final String SHAREDCCDMSG = testData.getProperty("shared.ccd.msg");
	public static final String DIRECTADDRESSCCD = testData.getProperty("direct.add.ccd");

	// Payments
	public static final String MASTERCARDNUMBER = testData.getProperty("payments.master.card");
	public static final String MASTERCARDTYPE = testData.getProperty("master.card.type");
	public static final String DISCOVERCARDNUMBER = testData.getProperty("payments.discover.card");
	public static final String DISCOVERCARDTYPE = testData.getProperty("discover.card.type");
	public static final String AMEXCARDNUMBER = testData.getProperty("payments.amex.card");
	public static final String AMEXCARDTYPE = testData.getProperty("amex.card.type");
	public static final String VISACARDNUMBER = testData.getProperty("payments.visa.card");
	public static final String VISACARDTYPE = testData.getProperty("visa.card.type");
	public static final String CVV = testData.getProperty("payments.cvv");
	public static final String AMEXCARDCVV = testData.getProperty("payments.cvv.one");
	public static final String COPAYTYPE = testData.getProperty("copayments.type");
	public static final String SECUREBILLPAYMENTTYPE = testData.getProperty("bill.payments.type");
	public static final String PAYMENTAMOUNT = PatientRandomDetails.generateRandomNumericString(1);
	public static final String COPAYAMOUNT = PatientRandomDetails.generateRandomNumericString(1);
	public static final String PAYMENTCOMMENT = PatientRandomDetails.generateAlphaNumericString(6);
	public static final String CARDHOLDERNAME = PatientRandomDetails.getFirst_name();
	public static final String PHONENUMBER = PatientRandomDetails.generateMobileNo();
	public static final String ZIPCODE1 = testData.getProperty("secure.zipCode");
	public static final String INVALIDACCOUNTNUMBER = PatientRandomDetails.generateNumericString(6);
	public static final String DBPASSWORD = testData.getProperty("db.password");
	public static final String VALIDACCOUNTNUMBER = testData.getProperty("secure.validAccountNumber");
	public static final String SECZIPCODE = testData.getProperty("secure.zipCode");
	public static final String SEDATE = testData.getProperty("secure.date");
	public static final String SEYEAR = testData.getProperty("secure.year");
	public static final String DATEYEARVAL = testData.getProperty("copay.date.year");
	public static final String QUANTITY = PatientRandomDetails.generateNumericString(1);
	public static final String UNITPRICE = PatientRandomDetails.generateNumericString(1);
	public static final String ZIPCODES = testData.getProperty("vcs.cardholders.zip");
	public static final String OTHERPAYMENTSTYPE = testData.getProperty("other.payments.type");
	public static final String OTHERPAYMENTCVVCODE = testData.getProperty("vcs.cvv.code");
	public static final String OTHERPAYMENTZIP = testData.getProperty("vcs.cardholders.zip");
	public static final String OTHERPAYMENACCOUNTNUMBER = testData.getProperty("vcs.account.number");
	public static final String OTHERPAYMENTPATIENTNAME = testData.getProperty("vcs.patient.name");
	public static final String OTHERPAYMENTSCARDNAMEDISCOVER = testData.getProperty("other.paymemts.discovers");
	public static final String OTHERPAYMENTSDATES = testData.getProperty("other.payments.dates");
	public static final String OTHERPAYMENTSCARDYEAR = testData.getProperty("other.payments.year");
	public static final String OTHERPAYMENTSLOCATIONONE = testData.getProperty("other.payments.locations.one");
	public static final String OTHERPAYMENTSLOCATIONTWO = testData.getProperty("other.payments.locations.two");
	public static final String OTHERPAYMENTSLOCATIONTHREE = testData.getProperty("other.payments.locations.three");
	public static final String OTHERPAYMENTSLOCATIONFOUR = testData.getProperty("other.payments.locations.four");
	public static final String OTHERPAYMENTSDATE = testData.getProperty("other.payments.date");
	public static final String OTHERPAYMENTVALIDACCOUNTNUMBER = testData.getProperty("secure.validAccountNumber");
	public static final String BALPAYTYPE = testData.getProperty("balance.payments.type");

	// HealthForms
	public static final String HEALTHADD1 = PatientRandomDetails.getAdd1();
	public static final String HEALTHCITYS = PatientRandomDetails.getAdd2();
	public static final String HEALTHCODE = PatientRandomDetails.newZipcodes();
	public static final String HEALTHEMAILID = testData.getProperty("db.emailId");
	public static final String HEALTHFIRSTNAMES = testData.getProperty("db.firstname");
	public static final String HEALTHLASTNAMES = testData.getProperty("db.lastname");
	public static final String HEALTHPHNONUMBER = testData.getProperty("phoneNumber");
	public static final String HEALTHPHONTYPE = testData.getProperty("phoneType");
	public static final String INSURANCECOMPANYNAME = testData.getProperty("insurancesCompany");
	public static final String INSURANCEADDRESS = testData.getProperty("insurancesAddress");
	public static final String REFERRINGPROVIDER = testData.getProperty("reffereingProvider");
	public static final String PROVIDERNUMBER = testData.getProperty("reffereingProviderNum");
	public static final String PHARMACYADDRESS = testData.getProperty("pharamacyAddress");
	public static final String PHARMACYNUMBER = testData.getProperty("pharamacyNumber");
	public static final String MEDICATIONNAME = testData.getProperty("medicationsName");
	public static final String MEDICATIONFREQ = testData.getProperty("medicationfrequency");
	public static final String MEDICATIONCOMMENTS = testData.getProperty("medicationsComments");
	public static final String VACCINATIONDETAILS = testData.getProperty("vaccinationdetails");
	public static final String VACCINATIONCOMMENTS = testData.getProperty("vaccinationsComments");
	public static final String SURGERYNAME = testData.getProperty("surgeriesName");
	public static final String EXAMCOMMENTS = testData.getProperty("examsComments");
	public static final String FAMILYNAME = testData.getProperty("familyName");
	public static final String FAMILYHISTORY = testData.getProperty("familHistory");
	public static final String OCCUPATIONS = testData.getProperty("occupations");
	public static final String HEALTHHABITS = testData.getProperty("healthHabits");
	public static final String SOCIALHISTORY = testData.getProperty("socialHistroy");
	public static final String SOURCEPRECHECK = testData.getProperty("source.precheck");
	public static final String DESTINATIONPRECHECK = testData.getProperty("destination.precheck");
	public static final String DESTINATIONPORTAL = testData.getProperty("destination.portal");
	public static final String HEALTHHEIGHT = testData.getProperty("health.forms.height");
	public static final String HEALTHWEIGHT = testData.getProperty("health.forms.weight");
	public static final String HEALTHDATE = testData.getProperty("health.forms.date");
	public static final String FORMNAME = testData.getProperty("name.forms");
	public static final String FORMSMONTH = testData.getProperty("forms.month");
	public static final String FORMSCOMMENTS = testData.getProperty("forms.comments");
	public static final String FORMSDURATION = testData.getProperty("forms.duration");
	public static final String FORMSDATA = testData.getProperty("forms.data");
	public static final String PATIENTINVITE = testData.getProperty("patient.invite");
	public static final String PATIENTAGE = PatientRandomDetails.generateNumericString(2);
	public static final String FORMSWEEK = testData.getProperty("forms.week");
	public static final String FORMSCUPS = testData.getProperty("forms.cups");
	public static final String FORMSCIG = testData.getProperty("forms.cig");
	public static final String ADD1 = PatientRandomDetails.getAdd1();
	public static final String ADD2 = PatientRandomDetails.getAdd2();
	public static final String CITY = testData.getProperty("city.name");
	public static final String MIDDLENAME = PatientRandomDetails.generateAlphaNumericString(6);
	public static final String SOURCEPORTAL = testData.getProperty("source.portal");
	public static final String HEALTHGURANATOR = testData.getProperty("health.forms.name");
	public static final int HEALTHCOUNT = Integer.parseInt(testData.getProperty("health.forms.date"));
	public static final String HEALTLOCATIONNAME = testData.getProperty("health.forms.location");
	public static final String HEALTHPROVIDERNAME = testData.getProperty("health.forms.provider");
	public static final String INSURANCENAME = testData.getProperty("insurance.name");

	// Appointments
	public static final String APPTREASON = PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String APPTLOCATION = testData.getProperty("provider").trim();
	public static final String APPTPROVIDER = testData.getProperty("location").trim();
	public static final String APPTUSERNAME = testData.getProperty("username");
	public static final String APPTPASSWORD = testData.getProperty("password");
	public static final String APPTFN = testData.getProperty("firstname").trim();
	public static final String APPTLN = testData.getProperty("emailId").trim();
	public static final String RESPONSEMESSAGE = "ResponseMessage"
			+ PatientRandomDetails.generateRandomStringWithoutSymbolsOrNumbers(6);
	public static final String PROVIDERNAME = testData.getProperty("provider.name");
	public static final String APPTORIGINATOR = testData.getProperty("appt.originator");
	public static final String DATETIME = PatientRandomDetails.dateTimeformatter();
	public static final String MAXCHARREASON = PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(254);
	public static final String REASONWITHCHAR = PatientRandomDetails.generateSpecialCharactersString(6);
	public static final String EARLYMORTIME = testData.getProperty("early.morning.time").trim();
	public static final String LATEMORTIME = testData.getProperty("late.morning.time").trim();
	public static final String EARLYAFTTIME = testData.getProperty("early.afternoon.time").trim();
	public static final String LATEAFTTIME = testData.getProperty("late.afternoon.time").trim();
	public static final String EVETIME = testData.getProperty("eve.time").trim();
	public static final String ANYDAYS = testData.getProperty("any.days").trim();
	public static final String FIRSTNAME1 = "subscriber" + PatientRandomDetails.getFirst_name();
	public static final String LASTNAME1 = "subscriber" + PatientRandomDetails.getLast_name();
	public static final String EMAILID1 = PatientRandomDetails.getEmail();
	public static final String GETMSGRESCHEDULEAPPT = testData.getProperty("cancelAppt");
	public String APPT_DATE = TimeUtil.setApptDate(4);
	public String APPT_BEGIN_TIME = TimeUtil.apptBegintime;
	public String APPT_END_TIME = TimeUtil.apptEndtime;
	public String NEW_APPT_DATE = TimeUtil.setApptDate(11);
	public static final String NULL_STATUS = "";
	public static final String CONFIRMINDTRUE = testData.getProperty("appt.confirmindtrue");
	public static final String CONFIRMINDFALSE = testData.getProperty("appt.confirmindfalse");

	// Statement Preferences
	public static final String STATEMENTUSERNAME = testData.getProperty("a.username");
	public static final String STATEMENTPASSWORD = testData.getProperty("p.password");
	public static final String STATEMENTPAPERSTATEMENTS = testData.getProperty("statement.paper");
	public static final String STATEMENTELECTRONICALLY = testData.getProperty("statement.electornically");
	public static final String STATEMENTPEROSNID = testData.getProperty("person.ids");

	// Login Activity
	public static final int NOOFPATIENTS = Integer.parseInt(testData.getProperty("bulkpatient"));
	public static final String PERSONID = testData.getProperty("persion.id");	
	public static final String LAUSERNAME = testData.getProperty("la.username");
	public static final String LAFIRSTNAME = testData.getProperty("la.firstname");


	// Read Receipts
	public static final String READRECEIPTSUB = testData.getProperty("rr.subject");
	public static final String COMMID = testData.getProperty("comm.id");

	// Inbound-Outbound Messaging
	public static final String PARENTID = null;
	public String REPLYSUBJECT = "Auto Sub:" + TimeUtil.getCurrentTimestamp() + "ss";
	public String REPLYBODY = "Message with automation " + TimeUtil.getCurrentTimestamp();
	public static final String NULLSUBJECT = "";
	public static final String NULLBODY = "";
	public static final String VALIDATENULLSUBJECT = testData.getProperty("validatenullsubject");
	public static final String VALIDATTACHMENTS = testData.getProperty("attachment.valid");
	public static final String INVALIDATTACHMENTS = testData.getProperty("attachment.invalid");
	public static final String MULTIPLEATTACHMENTS = testData.getProperty("attachment.multiple");
	public static final String COMM_ID = testData.getProperty("commid.column");
	public static final String PARENTID_COLUMN = testData.getProperty("parentid.column");
	public String MESSAGESUBJECT = "Auto_Sub:" + TimeUtil.getCurrentTimestamp() + "ss";
	public String QUESTIONBODY = "Details with automation " + TimeUtil.getCurrentTimestamp();
	public String MAXQUESTIONBODY = RandomStringUtils.randomAlphanumeric(4000);
	public static final int SINGLEATTACHMENT = Integer.parseInt(testData.getProperty("singleattachment"));
	public static final int FIVEATTACHMENTS = Integer.parseInt(testData.getProperty("fiveattachment"));
	public static final String[] ROUTE_ID = { USERID_2, USERID_3 };
	public static final String[] ROUTE_NAME = { USERFIRSTNAME_2 + " " + USERLASTNAME_2,
			USERFIRSTNAME_3 + " " + USERLASTNAME_3 };
	public static final String ACTIVATE_IND = "N";
	public static final String DEACTIVATE_IND = "Y";
	public static final String QUESTION_LABEL = testData.getProperty("questionlabel");
	public static final String ACTIVEPATIENT = testData.getProperty("patient.active");
	public static final String INACTIVEPATIENT = testData.getProperty("patient.inactive");
	public static final String YOPMAILURL = testData.getProperty("yopmail.url");

	// Bulk Messages
	public static final boolean MSGRECEIVED = true;
	public static final int USERID = Integer.parseInt(testData.getProperty("user.id"));
	public static final int PROCESSINGSTATUS = Integer.parseInt(testData.getProperty("processing.status"));
	public static final String SENDERNAME = testData.getProperty("msgsendername");
	public static final String SENDERTYPE = testData.getProperty("sender.type");
	public static final int SENDERTYPEVALUE = Integer.parseInt(testData.getProperty("sendertype.value"));
	public static final int SENDERTYPEVALUEREPLY = Integer.parseInt(testData.getProperty("sendertype.replyvalue"));
	public static final String MSGREAD = testData.getProperty("hasreadmsg");

	// Recall
	public static final String RECALL_AGENT_NAME = testData.getProperty("agent.recall");
	public static final String PATIENTINVITE_AGENT_NAME = testData.getProperty("agent.patientinvite");
	public static final String PROCESSINGSTAUTS_AGENT_NAME = testData.getProperty("agent.processingstatus");
	public static final String ACTIVEWITHNORECALL = testData.getProperty("patient.activewithnorecall");
	public static final List<String> RECALL_PLAN_ID = Arrays.asList(testData.getProperty("recallplan.id"));
	public static final String RECALL_FORMID1 = testData.getProperty("recallplan.formid1");
	public static final String RECALL_FORMID2 = testData.getProperty("recallplan.formid2");
	public static final String RECALL_FORMID3 = testData.getProperty("recallplan.formid3");
	public static final int SEQ_NUMBER = 999;
	public static final String RECALL_MESSAGE = testData.getProperty("recallmessage");

	// PXP Admin Tool
	public static final String REVIEWQUSERNAME = testData.getProperty("reviewQUsername");
	public static final String REVIEWQPASSWORD = testData.getProperty("reviewQPassword");
	public static final String REVIEWQURL = testData.getProperty("reviewQurl");
	public static final String BATCHNAME = PatientRandomDetails.generateAlphaNumericStringIncludingSpecialChar(7);
	public static final String PERSONNBR = testData.getProperty("secure.personNbr");
	public static final String TRANSICTIONID = PatientRandomDetails.generateNumericString(10);
	public static final String STARTDATE = testData.getProperty("from.date");
	public static final String ENDDATE = testData.getProperty("to.date");
	public static final String STATUS = testData.getProperty("valid.status");
	public static final String STATUSFORINVALID = testData.getProperty("invalid.status");
	public static final String EXPECTIONMESSAGE = testData.getProperty("expectation.message");

	public static final String USER = testData.getProperty("dropdown.user.name");
	public static final String ADMINNAME = testData.getProperty("admin.name");

	// Practice Portal
	public static final String PRACTICEPORTALUSERNAME=testData.getProperty("practicePortalUserName");
    public static final String PRACTICEPORTALPASSWORD=testData.getProperty("practicePortalPassword");
	
	public static final String VALIDATTACHMENTONE = testData.getProperty("valid.attachment.one");
	public static final String VALIDATTACHMENTTWO = testData.getProperty("valid.attachment.two");
	public static final String VALIDATTACHMENTTHREE = testData.getProperty("valid.attachment.three");
	public static final String VALIDATTACHMENTFOUR = testData.getProperty("valid.attachment.four");
	public static final String VALIDATTACHMENTFIVE = testData.getProperty("valid.attachment.five");
	public static final String VALIDATTACHMENTSIX = testData.getProperty("valid.attachment.six");
	public static final String INVALIDATTACHMENT = testData.getProperty("invalid.attachment");
	public static final String REQUESTREASON = testData.getProperty("request.reason");
	public static final String NEWUSERID = testData.getProperty("new.user.id");

	// Inbound Forms Pdf
	public static final String INBOUNDFORMSREFERRINGPROVIDERID = testData.getProperty("inbound.reffering.provider.id");
	public static final String PRECHECKDELETE = testData.getProperty("precheck.delete");
	public static final String PRECHECKREJECT = testData.getProperty("precheck.reject");
	public static final String MAILMESSAGE = testData.getProperty("mail.message");

}
