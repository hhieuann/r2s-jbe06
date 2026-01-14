package BusinessObjects;

import Core.Entities.Clothing;
import Core.Entities.Electronics;
import Core.Entities.Product;
import Core.Interfaces.IProductRepository;
import Utilities.DataInput;

public class ProductService {
    private final IProductRepository repo;
    private final DataInput input;

    public ProductService(IProductRepository repo, DataInput input) {
        this.repo = repo;
        this.input = input;
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = input.readInt("Chọn: ", 0, 5);
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    findProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n========= PRODUCT MANAGEMENT (OOP - CRUD) =========");
        System.out.println("1. Add product (Electronics/Clothing)");
        System.out.println("2. Display all products");
        System.out.println("3. Find product by ID");
        System.out.println("4. Update product by ID");
        System.out.println("5. Delete product by ID");
        System.out.println("0. Exit");
        System.out.println("==================================================");
    }

    /**
     * Kiểm tra ID đã tồn tại chưa
     */
    private boolean isDuplicateId(int id) {
        return repo.findById(id) != null;
    }

    private void addProduct() {
        if (repo.isFull()) {
            System.out.println("Danh sách đã đầy (MAX). Không thể thêm!");
            return;
        }

        int type = input.readInt("Chọn loại (1-Electronics, 2-Clothing): ", 1, 2);

        int id;
        while (true) {
            id = input.readInt("Nhập ID (>=1): ", 1, Integer.MAX_VALUE);
            if (isDuplicateId(id)) {
                System.out.println("ID đã tồn tại. Nhập ID khác!");
            } else break;
        }

        String name = input.readNonEmptyString("Nhập Name: ");
        float price = input.readFloat("Nhập Price (>=0): ", 0, Float.MAX_VALUE);

        Product p;
        if (type == 1) {
            String brand = input.readNonEmptyString("Nhập Brand: ");
            p = new Electronics(id, name, price, brand);
        } else {
            String size = input.readNonEmptyString("Nhập Size: ");
            p = new Clothing(id, name, price, size);
        }

        boolean ok = repo.add(p);
        System.out.println(ok ? "Thêm thành công!" : "Thêm thất bại!");
    }

    private void displayProducts() {
        Product[] list = repo.getAll();
        if (list.length == 0) {
            System.out.println("Danh sách rỗng.");
            return;
        }

        System.out.println("\n----- Danh sách sản phẩm -----");
        for (Product p : list) {
            // Polymorphism: toString() gọi getType/getExtraInfo của đúng subclass
            System.out.println(p);
        }
    }

    private void findProduct() {
        int id = input.readInt("Nhập ID cần tìm: ", 1, Integer.MAX_VALUE);
        Product p = repo.findById(id);
        if (p == null) {
            System.out.println("Không tìm thấy sản phẩm có ID=" + id);
        } else {
            System.out.println("Tìm thấy: " + p);
        }
    }

    private void updateProduct() {
        int id = input.readInt("Nhập ID cần update: ", 1, Integer.MAX_VALUE);
        Product old = repo.findById(id);
        if (old == null) {
            System.out.println("Không tìm thấy ID=" + id);
            return;
        }

        System.out.println("Sản phẩm hiện tại: " + old);
        System.out.println("Chọn cách update:");
        System.out.println("1) Giữ nguyên loại (update field)");
        System.out.println("2) Đổi sang loại khác (Electronics <-> Clothing)");

        int opt = input.readInt("Chọn: ", 1, 2);

        Product updated;
        if (opt == 1) {
            // Giữ nguyên type của old
            String name = input.readNonEmptyString("Nhập Name mới: ");
            float price = input.readFloat("Nhập Price mới (>=0): ", 0, Float.MAX_VALUE);

            if (old instanceof Electronics) {
                String brand = input.readNonEmptyString("Nhập Brand mới: ");
                updated = new Electronics(id, name, price, brand);
            } else {
                String size = input.readNonEmptyString("Nhập Size mới: ");
                updated = new Clothing(id, name, price, size);
            }
        } else {
            // Đổi type
            int type = input.readInt("Đổi sang loại (1-Electronics, 2-Clothing): ", 1, 2);
            String name = input.readNonEmptyString("Nhập Name mới: ");
            float price = input.readFloat("Nhập Price mới (>=0): ", 0, Float.MAX_VALUE);

            if (type == 1) {
                String brand = input.readNonEmptyString("Nhập Brand: ");
                updated = new Electronics(id, name, price, brand);
            } else {
                String size = input.readNonEmptyString("Nhập Size: ");
                updated = new Clothing(id, name, price, size);
            }
        }

        boolean ok = repo.update(updated);
        System.out.println(ok ? "Update thành công!" : "Update thất bại!");
    }

    private void deleteProduct() {
        int id = input.readInt("Nhập ID cần xóa: ", 1, Integer.MAX_VALUE);
        boolean ok = repo.remove(id);
        System.out.println(ok ? "Xóa thành công!" : "Không tìm thấy để xóa!");
    }
}
