package form;

import entity.Customer;
import util.ScannerUtil;
import util.ValidationUtil;

import java.util.Scanner;

public class CustomerForm {

    public static Customer inputNewCustomer() {
        String name = readValidName();
        String gender = readGender();
        String phone = readValidPhone();
        String email = readValidEmail();
        return new Customer(0, name, gender, phone, email);
    }

    public static Customer inputUpdateCustomer() {
        int id = ScannerUtil.readInt("Enter customer ID to update: ");
        String name = readValidName();
        String gender = readGender();
        String phone = readValidPhone();
        String email = readValidEmail();
        return new Customer(id, name, gender, phone, email);
    }

    public static int inputCustomerId(String action) {
        return ScannerUtil.readInt("Enter customer ID to " + action + ": ");
    }

    public static SearchCriteria inputSearchCriteria() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Customer ID (blank to skip): ");
        String idStr = sc.nextLine().trim();
        Integer id = null;
        if (!idStr.isEmpty()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID. Ignored.");
            }
        }

        System.out.print("Name (blank to skip): ");
        String name = sc.nextLine();

        System.out.print("Email (blank to skip): ");
        String email = sc.nextLine();

        System.out.print("Phone (blank to skip): ");
        String phone = sc.nextLine();

        return new SearchCriteria(id, name, email, phone);
    }

    // ===== Helpers =====

    private static String readValidName() {
        while (true) {
            String name = ScannerUtil.readNonEmptyString("Full name: ").trim();
            if (ValidationUtil.isValidString(name)) return name;
            System.out.println("Name must not be empty.");
        }
    }

    private static String readValidEmail() {
        while (true) {
            String email = ScannerUtil.readNonEmptyString("Email: ").trim();
            if (ValidationUtil.isValidEmail(email)) return email;
            System.out.println("Invalid email format.");
        }
    }

    private static String readValidPhone() {
        while (true) {
            String phone = ScannerUtil.readNonEmptyString("Phone (digits only): ").trim();
            if (ValidationUtil.isValidPhone(phone)) return phone;
            System.out.println("Invalid phone. Digits only, length 6-20.");
        }
    }

    private static String readGender() {
        while (true) {
            System.out.println("Gender: 1.Male  2.Female  3.Other");
            int c = ScannerUtil.readInt("Choose (1-3): ");
            switch (c) {
                case 1:
                    return "Male";
                case 2:
                    return "Female";
                case 3:
                    return "Other";
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Small DTO for search inputs.
     */
    public static class SearchCriteria {
        public final Integer id;
        public final String name;
        public final String email;
        public final String phone;

        public SearchCriteria(Integer id, String name, String email, String phone) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
    }
}
