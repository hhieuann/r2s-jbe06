public class Product {
    // ===== Thuộc tính của sản phẩm =====
    private int productID;
    private String name;
    private double price;
    private int quantityInStock;

    /**
     * Hàm khởi tạo: khởi tạo đầy đủ thông tin Product
     * @param productID mã sản phẩm (int)
     * @param name tên sản phẩm (String)
     * @param price giá sản phẩm (double) - phải >= 0
     * @param quantityInStock số lượng tồn (int) - phải >= 0
     */
    public Product(int productID, String name, double price, int quantityInStock) {
        // Validate dữ liệu đầu vào (âm là sai)
        if (price < 0 || quantityInStock < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty.");
        }

        this.productID = productID;
        this.name = name.trim();
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // ===== Getter/Setter =====
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be empty.");
        }
        this.name = name.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }
        this.quantityInStock = quantityInStock;
    }

    /**
     * In thông tin sản phẩm theo định dạng dễ đọc
     */
    public void displayProductInfo() {
        System.out.println("Product Info:");
        System.out.println("- ID: " + productID);
        System.out.println("- Name: " + name);
        System.out.println("- Price: " + price);
        System.out.println("- Quantity In Stock: " + quantityInStock);
    }
}
