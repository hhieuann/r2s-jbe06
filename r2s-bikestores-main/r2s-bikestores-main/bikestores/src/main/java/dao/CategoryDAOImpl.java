package dao;

import entity.Category;
import exception.DAOException;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public int insert(Category category) throws DAOException {
        // Check duplicate name (case-insensitive)
        if (isCategoryNameExists(category.getCategoryName(), null)) {
            throw new DAOException("Category name already exists.", null);
        }

        String sql = "INSERT INTO categories (category_name) VALUES (?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, category.getCategoryName());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return 0;
        } catch (SQLException e) {
            throw new DAOException("Failed to insert category.", e);
        }
    }

    @Override
    public void update(Category category) throws DAOException {
        if (findById(category.getCategoryId()) == null) {
            throw new DAOException("Category not found.", null);
        }
        if (isCategoryNameExists(category.getCategoryName(), category.getCategoryId())) {
            throw new DAOException("Category name already exists.", null);
        }

        String sql = "UPDATE categories SET category_name=? WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getCategoryId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to update category.", e);
        }
    }

    @Override
    public void delete(int categoryId) throws DAOException {
        // block delete if used by products
        if (isCategoryUsedByProducts(categoryId)) {
            throw new DAOException("Category in use by products, cannot delete.", null);
        }

        String sql = "DELETE FROM categories WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to delete category.", e);
        }
    }

    @Override
    public Category findById(int categoryId) throws DAOException {
        String sql = "SELECT * FROM categories WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Category(rs.getInt("category_id"), rs.getString("category_name"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find category by ID.", e);
        }
        return null;
    }

    @Override
    public List<Category> findAll() throws DAOException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories ORDER BY category_id";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Category(rs.getInt("category_id"), rs.getString("category_name")));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to get category list.", e);
        }
        return list;
    }

    @Override
    public List<Category> searchByName(String keyword) throws DAOException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE category_name LIKE ? ORDER BY category_id";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + (keyword == null ? "" : keyword.trim()) + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Category(rs.getInt("category_id"), rs.getString("category_name")));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to search categories.", e);
        }
        return list;
    }

    // ===== Helper methods =====

    private boolean isCategoryUsedByProducts(int categoryId) throws DAOException {
        String sql = "SELECT COUNT(*) FROM products WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check category usage.", e);
        }
        return false;
    }

    private boolean isCategoryNameExists(String name, Integer excludeId) throws DAOException {
        String sql = "SELECT COUNT(*) FROM categories WHERE LOWER(category_name)=LOWER(?)";
        if (excludeId != null) {
            sql += " AND category_id<>?";
        }
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            if (excludeId != null) {
                stmt.setInt(2, excludeId);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to check duplicate category name.", e);
        }
        return false;
    }
}
