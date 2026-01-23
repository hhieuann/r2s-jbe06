package form;

import entity.Category;
import util.ScannerUtil;
import util.ValidationUtil;

import java.util.Scanner;

public class CategoryForm {

    public static Category inputNewCategory() {
        while (true) {
            String name = ScannerUtil.readNonEmptyString("Enter category name: ");
            if (ValidationUtil.isValidString(name)) {
                return new Category(0, name.trim());
            }
            System.out.println("Category name must not be empty.");
        }
    }

    public static Category inputUpdateCategory() {
        int id = ScannerUtil.readInt("Enter category ID to update: ");
        String name = ScannerUtil.readNonEmptyString("Enter new category name: ");
        return new Category(id, name.trim());
    }

    public static int inputCategoryId(String action) {
        return ScannerUtil.readInt("Enter category ID to " + action + ": ");
    }

    public static String inputSearchKeyword() {
        System.out.print("Enter keyword (blank for all): ");
        return new Scanner(System.in).nextLine();
    }
}
