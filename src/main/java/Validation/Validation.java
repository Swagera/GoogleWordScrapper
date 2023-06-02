package Validation;

import net.sf.cglib.core.Local;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern FILE_NAME_PATTERN = Pattern.compile("[^\\\\/:*?\"<>|]+");

    public static boolean isFormValid(String monthFrom, String monthTo, String dayFrom, String dayTo, String yearFrom, String yearTo) {
        boolean isMonthFromValid = isValidMonth(monthFrom);
        boolean isMonthToValid = isValidMonth(monthTo);
        boolean isDayFromValid = isValidDay(monthFrom, dayFrom);
        boolean isDayToValid = isValidDay(monthTo, dayTo);
        boolean isYearFromValid = isValidYear(yearFrom);
        boolean isYearToValid = isValidYear(yearTo);
        boolean isDateFromValid = isValidDate(dayFrom, monthFrom, yearFrom);
        boolean isDateToValid = isValidDate(dayTo, monthTo, yearTo);
        boolean isDateFromToValid = isDateFromToValid(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);

        if (!isMonthFromValid) {
            JOptionPane.showMessageDialog(null, "Invalid month value for 'Month From' field");
            return false;
        }
        if (!isMonthToValid) {
            JOptionPane.showMessageDialog(null, "Invalid month value for 'Month To' field");
            return false;
        }
        if (!isDayFromValid) {
            JOptionPane.showMessageDialog(null, "Invalid day value for 'Day From' field. Please enter a number between 1 and the maximum days of the selected month.");
            return false;
        }
        if (!isDayToValid) {
            JOptionPane.showMessageDialog(null, "Invalid day value for 'Day To' field. Please enter a number between 1 and the maximum days of the selected month.");
            return false;
        }
        if (!isYearFromValid) {
            JOptionPane.showMessageDialog(null, "Invalid year value for 'Year From' field");
            return false;
        }
        if (!isYearToValid) {
            JOptionPane.showMessageDialog(null, "Invalid year value for 'Year To' field");
            return false;
        }
        if (!isDateFromValid) {
            JOptionPane.showMessageDialog(null, "You cannot choose date from the past");
            return false;
        }
        if (!isDateToValid) {
            JOptionPane.showMessageDialog(null, "You cannot choose date from the past");
            return false;
        }
        if (!isDateFromToValid) {
            JOptionPane.showMessageDialog(null, "Check in date cannot be after check out date");
            return false;
        }
        return true;
    }

    public static boolean isValidFileName(String fileName) {
        return FILE_NAME_PATTERN.matcher(fileName).matches();
    }
    private static boolean isValidMonth(String month) {
        String[] validMonths = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        for (String validMonth : validMonths) {
            if (month.equals(validMonth)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isValidDay(String month, String value) {
        int maxDays = 31;

        if (isValidMonth(month)) {
            String[] monthsWith30Days = {"4", "6", "9", "11"};
            String[] monthsWith28Days = {"2"};

            if (Arrays.asList(monthsWith30Days).contains(month)) {
                maxDays = 30;
            } else if (Arrays.asList(monthsWith28Days).contains(month)) {
                maxDays = 28;
            }
        }

        try {
            int number = Integer.parseInt(value);
            return number >= 1 && number <= maxDays;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidYear(String value) {
        int min = 2023;
        int max = 2028;
        try {
            int number = Integer.parseInt(value);
            return number >= min && number <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String day, String month, String year){
        int dayInt = Integer.parseInt(day);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        LocalDate currentDate = LocalDate.now();
        LocalDate comparisonDate = LocalDate.of(yearInt, monthInt, dayInt);

        return comparisonDate.isAfter(currentDate) || comparisonDate.isEqual(currentDate);

    }
    public static boolean isDateFromToValid(String dayFrom, String monthFrom, String yearFrom, String dayTo, String monthTo, String yearTo){
        int dayIntFrom = Integer.parseInt(dayFrom);
        int monthIntFrom = Integer.parseInt(monthFrom);
        int yearIntFrom = Integer.parseInt(yearFrom);
        int dayIntTo = Integer.parseInt(dayTo);
        int monthIntTo = Integer.parseInt(monthTo);
        int yearIntTo = Integer.parseInt(yearTo);
        LocalDate comparisonDateFrom = LocalDate.of(yearIntFrom, monthIntFrom, dayIntFrom);
        LocalDate comparisonDateTo = LocalDate.of(yearIntTo, monthIntTo, dayIntTo);

        return comparisonDateTo.isAfter(comparisonDateFrom) || comparisonDateTo.isEqual(comparisonDateFrom);
    }
}