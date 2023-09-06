//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.util;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	// appt details
	static int begintime = RandomUtils.nextInt(11, 20);
	public static String apptBegintime = begintime + "15";
	public static String apptEndtime = begintime + "30";

	public static String setApptDate(long day) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime tomorrow = now.plusDays(day);
		return dtf.format(tomorrow);
	}

	public static String estToistConversion(String apptDate, String apptBeginTime) {
		String estTime = apptDate + " " + apptBeginTime;
		// Convert EST time to LocalDateTime
		DateTimeFormatter estFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
		LocalDateTime estDateTime = LocalDateTime.parse(estTime, estFormatter);
		// Convert LocalDateTime to ZonedDateTime with EST time zone
		ZoneId estZoneId = ZoneId.of("America/New_York");
		ZonedDateTime estZonedDateTime = estDateTime.atZone(estZoneId);
		// Convert ZonedDateTime to IST time zone
		ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
		ZonedDateTime istZonedDateTime = estZonedDateTime.withZoneSameInstant(istZoneId).plusHours(4);
		// Format IST time as desired format
		DateTimeFormatter istFormatter = DateTimeFormatter.ofPattern("M/d/yy h:mm a");
		String istTime = istZonedDateTime.format(istFormatter).toUpperCase();
		return istTime;
	}

	public static String istApptDate(String apptDate, String apptBeginTime, int dateTime) throws Exception {
		String dateTimeString = estToistConversion(apptDate, apptBeginTime);
		String[] parts = dateTimeString.split(" ");
		String dateString = parts[dateTime];
		return dateString;
	}

	public static String apptDataSyncScheduleTime(int hour) {
		// Get the current time in the EST time zone
		ZoneId estZone = ZoneId.of("America/New_York");
		LocalTime currentTime = LocalTime.now(estZone);
		LocalTime adjustedTime = currentTime.plusHours(hour);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
		String formattedTime = adjustedTime.format(formatter);
		return formattedTime;
	}

	public static String getCurrentTimestamp() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String timestamp = formatter.format(currentDateTime);
		return timestamp;
	}

	public static String getESTDate(int daysToAdd) {
		TimeZone estTimeZone = TimeZone.getTimeZone("America/New_York");
		Calendar estCalendar = Calendar.getInstance(estTimeZone);
		estCalendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
		Date estDate = estCalendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setTimeZone(estTimeZone);
		String formattedDate = dateFormat.format(estDate);
		return formattedDate;
	}

	public static String getDateInFormat(int dayToAdd) throws Exception {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
		Date date = inputFormat.parse(getESTDate(dayToAdd));
		String outputDate = outputFormat.format(date);
		return outputDate;
	}
}
