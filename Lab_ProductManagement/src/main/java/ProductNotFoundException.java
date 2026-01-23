public class ProductNotFoundException extends Exception {
    /**
     * Custom Exception dùng khi không tìm thấy product theo ID
     * @param message thông báo lỗi
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
