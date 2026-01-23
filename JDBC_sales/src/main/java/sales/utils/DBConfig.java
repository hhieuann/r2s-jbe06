package sales.utils;

/**
 * DBConfig: chứa cấu hình kết nối DB.
 * Bạn đổi URL/USER/PASS theo máy bạn.
 */
public class DBConfig {
    // MySQL 8+
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/sales?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASS = "BiBonnie@316"; // đổi theo mật khẩu của bạn
}
