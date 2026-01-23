package sales.dao;

import sales.entities.Employee;

import java.sql.*;
import java.util.ArrayList;

/**
 * EmployeeDAO: thao tác DB với bảng employees.
 * Lưu ý: insert() bắt buộc dùng Stored Procedure sp_add_employee theo đề.
 */
public class EmployeeDAO {
    private final Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * insert: thêm employee bằng Stored Procedure sp_add_employee.
     * @param employee dữ liệu employee (id không cần)
     */
    public boolean insert(Employee employee) {
        String call = "{CALL sp_add_employee(?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setString(1, employee.getLastName());
            cs.setString(2, employee.getFirstName());

            // birthdate đang lưu String theo format yyyy-MM-dd
            // -> chuyển sang java.sql.Date để set vào SP
            Date birth = null;
            if (employee.getBirthdate() != null && !employee.getBirthdate().trim().isEmpty()) {
                birth = Date.valueOf(employee.getBirthdate().trim());
            }
            cs.setDate(3, birth);

            // supervisor_id có thể null
            if (employee.getSupervisorId() == null || employee.getSupervisorId() == 0) {
                cs.setNull(4, Types.INTEGER);
            } else {
                cs.setInt(4, employee.getSupervisorId());
            }

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert employee (SP) lỗi: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Birthdate sai format! Hãy nhập yyyy-MM-dd");
            return false;
        }
    }

    /**
     * update: cập nhật employee theo id (dùng PreparedStatement).
     */
    public boolean update(int id, Employee employee) {
        String sql = "UPDATE employees SET last_name=?, first_name=?, birth_date=?, supervisor_id=? WHERE employee_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());

            Date birth = null;
            if (employee.getBirthdate() != null && !employee.getBirthdate().trim().isEmpty()) {
                birth = Date.valueOf(employee.getBirthdate().trim());
            }
            ps.setDate(3, birth);

            if (employee.getSupervisorId() == null || employee.getSupervisorId() == 0) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, employee.getSupervisorId());
            }

            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update employee lỗi: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Birthdate sai format! Hãy nhập yyyy-MM-dd");
            return false;
        }
    }

    /**
     * delete: xóa employee theo id.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM employees WHERE employee_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete employee lỗi: " + e.getMessage());
            return false;
        }
    }

    /**
     * selectAll: lấy toàn bộ employees.
     */
    public ArrayList<Employee> selectAll() {
        ArrayList<Employee> list = new ArrayList<>();
        String sql = "SELECT employee_id, last_name, first_name, birth_date, supervisor_id FROM employees";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer supervisor = (Integer) rs.getObject("supervisor_id"); // có thể null
                String birth = null;
                Date bd = rs.getDate("birth_date");
                if (bd != null) birth = bd.toString();

                Employee e = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        birth,
                        supervisor
                );
                list.add(e);
            }
        } catch (SQLException e) {
            System.out.println("SelectAll employees lỗi: " + e.getMessage());
        }
        return list;
    }
}
