package sales.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtils: tạo Connection cho JDBC.
 */
public class DBUtils {

    /**
     * getConnection(): tạo kết nối tới database
     * @return Connection đang mở
     * @throws SQLException nếu kết nối lỗi
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DBConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy JDBC Driver. Kiểm tra thư viện driver!", e);
        }
        return DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASS);
    }
}
