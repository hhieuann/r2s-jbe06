package Core.Entities;

public class Clothing extends Product {
    private String size;

    /**
     * Hàm tạo Clothing
     * @param id mã sản phẩm
     * @param name tên sản phẩm
     * @param price giá
     * @param size kích cỡ
     */
    public Clothing(int id, String name, float price, String size) {
        super(id, name, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getType() {
        return "Clothing";
    }

    @Override
    public String getExtraInfo() {
        return "Size=" + size;
    }
}