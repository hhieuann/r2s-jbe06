package app;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import entity.Category;
import exception.DAOException;
import exception.GlobalExceptionHandler;
import form.CategoryForm;
import util.Constants;

import java.util.List;
import java.util.Scanner;

public class CategoryApp {
    public static void run() {
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== CATEGORY MENU =====");
            System.out.println("1. List all categories");
            System.out.println("2. Search categories by name");
            System.out.println("3. Add new category");
            System.out.println("4. Update category");
            System.out.println("5. Delete category");
            System.out.println("6. Find category by ID");
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
                        List<Category> list = categoryDAO.findAll();
                        System.out.println(Constants.CATEGORY_HEADER);
                        for (Category c : list) {
                            System.out.printf(Constants.CATEGORY_ROW_FORMAT + "%n", c.getCategoryId(), c.getCategoryName());
                        }
                    }
                    case 2 -> {
                        String kw = CategoryForm.inputSearchKeyword();
                        List<Category> list = categoryDAO.searchByName(kw);
                        System.out.println(Constants.CATEGORY_HEADER);
                        for (Category c : list) {
                            System.out.printf(Constants.CATEGORY_ROW_FORMAT + "%n", c.getCategoryId(), c.getCategoryName());
                        }
                    }
                    case 3 -> {
                        int newId = categoryDAO.insert(CategoryForm.inputNewCategory());
                        System.out.println("Added category. New category_id=" + newId);
                    }
                    case 4 -> {
                        categoryDAO.update(CategoryForm.inputUpdateCategory());
                        System.out.println("Category updated.");
                    }
                    case 5 -> {
                        categoryDAO.delete(CategoryForm.inputCategoryId("delete"));
                        System.out.println("Category deleted.");
                    }
                    case 6 -> {
                        Category c = categoryDAO.findById(CategoryForm.inputCategoryId("find"));
                        if (c == null) {
                            System.out.println("Category not found");
                        } else {
                            System.out.println(Constants.CATEGORY_HEADER);
                            System.out.printf(Constants.CATEGORY_ROW_FORMAT + "%n",
                                    c.getCategoryId(), c.getCategoryName());
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
}
