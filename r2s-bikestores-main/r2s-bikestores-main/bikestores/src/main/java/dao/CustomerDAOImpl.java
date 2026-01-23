package dao;

import entity.Customer;
import exception.DAOException;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public int insert(Customer customer) throws DAOException {
        // Optional: check duplicate email/phone
        if (isEmailOrPhoneExists(customer.getEmail(), customer.getPhone(), null)) {
            throw new DAOException("Email or phone already exists.", null);
        }

        String sql = "INSERT INTO customers (name, gender, phone, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getGender());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to insert customer.", e);
        }
    }

    @Override
    public void update(Customer customer) throws DAOException {
        Customer existing = findById(customer.getCustomerId());
        if (existing == null) {
            throw new DAOException("Customer not found.", null);
        }

        // Optional: block duplicate email/phone (excluding this id)
        if (isEmailOrPhoneExists(customer.getEmail(), customer.getPhone(), customer.getCustomerId())) {
            throw new DAOException("Email or phone already exists.", null);
        }

        String sql = "UPDATE customers SET name=?, gender=?, phone=?, email=? WHERE customer_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getGender());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.setInt(5, customer.getCustomerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to update customer.", e);
        }
    }

    @Override
    public void delete(int customerId) throws DAOException {
        // Block delete if linked to orders / reviews / wishlists
        if (hasLinkedRecords(customerId)) {
            throw new DAOException("Customer has linked records (orders/reviews/wishlists), cannot delete.", null);
        }

        String sql = "DELETE FROM customers WHERE customer_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to delete customer.", e);
        }
    }

    @Override
    public Customer findById(int customerId) throws DAOException {
        String sql = "SELECT * FROM customers WHERE customer_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find customer by ID.", e);
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws DAOException {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY customer_id";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to get customer list.", e);
        }
        return list;
    }

    @Override
    public List<Customer> search(Integer id, String name, String email, String phone) throws DAOException {
        StringBuilder sql = new StringBuilder("SELECT * FROM customers WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            sql.append(" AND customer_id=?");
            params.add(id);
        }
        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name.trim() + "%");
        }
        if (email != null && !email.trim().isEmpty()) {
            sql.append(" AND email LIKE ?");
            params.add("%" + email.trim() + "%");
        }
        if (phone != null && !phone.trim().isEmpty()) {
            sql.append(" AND phone LIKE ?");
            params.add("%" + phone.trim() + "%");
        }
        sql.append(" ORDER BY customer_id");

        List<Customer> list = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to search customers.", e);
        }
        return list;
    }

    // ===== Helper methods =====

    private Customer mapRow(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("phone"),
                rs.getString("email")
        );
    }

    private boolean isEmailOrPhoneExists(String email, String phone, Integer excludeId) throws DAOException {
        // If both null/empty -> no need check
        boolean hasEmail = email != null && !email.trim().isEmpty();
        boolean hasPhone = phone != null && !phone.trim().isEmpty();
        if (!hasEmail && !hasPhone) return false;

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM customers WHERE ");
        List<Object> params = new ArrayList<>();
        if (hasEmail && hasPhone) {
            sql.append("(email=? OR phone=?)");
            params.add(email.trim());
            params.add(phone.trim());
        } else if (hasEmail) {
            sql.append("email=?");
            params.add(email.trim());
        } else {
            sql.append("phone=?");
            params.add(phone.trim());
        }

        if (excludeId != null) {
            sql.append(" AND customer_id<>?");
            params.add(excludeId);
        }

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check duplicate email/phone.", e);
        }
        return false;
    }

    private boolean hasLinkedRecords(int customerId) throws DAOException {
        // check orders, reviews, wishlists
        return count("orders", customerId) > 0
                || count("reviews", customerId) > 0
                || count("wishlists", customerId) > 0;
    }

    private int count(String table, int customerId) throws DAOException {
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE customer_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check linked records.", e);
        }
        return 0;
    }
}
