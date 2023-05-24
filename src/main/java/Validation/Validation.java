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
        String lowercaseMonth = month.toLowerCase();
        String[] validMonths = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"};
        for (String validMonth : validMonths) {
            if (lowercaseMonth.equals(validMonth)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isValidDay(String month, String value) {
        int maxDays = 31;
        String lowercaseMonth = month.toLowerCase();

        if (isValidMonth(month)) {
            String[] monthsWith30Days = {"april", "june", "september", "november", "kwiecień", "czerwiec", "wrzesień", "listopad"};
            String[] monthsWith28Days = {"february", "luty"};

            if (Arrays.asList(monthsWith30Days).contains(lowercaseMonth)) {
                maxDays = 30;
            } else if (Arrays.asList(monthsWith28Days).contains(lowercaseMonth)) {
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
        int monthInt = getMonth(month);
        int yearInt = Integer.parseInt(year);
        LocalDate currentDate = LocalDate.now();
        LocalDate comparisonDate = LocalDate.of(yearInt, monthInt, dayInt);

        return comparisonDate.isAfter(currentDate) || comparisonDate.isEqual(currentDate);

    }
    public static boolean isDateFromToValid(String dayFrom, String monthFrom, String yearFrom, String dayTo, String monthTo, String yearTo){
        int dayIntFrom = Integer.parseInt(dayFrom);
        int monthIntFrom = getMonth(monthFrom);
        int yearIntFrom = Integer.parseInt(yearFrom);
        int dayIntTo = Integer.parseInt(dayTo);
        int monthIntTo = getMonth(monthTo);
        int yearIntTo = Integer.parseInt(yearTo);

        return dayIntFrom <= dayIntTo && monthIntFrom <= monthIntTo && yearIntFrom <= yearIntTo;
    }
    public static int getMonth(String month) {
        return switch (month.toLowerCase()) {
            case "styczeń" -> 1;
            case "luty" -> 2;
            case "marzec" -> 3;
            case "kwiecień" -> 4;
            case "maj" -> 5;
            case "czerwiec" -> 6;
            case "lipiec" -> 7;
            case "sierpień" -> 8;
            case "wrzesień" -> 9;
            case "październik" -> 10;
            case "listopad" -> 11;
            case "grudzień" -> 12;
            default -> -1;
        };

    }

}