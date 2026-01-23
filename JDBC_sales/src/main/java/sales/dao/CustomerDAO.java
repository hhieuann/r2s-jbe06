package sales.dao;

import sales.entities.Customer;

import java.sql.*;
import java.util.ArrayList;

/**
 * CustomerDAO: thao tác DB với bảng customers.
 */
public class CustomerDAO {
    private final Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * insert: thêm customer (dùng PreparedStatement).
     */
    public boolean insert(Customer customer) {
        String sql = "INSERT INTO customers(customer_name, contact_name, address, city, post_code, country) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert customer lỗi: " + e.getMessage());
            return false;
        }
    }

    /**
     * update: cập nhật customer theo id.
     */
    public boolean update(int id, Customer customer) {
        String sql = "UPDATE customers SET customer_name=?, contact_name=?, address=?, city=?, post_code=?, country=? " +
                "WHERE customer_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getPostCode());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update customer lỗi: " + e.getMessage());
            return false;
        }
    }

    /**
     * delete: xóa customer theo id.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM customers WHERE customer_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Delete customer lỗi: " + e.getMessage());
            return false;
        }
    }

    /**
     * selectAll: lấy toàn bộ customers.
     */
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "SELECT customer_id, customer_name, contact_name, address, city, post_code, country FROM customers";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("contact_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("post_code"),
                        rs.getString("country")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("SelectAll customers lỗi: " + e.getMessage());
        }
        return list;
    }
}
