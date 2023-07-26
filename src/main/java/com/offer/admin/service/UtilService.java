package com.offer.admin.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UtilService {
	 public static String hashPassword(String password) {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(password.getBytes());
	            byte[] bytes = md.digest();
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < bytes.length; i++) {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            generatedPassword = sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        log.debug(generatedPassword);
	        return generatedPassword;
	    }


	    public static String getISOFromDate(Date date) {

	        String dateString = null;
	        if (null != date) {

	            dateString = date.toInstant().toString();
	            return dateString;
	        }

	        return dateString;

	    }


	    public static Date getDateFromISOString(String dateInISOString) throws Exception {

	        Date date = null;

	        if (null != dateInISOString && !dateInISOString.isEmpty()) {
	            date = Date.from(Instant.parse(dateInISOString));
	            return date;
	        }
	        return date;
	    }

	    public static Date removeTime(Date date) {

	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }


	    public static LocalDate getLocalDateByDate(Date date) {

	        Instant instant = Instant.ofEpochMilli(date.getTime());
	        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	        LocalDate localDate = localDateTime.toLocalDate();
	        return localDate;

	    }

	    public static LocalTime getLocalTimeByDate(Date date) {

	        Instant instant = Instant.ofEpochMilli(date.getTime());
	        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	        LocalTime localTime = localDateTime.toLocalTime();


	        return localTime;

	    }


	    public static Date getUtilDateByLocalDate(LocalDate date) {
	        Date UtilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        return UtilDate;

	    }


	    public static boolean checkForToBeRenew(Date date) {


	        LocalDate localReNewDate = UtilService.getLocalDateByDate(date);
	        LocalDate localTodayDate = UtilService.getLocalDateByDate(new Date());
	        Period period = Period.between(localTodayDate, localReNewDate);

	        return period.getDays() <= 7 && period.getMonths() == 0 && period.getMonths() == 0;
	    }

	    public static Date getDateAfterEndDate(Date date) {

	        LocalDate endDate = getLocalDateByDate(date);
	        LocalDate dateAfter = endDate.plusDays(15);

	        return getUtilDateByLocalDate(dateAfter);
	    }

	    public static Date getDateBeforeStartDate(Date date) {

	        LocalDate endDate = getLocalDateByDate(date);
	        LocalDate dateAfter = endDate.minusDays(1);

	        return getUtilDateByLocalDate(dateAfter);
	    }

	    public static float floatFormat(float num) {
	        float num3 = num * 100;
	        int num1 = (int) num3;
	        float num2 = (float) num1 / 100;
	        return num2;
	    }

	    public static String getManufacturingId() {
	        Calendar calendar = Calendar.getInstance();
	        return "MFG" + generateId() + generateAnotherId()
	                + calendar.get(Calendar.DATE) + calendar.get(Calendar.YEAR);


	    }

	    public static int generateId() {
	        Random random = new Random();
	        return random.nextInt(1000);
	    }

	    public static String generateAnotherId() {

	        return RandomStringUtils.randomNumeric(9);
	    }
	    
	    public static Date getPlusDays(Date date,int day) throws Exception {
//	    	LocalDate localDate = UtilService.getLocalDateByDate(date).plusDays(day);
//	    	Date resultDate = UtilService.getUtilDateByLocalDate(localDate);
//	    	return resultDate;
	    	 LocalDate endDate = getLocalDateByDate(date);
		        LocalDate dateAfter = endDate.plusDays(day);

		        return getUtilDateByLocalDate(dateAfter);

	    	}
	}

