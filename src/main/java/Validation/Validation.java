package Validation;

import java.util.regex.Pattern;

public class Validation {

    private static final Pattern FILE_NAME_PATTERN = Pattern.compile("[^\\\\/:*?\"<>|]+");

    public static boolean isValidFileName(String fileName) {
        return FILE_NAME_PATTERN.matcher(fileName).matches();
    }
}