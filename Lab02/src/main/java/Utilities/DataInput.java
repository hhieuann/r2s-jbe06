package Utilities;

import java.util.Scanner;

public class DataInput {
    private static final Scanner sc = new Scanner(System.in);
    static Validator validator = new Validator();

    public static String inputString(String message) {
        while (true) {
            System.out.print(message);
            String s = sc.nextLine();
            if (validator.isNotEmpty(s)) return s.trim();
            System.out.println(">> Không được để trống!");
        }
    }

    public static String inputOptionalString(String message) {
        System.out.print(message);
        return sc.nextLine().trim(); // có thể rỗng
    }

    public static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String raw = sc.nextLine().trim();
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println(">> Vui lòng nhập số nguyên!");
            }
        }
    }

    public static int inputIntInRange(String message, int min, int max) {
        while (true) {
            int n = inputInt(message);
            if (n >= min && n <= max) return n;
            System.out.println(">> Vui lòng nhập trong khoảng [" + min + ", " + max + "]");
        }
    }

    public static boolean confirmYesNo(String message) {
        while (true) {
            System.out.print(message + " (Y/N): ");
            String s = sc.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.println(">> Chỉ nhập Y hoặc N!");
        }
    }
}
