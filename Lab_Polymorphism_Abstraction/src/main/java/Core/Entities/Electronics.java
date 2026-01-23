package Core.Entities;

public class Electronics extends Product {
    private String brand;

    /**
     * Hàm tạo Electronics
     * @param id mã sản phẩm
     * @param name tên sản phẩm
     * @param price giá
     * @param brand hãng
     */
    public Electronics(int id, String name, float price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getType() {
        return "Electronics";
    }

    @Override
    public String getExtraInfo() {
        return "Brand=" + brand;
    }
}