package util;

public class ValidationUtil {
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Basic email format validation (simple regex for lab use).
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String e = email.trim();
        if (e.isEmpty()) return false;
        return e.matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Phone digits validation (length 6-20).
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        String p = phone.trim();
        if (p.isEmpty()) return false;
        return p.matches("^\\d{6,20}$");
    }
}