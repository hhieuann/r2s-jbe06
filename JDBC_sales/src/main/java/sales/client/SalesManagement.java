package sales.client;

import sales.dao.CustomerDAO;
import sales.dao.EmployeeDAO;
import sales.entities.Customer;
import sales.entities.Employee;
import sales.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * SalesManagement: chạy chương trình console theo menu đề.
 */
public class SalesManagement {

    private final Scanner sc;
    private final CustomerDAO customerDAO;
    private final EmployeeDAO employeeDAO;
    private final CustomerForm customerForm;
    private final EmployeeForm employeeForm;

    public SalesManagement() throws SQLException {
        this.sc = new Scanner(System.in);

        // Tạo connection 1 lần cho app (demo project nhỏ)
        Connection conn = getConnection();

        this.customerDAO = new CustomerDAO(conn);
        this.employeeDAO = new EmployeeDAO(conn);

        this.customerForm = new CustomerForm(sc);
        this.employeeForm = new EmployeeForm(sc);
    }

    /**
     * getConnection(): lấy connection từ DBUtils.
     */
    private Connection getConnection() throws SQLException {
        return DBUtils.getConnection();
    }

    private void printMenu() {
        System.out.println("\n1. Get all customers");
        System.out.println("2. Add new a customer");
        System.out.println("3. Change customer information");
        System.out.println("4. Remove a customer");
        System.out.println("5. Get all employees");
        System.out.println("6. Add new an employee");
        System.out.println("7. Change employee information");
        System.out.println("8. Remove an employee");
        System.out.println("0. Quit");
        System.out.print("Your choice: ");
    }

    private int readChoice() {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Nhap so (0-8): ");
        }
        int c = sc.nextInt();
        sc.nextLine(); // clear buffer
        return c;
    }

    // ===== Customer features =====
    public void displayAllCustomers() {
        ArrayList<Customer> list = customerDAO.selectAll();
        if (list.isEmpty()) {
            System.out.println("Danh sach customer rong!");
            return;
        }
        list.forEach(System.out::println);
    }

    public void addCustomer() {
        Customer c = customerForm.getCustomer();
        boolean ok = customerDAO.insert(c);
        System.out.println(ok ? "Them customer thanh cong!" : "Them customer that bai!");
    }

    public void updateCustomer() {
        int id = customerForm.getId();
        Customer c = customerForm.getCustomer();
        boolean ok = customerDAO.update(id, c);
        System.out.println(ok ? "Cap nhat customer thanh cong!" : "Cap nhat customer that bai!");
    }

    public void removeCustomer() {
        int id = customerForm.getId();
        boolean ok = customerDAO.delete(id);
        System.out.println(ok ? "Xoa customer thanh cong!" : "Xoa customer that bai!");
    }

    // ===== Employee features (Functional Requirements) =====
    public void displayAllEmployees() {
        ArrayList<Employee> list = employeeDAO.selectAll();
        if (list.isEmpty()) {
            System.out.println("Danh sach employee rong!");
            return;
        }
        list.forEach(System.out::println);
    }

    /**
     * addEmployee: BẮT BUỘC dùng Stored Procedure (employeeDAO.insert()).
     */
    public void addEmployee() {
        Employee e = employeeForm.getEmployee();
        boolean ok = employeeDAO.insert(e);
        System.out.println(ok ? "Them employee (SP) thanh cong!" : "Them employee (SP) that bai!");
    }

    public void updateEmployee() {
        int id = employeeForm.getId();
        Employee e = employeeForm.getEmployee();
        boolean ok = employeeDAO.update(id, e);
        System.out.println(ok ? "Cap nhat employee thanh cong!" : "Cap nhat employee that bai!");
    }

    public void removeEmployee() {
        int id = employeeForm.getId();
        boolean ok = employeeDAO.delete(id);
        System.out.println(ok ? "Xoa employee thanh cong!" : "Xoa employee that bai!");
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = readChoice();

            switch (choice) {
                case 1: displayAllCustomers(); break;
                case 2: addCustomer(); break;
                case 3: updateCustomer(); break;
                case 4: removeCustomer(); break;

                case 5: displayAllEmployees(); break;
                case 6: addEmployee(); break;     // SP
                case 7: updateEmployee(); break;
                case 8: removeEmployee(); break;

                case 0:
                    System.out.println("Bye!");
                    return;

                default:
                    System.out.println("Lua chon khong hop le (0-8).");
            }
        }
    }

    public static void main(String[] args) {
        try {
            SalesManagement app = new SalesManagement();
            app.run();
        } catch (SQLException e) {
            System.out.println("Loi ket noi DB / SQL: " + e.getMessage());
        }
    }
}
