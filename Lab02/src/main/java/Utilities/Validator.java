package Utilities;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    public static boolean isValidAge(int age) {
        return age >= 18;
    }

    public static boolean isValidGender(String gender) {
        if (gender == null) return false;
        String g = gender.trim().toLowerCase();
        return g.equals("male") || g.equals("female");
    }

    public static String normalizeGender(String gender) {
        return gender.trim().toLowerCase();
    }

    public static boolean isValidEmail(String email) {
        if (!isNotEmpty(email)) return false;
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
}
