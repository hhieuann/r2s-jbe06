package sales.client;

import sales.entities.Employee;

import java.util.Scanner;

/**
 * EmployeeForm: phụ trách nhập dữ liệu Employee từ bàn phím.
 */
public class EmployeeForm {
    private final Scanner sc;

    public EmployeeForm(Scanner sc) {
        this.sc = sc;
    }

    /**
     * getId(): nhập employee_id để update/delete.
     */
    public int getId() {
        System.out.print("Nhap employee_id: ");
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Sai dinh dang! Nhap so: ");
        }
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        return id;
    }

    /**
     * getEmployee(): nhập thông tin employee.
     * birthdate nhập theo yyyy-MM-dd (ví dụ 2003-10-27)
     * supervisorId có thể bỏ trống -> null
     */
    public Employee getEmployee() {
        Employee e = new Employee();

        System.out.print("Last name: ");
        e.setLastName(sc.nextLine().trim());

        System.out.print("First name: ");
        e.setFirstName(sc.nextLine().trim());

        System.out.print("Birthdate (yyyy-MM-dd, enter de bo qua): ");
        String bd = sc.nextLine().trim();
        e.setBirthdate(bd.isEmpty() ? null : bd);

        System.out.print("Supervisor id (enter de null): ");
        String sup = sc.nextLine().trim();
        if (sup.isEmpty()) {
            e.setSupervisorId(null);
        } else {
            try {
                e.setSupervisorId(Integer.parseInt(sup));
            } catch (NumberFormatException ex) {
                e.setSupervisorId(null);
            }
        }

        return e;
    }
}
