package dao;

import entity.Customer;
import exception.DAOException;

import java.util.List;

public interface CustomerDAO {
    int insert(Customer customer) throws DAOException;
    void update(Customer customer) throws DAOException;
    void delete(int customerId) throws DAOException;
    Customer findById(int customerId) throws DAOException;
    List<Customer> findAll() throws DAOException;

    /**
     * Flexible search: pass null/"" if you don't use a criterion.
     */
    List<Customer> search(Integer id, String name, String email, String phone) throws DAOException;
}
