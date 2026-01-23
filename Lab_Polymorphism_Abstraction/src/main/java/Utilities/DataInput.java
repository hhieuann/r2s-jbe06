package Utilities;

import java.util.Scanner;

public class DataInput {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Đọc số nguyên với ràng buộc min..max
     */
    public int readInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.print(msg);
                int x = Integer.parseInt(sc.nextLine().trim());
                if (x < min || x > max) {
                    System.out.println("Giá trị phải trong [" + min + ", " + max + "]");
                    continue;
                }
                return x;
            } catch (Exception e) {
                System.out.println("Sai định dạng số nguyên. Nhập lại!");
            }
        }
    }

    /**
     * Đọc float với ràng buộc min..max
     */
    public float readFloat(String msg, float min, float max) {
        while (true) {
            try {
                System.out.print(msg);
                float x = Float.parseFloat(sc.nextLine().trim());
                if (x < min || x > max) {
                    System.out.println("Giá trị phải trong [" + min + ", " + max + "]");
                    continue;
                }
                return x;
            } catch (Exception e) {
                System.out.println("Sai định dạng số thực. Nhập lại!");
            }
        }
    }

    /**
     * Đọc chuỗi không rỗng
     */
    public String readNonEmptyString(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                System.out.println("Không được để trống. Nhập lại!");
                continue;
            }
            return s;
        }
    }
}
