package com.aarujob.parseurhtml.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class provides methods which help delevoper to format date retrive in
 * 
 * @author smag
 * @version 1.0.0
 */
public class DateTimeHelper {

    private DateTimeHelper() {
    }

    /**
     * get the value of month
     * 
     * @return integer corresponding to month string
     */
    public static int getMonth(String monthStr) {

        String month = monthStr.toLowerCase();
        if (month.equals("janvier") || month.equals("january") || month.equals("jan"))
            return Calendar.JANUARY;
        if (month.equals("février") || month.equals("february") || month.equals("fev") || month.equals("feb"))
            return Calendar.FEBRUARY;
        if (month.equals("mars") || month.equals("march") || month.equals("mar"))
            return Calendar.MARCH;
        if (month.equals("avril") || month.equals("april") || month.equals("apr"))
            return Calendar.APRIL;
        if (month.equals("mai") || month.equals("may"))
            return Calendar.MAY;
        if (month.equals("juin") || month.equals("june") || month.equals("jui") || month.equals("jun"))
            return Calendar.JUNE;
        if (month.equals("juillet") || month.equals("july") || month.equals("jui") || month.equals("jul"))
            return Calendar.JULY;
        if (month.equals("août") || month.equals("august") || month.equals("aou") || month.equals("aoû")
                || month.equals("aug"))
            return Calendar.AUGUST;
        if (month.equals("septembre") || month.equals("september") || month.equals("sep"))
            return Calendar.SEPTEMBER;
        if (month.equals("octobre") || month.equals("october") || month.equals("oct"))
            return Calendar.OCTOBER;
        if (month.equals("novembre") || month.equals("november") || month.equals("nov"))
            return Calendar.NOVEMBER;
        if (month.equals("décembre") || month.equals("december") || month.equals("déc") || month.equals("dec"))
            return Calendar.DECEMBER;
        return 0;
    }

    public static Date getDate(String dateStr, String delimiter) {
        try {
            String[] tokens = dateStr.trim().toLowerCase().split(delimiter);
            SimpleDateFormat dateFormatter = null;
            if (tokens[0].length() >= 3 && tokens[1].contains(",")) { // dateStr in english format
                dateFormatter = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
                String monthStr = tokens[0];
                int monthInt = getMonth(monthStr) + 1;
                monthStr = (monthInt >= 10) ? Integer.toString(monthInt) : "0".concat(Integer.toString(monthInt));
                dateStr = tokens[2].concat("/").concat(monthStr).concat("/").concat(tokens[1].substring(0, 2));
                return getDate(dateStr, "/");
            } else if (tokens[0].length() == 4) { // dateStr in english format
                dateFormatter = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
                return dateFormatter.parse(dateStr);
            } else if (tokens[1].length() >= 3) { // dateStr in french format
                dateFormatter = new SimpleDateFormat("dd" + delimiter + "MM" + delimiter + "yyyy");
                String monthStr = tokens[1];
                int monthInt = getMonth(monthStr) + 1;
                monthStr = (monthInt >= 10) ? Integer.toString(monthInt) : "0".concat(Integer.toString(monthInt));
                dateStr = tokens[0].concat("/").concat(monthStr).concat("/").concat(tokens[2]);
                return getDate(dateStr, "/");
            } else if (tokens[2].length() == 4) { // dateStr in french format
                dateFormatter = new SimpleDateFormat("dd" + delimiter + "MM" + delimiter + "yyyy");
                // System.out.println("Date str : " + dateStr + "\n date format : " +
                // dateFormatter.parse(dateStr));
                return dateFormatter.parse(dateStr);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public Date getDateTime(String dateTimeStr) {
        Calendar cal = new GregorianCalendar();
        try {
            cal.set(Integer.parseInt(dateTimeStr.substring(0, 4)), Integer.parseInt(dateTimeStr.substring(5, 7)) - 1,
                    Integer.parseInt(dateTimeStr.substring(8, 10)));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateTimeStr.substring(11, 13)));
            cal.set(Calendar.MINUTE, Integer.parseInt(dateTimeStr.substring(14, 16)));
            cal.set(Calendar.SECOND, Integer.parseInt(dateTimeStr.substring(17, 19)));
            cal.set(Calendar.MILLISECOND, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }

    public static Date getDateTime(String dateStr, String delimiter, String timeStr) {
        Calendar cal = new GregorianCalendar();
        try {
            Date date = getDate(dateStr, delimiter);
            String tokensTime[] = timeStr.split(":");
            if (dateStr != null) {
                cal.set(date.getYear() + 1900, date.getMonth(), date.getDate());
            } else
                cal.set(1000, 1, 1);

            if (timeStr != null) {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tokensTime[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(tokensTime[1]));
                return cal.getTime();
            }
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cal.getTime();
    }

    public static Date getDateTime(Date date, String timeStr) {
        Calendar cal = new GregorianCalendar();
        try {
            String tokensTime[] = timeStr.split(":");
            if (date != null) {
                cal.set(date.getYear() + 1900, date.getMonth(), date.getDate());
            } else
                cal.set(1000, 1, 1);

            if (timeStr != null) {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tokensTime[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(tokensTime[1]));
                return cal.getTime();
            }
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();

        } catch (Exception e) {
        }
        return cal.getTime();
    }

}