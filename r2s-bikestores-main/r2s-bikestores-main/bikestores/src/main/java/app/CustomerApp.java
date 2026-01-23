package app;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import entity.Customer;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.CustomerForm;
import util.Constants;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class CustomerApp {
    public static void run() {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. List all customers");
            System.out.println("2. Search customers (id/name/email/phone)");
            System.out.println("3. Add new customer");
            System.out.println("4. Update customer");
            System.out.println("5. Delete customer");
            System.out.println("6. Find customer by ID");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            String choiceInput = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        List<Customer> list = customerDAO.findAll();
                        printCustomers(list);
                    }
                    case 2 -> {
                        CustomerForm.SearchCriteria c = CustomerForm.inputSearchCriteria();
                        List<Customer> list = customerDAO.search(c.id, c.name, c.email, c.phone);
                        printCustomers(list);
                    }
                    case 3 -> {
                        int newId = customerDAO.insert(CustomerForm.inputNewCustomer());
                        System.out.println("Added customer. New customer_id=" + newId);
                    }
                    case 4 -> {
                        customerDAO.update(CustomerForm.inputUpdateCustomer());
                        System.out.println("Customer updated.");
                    }
                    case 5 -> {
                        customerDAO.delete(CustomerForm.inputCustomerId("delete"));
                        System.out.println("Customer deleted.");
                    }
                    case 6 -> {
                        Customer cus = customerDAO.findById(CustomerForm.inputCustomerId("find"));
                        if (cus == null) {
                            System.out.println("Customer not found");
                        } else {
                            printCustomers(Arrays.asList(cus));
                        }
                    }
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (DAOException e) {
                GlobalExceptionHandler.handle(e);
            }
        }
    }

    private static void printCustomers(List<Customer> list) {
        System.out.println(Constants.CUSTOMER_HEADER);
        for (Customer c : list) {
            System.out.printf(Constants.CUSTOMER_ROW_FORMAT + "%n",
                    c.getCustomerId(),
                    c.getName(),
                    c.getGender(),
                    c.getPhone(),
                    c.getEmail()
            );
        }
    }
}
