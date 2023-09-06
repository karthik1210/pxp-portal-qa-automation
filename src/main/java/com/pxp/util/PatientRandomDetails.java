//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import com.intuit.ifs.csscat.core.utils.Log4jUtil;

public class PatientRandomDetails {

	public static int count = 1;
	public static int dob = Integer.parseInt(dateOfBirth());
	public static int dobForAgeLimit = Integer.parseInt(dobForAgeLimit());

	private static String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"
			+ "abcdefghijklmnopqrstuvxyz";
	private static String alphaNumericStringWithoutNumbersAndSpecialCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvxyz";
	private static String alphaNumericStringIncludingSpecialCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"
			+ "abcdefghijklmnopqrstuvxyz" + ".,!@#$%&*_-";
	private static String numericString = "0123456789";
	private static String specialCharactersString = "!@#$%&*()+,-./:;<=>?[]^_`{|}";
	private static String randomNumericString = "123456789";
	
	public static String getFirst_name() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTimeInMillis());
		String generatedFirst_name = "ks"
				+ TimeUtil.getCurrentTimestamp().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		return (generatedFirst_name);
	}

	public static String getLast_name() {
		String timeStamp = new SimpleDateFormat("MMdd_ss").format(Calendar.getInstance().getTime());

		String generatedLast_name = "hh" + timeStamp;
		return (generatedLast_name);
	}

	public static String dateOfBirth() {
		String dateOfBirth = RandomUtils.nextInt(1970, 2002) + "0" + RandomUtils.nextInt(1, 9) + ""
				+ (RandomUtils.nextInt(11, 28) + count++);
		return dateOfBirth;
	}

	public static String dobForAgeLimit() {
		String dateOfBirth = RandomUtils.nextInt(2008, 2012) + "0" + RandomUtils.nextInt(1, 9) + ""
				+ RandomUtils.nextInt(11, 28);
		return dateOfBirth;
	}

	public static int newZipcode() {
		int generatedZipcode = RandomUtils.nextInt(11111, 19999);
		return generatedZipcode;
	}

	public static String updateDateOfBirth() {
		String dob = RandomUtils.nextInt(1970, 2002) + "0" + RandomUtils.nextInt(1, 9) + ""
				+ RandomUtils.nextInt(11, 28);
		return dob;
	}

	public static String getEmail() {
		String generatedEmail = getFirst_name() + "@yopmail.com";
		return (generatedEmail);
	}

	public static String getUpdatedEmail() {
		String generatedEmail = getFirst_name() + "1@yopmail.com";
		return (generatedEmail);
	}

	public static String getAdd1() {
		String generatedAdd1 = "Golden Tower " + RandomUtils.nextInt(1, 9);
		return (generatedAdd1);
	}

	public static String updateAdd1() {
		String updatedAdd1 = "Golden Villa " + RandomUtils.nextInt(1, 229);
		return (updatedAdd1);
	}

	public static String getAdd2() {
		String generatedAdd2 = "New Street " + RandomUtils.nextInt(1, 30);
		return (generatedAdd2);
	}

	public static String updateAdd2() {
		String updatedAdd2 = "RG Road " + RandomUtils.nextInt(1, 299);
		return (updatedAdd2);
	}

	public static String date() {
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		String timeStamp = new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String[] estScheduleTime() {
		ZoneId estZone = ZoneId.of("America/New_York");
		LocalTime currentTime = LocalTime.now(estZone);
		LocalTime twoHourLater = currentTime.plusHours(2);
		LocalTime twoHourEarlier = currentTime.minusHours(2);
		LocalTime threeHourEarlier = currentTime.minusHours(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String twoHrLater = twoHourLater.format(formatter);
		String twoHrEarlier = twoHourEarlier.format(formatter);
		String threeHrEarlier = threeHourEarlier.format(formatter);
		String[] times = {twoHrLater, twoHrEarlier, threeHrEarlier};
		return times;
	}

	public static String newZipcodes() {
		String generatedZipcode = "12345";
		return generatedZipcode;
	}

	public static String getmobileNo() {
		String generatedMob_No = "91" + RandomStringUtils.randomNumeric(8);
		return (generatedMob_No);
	}

	public static String randomNumber() {
		String generatedZipcode = String.valueOf(RandomUtils.nextInt(11111, 99999));
		return generatedZipcode;
	}

	public static String getSubscriber_FirstName() {
		String timeStamp = new SimpleDateFormat("MMdd_ss").format(Calendar.getInstance().getTimeInMillis());
		String generatedFirst_name = "Subscriber"
				+ TimeUtil.getCurrentTimestamp().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
		return (generatedFirst_name);
	}

	public static String generateMobileNo() {
		String generatedMob_No = RandomStringUtils.randomNumeric(10);
		return (generatedMob_No);
	}

	public static String getMailinatorEmail() {
		String generatedEmail = getFirst_name() + "@mailinator.com";
		return (generatedEmail);
	}

	public static String generateRandomString() {
		String generateRandom_String = RandomStringUtils.randomAlphanumeric(10);
		return (generateRandom_String);
	}

	public static String dateTimeformatter() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Log4jUtil.log(formatter.format(now));
		return formatter.format(now);
	}

	public static Long generateMobileNumber() {
		String mob = generateNumericString(10);
		mob = mob.toString().replace('0', '9');
		return Long.parseLong(mob);
	}

	public static String generateNumericString(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (numericString.length() * Math.random());
			sb.append(numericString.charAt(index));
		}
		return sb.toString();
	}

	public static String generateAlphaNumericString(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static String generateRandomStringWithoutSymbolsOrNumbers(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (alphaNumericStringWithoutNumbersAndSpecialCharacters.length() * Math.random());
			sb.append(alphaNumericStringWithoutNumbersAndSpecialCharacters.charAt(index));
		}
		return sb.toString();
	}

	public static String generateAlphaNumericStringIncludingSpecialChar(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (alphaNumericStringIncludingSpecialCharacters.length() * Math.random());
			sb.append(alphaNumericStringIncludingSpecialCharacters.charAt(index));
		}
		return sb.toString();
	}

	public static String generateSpecialCharactersString(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (specialCharactersString.length() * Math.random());
			sb.append(specialCharactersString.charAt(index));
		}
		return sb.toString();
	}
	
	public static String generateRandomNumericString(int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (randomNumericString.length() * Math.random());
			sb.append(randomNumericString.charAt(index));
		}
		return sb.toString();
	}
}
