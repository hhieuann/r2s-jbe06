package sales.client;

import sales.entities.Customer;

import java.util.Scanner;

/**
 * CustomerForm: phụ trách nhập dữ liệu Customer từ bàn phím.
 */
public class CustomerForm {
    private final Scanner sc;

    public CustomerForm(Scanner sc) {
        this.sc = sc;
    }

    /**
     * getId(): nhập id customer để update/delete.
     */
    public int getId() {
        System.out.print("Nhap customer_id: ");
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Sai dinh dang! Nhap so: ");
        }
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        return id;
    }

    /**
     * getCustomer(): nhập thông tin customer.
     */
    public Customer getCustomer() {
        Customer c = new Customer();

        System.out.print("Customer name: ");
        c.setName(sc.nextLine().trim());

        System.out.print("Contact name: ");
        c.setContact(sc.nextLine().trim());

        System.out.print("Address: ");
        c.setAddress(sc.nextLine().trim());

        System.out.print("City: ");
        c.setCity(sc.nextLine().trim());

        System.out.print("Post code: ");
        c.setPostCode(sc.nextLine().trim());

        System.out.print("Country: ");
        c.setCountry(sc.nextLine().trim());

        return c;
    }
}
