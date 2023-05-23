package Validation;

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

        int currentDay = currentDate.getDayOfMonth();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        if (dayInt >= currentDay && monthInt >= currentMonth && yearInt >= currentYear){
            return true;
        } else {
            return false;
        }
    }
    public static int getMonth(String month) {
        int monthInt;

        switch (month.toLowerCase()) {
            case "styczeń":
                monthInt = 1;
                break;
            case "luty":
                monthInt = 2;
                break;
            case "marzec":
                monthInt = 3;
                break;
            case "kwiecień":
                monthInt = 4;
                break;
            case "maj":
                monthInt = 5;
                break;
            case "czerwiec":
                monthInt = 6;
                break;
            case "lipiec":
                monthInt = 7;
                break;
            case "sierpień":
                monthInt = 8;
                break;
            case "wrzesień":
                monthInt = 9;
                break;
            case "październik":
                monthInt = 10;
                break;
            case "listopad":
                monthInt = 11;
                break;
            case "grudzień":
                monthInt = 12;
                break;
            default:
                monthInt = -1;
                break;
        }
        return monthInt;
    }

}