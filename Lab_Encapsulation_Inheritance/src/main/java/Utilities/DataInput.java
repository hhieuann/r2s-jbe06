package Utilities;

import java.util.Scanner;

public class DataInput {
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Đọc chuỗi không rỗng.
     */
    public static String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String s = SC.nextLine();
            if (!Validation.isNullOrEmpty(s)) return s.trim();
            System.out.println("Khong duoc de trong!");
        }
    }

    /**
     * Đọc số nguyên có giới hạn.
     */
    public static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int n = Integer.parseInt(SC.nextLine().trim());
                if (n < min || n > max) {
                    System.out.println("Gia tri phai trong [" + min + ", " + max + "]");
                    continue;
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    /**
     * Đọc boolean dạng Y/N.
     */
    public static boolean readBoolean(String message) {
        while (true) {
            System.out.print(message);
            String s = SC.nextLine().trim();
            if (s.equalsIgnoreCase("Y")) return true;
            if (s.equalsIgnoreCase("N")) return false;
            System.out.println("Chi nhap Y hoac N!");
        }
    }
}
