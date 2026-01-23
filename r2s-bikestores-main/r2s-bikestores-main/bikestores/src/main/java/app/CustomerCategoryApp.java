package app;

import java.util.Scanner;

/**
 * Wrapper menu for Customer & Category module (DashboardUI option 4).
 */
public class CustomerCategoryApp {
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CUSTOMER & CATEGORY MENU =====");
            System.out.println("1. Customer Management");
            System.out.println("2. Category Management");
            System.out.println("0. Back to main menu");
            System.out.print("Choose: ");
            String input = scanner.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> CustomerApp.run();
                case 2 -> CategoryApp.run();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
