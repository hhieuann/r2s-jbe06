package training.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Validator: tập trung các hàm validate theo Business Rules.
 * Tối ưu: dùng HashSet cho flags hợp lệ.
 */
public class Validator {

    // Flag hợp lệ (HashSet -> contains O(1))
    private static final Set<String> ALLOWED_FLAGS = new HashSet<>(
            Arrays.asList("optional", "prerequisite", "N/A")
    );

    /**
     * Validate code: 5 ký tự, bắt đầu "RA" + 3 chữ số.
     * Ví dụ: RA001
     */
    public static boolean validateCode(String code) {
        if (code == null) return false;
        return code.matches("^RA\\d{3}$");
    }

    /**
     * Validate status input dạng string: chỉ nhận "true" hoặc "false".
     */
    public static boolean validateStatusInput(String statusText) {
        return "true".equals(statusText) || "false".equals(statusText);
    }

    /**
     * Validate duration > 0.
     */
    public static boolean validateDuration(short duration) {
        return duration > 0;
    }

    /**
     * Validate flag: optional / prerequisite / N/A
     */
    public static boolean validateFlag(String flag) {
        if (flag == null) return false;
        // chấp nhận đúng chữ hoa/thường theo đề (N/A) và 2 cái còn lại thường
        if ("N/A".equals(flag)) return true;
        return ALLOWED_FLAGS.contains(flag.toLowerCase());
    }

    /**
     * Kiểm tra string có phải số kiểu short không (tránh crash parse).
     */
    public static boolean isShortNumber(String s) {
        if (s == null || s.trim().isEmpty()) return false;
        try {
            Short.parseShort(s.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
