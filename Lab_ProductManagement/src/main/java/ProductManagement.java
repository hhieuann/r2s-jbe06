public class ProductManagement {
    // Mảng tối đa 10 sản phẩm
    private final Product[] products = new Product[10];
    private int size = 0; // số lượng sản phẩm hiện có

    /**
     * Thêm sản phẩm vào mảng (tối đa 10)
     * Rule:
     * - Không quá 10 sản phẩm
     * - productID phải unique
     * - price, quantity phải >= 0 (đã validate trong Product, nhưng vẫn check thêm)
     * @param p sản phẩm cần thêm
     */
    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product must not be null.");
        }

        // Check giới hạn 10 sản phẩm
        if (size >= products.length) {
            throw new IllegalStateException("The array should accept no more than 10 products.");
        }

        // Check trùng ID
        if (existsId(p.getProductID())) {
            throw new IllegalArgumentException("Duplicate product ID is not allowed.");
        }

        // Check dữ liệu âm (an toàn thêm)
        if (p.getPrice() < 0 || p.getQuantityInStock() < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }

        // Thêm vào mảng
        products[size] = p;
        size++;

        System.out.println("Product added successfully.");
    }

    /**
     * Tìm sản phẩm theo ID
     * Nếu không thấy -> throw ProductNotFoundException
     * @param id mã sản phẩm
     * @return Product tìm được
     */
    public Product getProductById(int id) throws ProductNotFoundException {
        for (int i = 0; i < size; i++) {
            if (products[i].getProductID() == id) {
                return products[i];
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found.");
    }

    /**
     * Update số lượng tồn kho theo ID
     * Rule:
     * - Nếu ID không tồn tại -> ProductNotFoundException
     * - quantity phải >= 0, nếu âm -> IllegalArgumentException
     * @param id mã sản phẩm
     * @param newQuantity số lượng mới
     */
    public void updateQuantity(int id, int newQuantity) throws ProductNotFoundException {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }

        Product p = getProductById(id); // có thể ném ProductNotFoundException
        p.setQuantityInStock(newQuantity);

        System.out.println("Quantity updated successfully.");
    }

    /**
     * Kiểm tra ID có tồn tại trong mảng không
     * @param id mã sản phẩm
     * @return true nếu đã tồn tại
     */
    private boolean existsId(int id) {
        for (int i = 0; i < size; i++) {
            if (products[i].getProductID() == id) {
                return true;
            }
        }
        return false;
    }
}
