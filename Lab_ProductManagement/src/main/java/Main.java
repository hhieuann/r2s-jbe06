import java.util.Scanner;

public class Main {

    /**
     * Hàm hiển thị menu lựa chọn chức năng
     */
    private static void showMenu() {
        System.out.println("=== Product Management Menu ===");
        System.out.println("1. Add Product");
        System.out.println("2. Retrieve Product by ID");
        System.out.println("3. Update Product Quantity");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductManagement pm = new ProductManagement();

        while (true) {
            showMenu();

            // Đọc lựa chọn menu (chống lỗi nhập chữ)
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number from 1 to 4.");
                continue;
            }

            switch (choice) {
                case 1: // Add Product
                    try {
                        System.out.println("Enter product details:");

                        System.out.print("Product ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Product Name: ");
                        String name = sc.nextLine();

                        System.out.print("Product Price: ");
                        double price = Double.parseDouble(sc.nextLine().trim());

                        System.out.print("Quantity in Stock: ");
                        int qty = Integer.parseInt(sc.nextLine().trim());

                        // Tạo product (có validate âm trong constructor)
                        Product p = new Product(id, name, price, qty);

                        // Add vào mảng (có check trùng ID + max 10)
                        pm.addProduct(p);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter correct numeric values.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        // IllegalArgumentException: dữ liệu âm / tên rỗng / trùng ID
                        // IllegalStateException: mảng đầy 10
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: // Retrieve Product by ID
                    try {
                        System.out.print("Enter Product ID to retrieve: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        Product product = pm.getProductById(id);

                        // In ra đúng kiểu sample (gọn)
                        System.out.println("Product ID: " + product.getProductID());
                        System.out.println("Name: " + product.getName());
                        System.out.println("Price: $" + product.getPrice());
                        System.out.println("Quantity in Stock: " + product.getQuantityInStock());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter a valid ID.");
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // Update Product Quantity
                    try {
                        System.out.print("Enter Product ID to update: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter new quantity: ");
                        int newQty = Integer.parseInt(sc.nextLine().trim());

                        pm.updateQuantity(id, newQty);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter valid numbers.");
                    } catch (ProductNotFoundException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4: // Exit
                    System.out.println("Exiting the program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose from 1 to 4.");
            }

            System.out.println(); // dòng trống cho đẹp giống sample
        }
    }
}
